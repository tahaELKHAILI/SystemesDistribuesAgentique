package ma.enset.bankaccountmicroservice.services;

import jakarta.transaction.Transactional;
import ma.enset.bankaccountmicroservice.dtos.BankAccountRequestDTO;
import ma.enset.bankaccountmicroservice.dtos.BankAccountResponseDTO;
import ma.enset.bankaccountmicroservice.entities.BankAccount;
import ma.enset.bankaccountmicroservice.exceptions.AccountNotFoundException;
import ma.enset.bankaccountmicroservice.mappers.BankAccountMapper;
import ma.enset.bankaccountmicroservice.repository.BankAccountRepository;
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

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .type(bankAccountRequestDTO.getType())
                .currency(bankAccountRequestDTO.getCurrency())
                .balance(bankAccountRequestDTO.getBalance())
                .build();
        BankAccount savedAccount =  bankAccountRepository.save(bankAccount);
        return bankAccountMapper.fromBankAccount(savedAccount);
    }

    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountRequestDTO) throws AccountNotFoundException {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow(()->
                new AccountNotFoundException(String.format("No account with id: %s", id)));
        if(bankAccountRequestDTO.getBalance() != null) account.setBalance(bankAccountRequestDTO.getBalance());
        if(bankAccountRequestDTO.getCurrency() != null) account.setCurrency(bankAccountRequestDTO.getCurrency());
        if(bankAccountRequestDTO.getType() != null) account.setType(bankAccountRequestDTO.getType());

        BankAccount updatedAccount = bankAccountRepository.save(account);
        return bankAccountMapper.fromBankAccount(updatedAccount);
    }

    @Override
    public List<BankAccountResponseDTO> getAccounts() {
        List<BankAccount> accounts = bankAccountRepository.findAll();
        return accounts.stream().map(bankAccount -> bankAccountMapper.fromBankAccount(bankAccount)).toList();
    }

    @Override
    public BankAccountResponseDTO getAccount(String id) throws AccountNotFoundException{
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(() ->
            new AccountNotFoundException(String.format("No account with id: %s", id))
        );
        return bankAccountMapper.fromBankAccount(bankAccount);
    }

    @Override
    public void deleteAccount(String id) {
        bankAccountRepository.deleteById(id);
    }
}
