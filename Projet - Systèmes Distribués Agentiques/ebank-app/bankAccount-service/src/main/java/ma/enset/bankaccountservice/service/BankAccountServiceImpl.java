package ma.enset.bankaccountservice.service;

import jakarta.transaction.Transactional;
import ma.enset.bankaccountservice.domain.BankAccount;
import ma.enset.bankaccountservice.dtos.BankAccountRequestDto;
import ma.enset.bankaccountservice.dtos.BankAccountResponseDto;
import ma.enset.bankaccountservice.exceptions.AccountNotFoundException;
import ma.enset.bankaccountservice.feign.CustomerRestClient;
import ma.enset.bankaccountservice.mappers.BankAccountMapper;
import ma.enset.bankaccountservice.repository.BankAccountRepository;
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
    BankAccountMapper bankAccountMapper;

    @Autowired
    CustomerRestClient customerRestClient;

    @Override
    public List<BankAccountResponseDto> getAccounts() {
        List<BankAccount> bankAccounts =  bankAccountRepository.findAll();
        return bankAccounts.stream().map(bankAccount -> bankAccountMapper.fromBankAccount(bankAccount)).toList();
    }

    @Override
    public BankAccountResponseDto getAccount(String id) throws AccountNotFoundException {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow(()->
                new AccountNotFoundException(String.format("There is no account with id: %s", id)));
        return bankAccountMapper.fromBankAccount(account);
    }

    @Override
    public BankAccountResponseDto addAccount(BankAccountRequestDto bankAccountRequestDto) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .type(bankAccountRequestDto.getType())
                .balance(bankAccountRequestDto.getBalance())
                .currency(bankAccountRequestDto.getCurrency())
                .customerId(bankAccountRequestDto.getCustomerId())
                .customer(customerRestClient.getCustomer(bankAccountRequestDto.getCustomerId()))
                .build();
        BankAccount savedAccount =  bankAccountRepository.save(bankAccount);
        return bankAccountMapper.fromBankAccount(savedAccount);
    }
}
