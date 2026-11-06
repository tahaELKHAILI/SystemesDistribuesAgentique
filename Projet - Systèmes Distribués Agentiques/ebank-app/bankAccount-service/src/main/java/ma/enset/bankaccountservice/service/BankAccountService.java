package ma.enset.bankaccountservice.service;

import ma.enset.bankaccountservice.domain.BankAccount;
import ma.enset.bankaccountservice.dtos.BankAccountRequestDto;
import ma.enset.bankaccountservice.dtos.BankAccountResponseDto;
import ma.enset.bankaccountservice.exceptions.AccountNotFoundException;

import java.util.List;

public interface BankAccountService {
    public List<BankAccountResponseDto> getAccounts();
    public BankAccountResponseDto getAccount(String id) throws AccountNotFoundException;
    public BankAccountResponseDto addAccount(BankAccountRequestDto bankAccountRequestDto);
}
