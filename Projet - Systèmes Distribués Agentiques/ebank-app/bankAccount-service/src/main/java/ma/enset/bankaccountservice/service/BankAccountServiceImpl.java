package ma.enset.bankaccountservice.service;

import ma.enset.bankaccountservice.domain.BankAccount;
import ma.enset.bankaccountservice.exceptions.AccountNotFoundException;
import ma.enset.bankaccountservice.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BankAccountServiceImpl implements BankAccountService{
    @Autowired
    BankAccountRepository bankAccountRepository;

    @Override
    public List<BankAccount> getAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccount getAccount(String id) throws AccountNotFoundException {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow(()->
                new AccountNotFoundException(String.format("There is no account with id: %s", id)));
        return account;
    }

    @Override
    public BankAccount addAccount(BankAccount bankAccount) {
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());
        return bankAccountRepository.save(bankAccount);
    }
}
