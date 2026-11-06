package ma.enset.bankaccountservice.service;

import ma.enset.bankaccountservice.domain.BankAccount;
import ma.enset.bankaccountservice.exceptions.AccountNotFoundException;

import java.util.List;

public interface BankAccountService {
    public List<BankAccount> getAccounts();
    public BankAccount getAccount(String id) throws AccountNotFoundException;
    public BankAccount addAccount(BankAccount bankAccount);
}
