package ma.enset.bankaccountservice.service;

import jakarta.transaction.Transactional;
import ma.enset.bankaccountservice.domain.BankAccount;
import ma.enset.bankaccountservice.domain.Transaction;
import ma.enset.bankaccountservice.dtos.BankAccountRequestDto;
import ma.enset.bankaccountservice.dtos.BankAccountResponseDto;
import ma.enset.bankaccountservice.dtos.TransactionRequestDto;
import ma.enset.bankaccountservice.dtos.TransactionResponseDto;
import ma.enset.bankaccountservice.exceptions.AccountNotFoundException;
import ma.enset.bankaccountservice.exceptions.CustomerNotFoundException;
import ma.enset.bankaccountservice.feign.CustomerRestClient;
import ma.enset.bankaccountservice.mappers.BankAccountMapper;
import ma.enset.bankaccountservice.mappers.TransactionMapper;
import ma.enset.bankaccountservice.model.Customer;
import ma.enset.bankaccountservice.repository.BankAccountRepository;
import ma.enset.bankaccountservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService{
    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BankAccountMapper bankAccountMapper;

    @Autowired
    TransactionMapper transactionMapper;

    @Autowired
    CustomerRestClient customerRestClient;

    @Override
    public List<BankAccountResponseDto> getAccounts() {
        List<BankAccount> bankAccounts =  bankAccountRepository.findAll();
        bankAccounts.forEach(bankAccount -> {
            bankAccount.setCustomer(customerRestClient.getCustomer(bankAccount.getCustomerId()));
        });
        return bankAccounts.stream().map(bankAccount -> bankAccountMapper.fromBankAccount(bankAccount)).toList();
    }

    @Override
    public BankAccountResponseDto getAccount(String id) throws AccountNotFoundException {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow(()->
                new AccountNotFoundException(String.format("There is no account with id: %s", id)));

        Customer customer = customerRestClient.getCustomer(account.getCustomerId());
        account.setCustomer(customer);
        return bankAccountMapper.fromBankAccount(account);
    }

    @Override
    public BankAccountResponseDto addAccount(BankAccountRequestDto bankAccountRequestDto) {

        try {
            Customer customer = customerRestClient.getCustomer(bankAccountRequestDto.getCustomerId());
            BankAccount bankAccount = BankAccount.builder()
                    .id(UUID.randomUUID().toString())
                    .createdAt(new Date())
                    .type(bankAccountRequestDto.getType())
                    .balance(bankAccountRequestDto.getBalance())
                    .currency(bankAccountRequestDto.getCurrency())
                    .customerId(bankAccountRequestDto.getCustomerId())
                    .customer(customer)
                    .build();
            BankAccount savedAccount =  bankAccountRepository.save(bankAccount);
            return bankAccountMapper.fromBankAccount(savedAccount);

        }catch (Exception ex){
            throw new CustomerNotFoundException(String.format("There is no customer with the id: %s",
                    bankAccountRequestDto.getCustomerId()));
        }
    }

    @Override
    public List<TransactionResponseDto> getTransactions(){
        List<Transaction> transactions =transactionRepository.findAll();
        return transactions.stream().map(transaction -> transactionMapper.fromTransaction(transaction)
        ).toList();
    }
    @Override
    public List<TransactionResponseDto> getTransactionsByCustomer(String accountId) throws AccountNotFoundException{
        BankAccount account = bankAccountRepository.findById(accountId).orElseThrow(()->
                new AccountNotFoundException(String.format("There is no account with id %s", accountId)));
        List<Transaction> transactions = transactionRepository.findTransactionByAccountID(accountId);
        return transactions.stream().map(transaction -> transactionMapper.fromTransaction(transaction)
        ).toList();
    }

    @Override
    public TransactionResponseDto debitTransaction(TransactionRequestDto transactionRequestDto) throws AccountNotFoundException {
        BankAccount account = bankAccountRepository.findById(transactionRequestDto.getAccountID()).orElseThrow(()->
                new AccountNotFoundException(String.format("There is no account with the id %s", transactionRequestDto.getAccountID())));

        account.setBalance(account.getBalance()-transactionRequestDto.getAmount());

        Transaction newTransaction = Transaction.builder()
                .id(UUID.randomUUID().toString())
                .amount(transactionRequestDto.getAmount())
                .type(transactionRequestDto.getType())
                .accountID(transactionRequestDto.getAccountID())
                .build();
        Transaction savedTransaction = transactionRepository.save(newTransaction);
        return transactionMapper.fromTransaction(savedTransaction);
    }

    @Override
    public TransactionResponseDto creditTransaction(TransactionRequestDto transactionRequestDto) throws AccountNotFoundException {
        BankAccount account = bankAccountRepository.findById(transactionRequestDto.getAccountID()).orElseThrow(()->
                new AccountNotFoundException(String.format("There is no account with the id %s", transactionRequestDto.getAccountID())));

        account.setBalance(account.getBalance()+transactionRequestDto.getAmount());

        Transaction newTransaction = Transaction.builder()
                .id(UUID.randomUUID().toString())
                .amount(transactionRequestDto.getAmount())
                .type(transactionRequestDto.getType())
                .accountID(transactionRequestDto.getAccountID())
                .build();
        Transaction savedTransaction = transactionRepository.save(newTransaction);
        return transactionMapper.fromTransaction(savedTransaction);
    }
}
