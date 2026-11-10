package ma.enset.bankaccountservice.service;

import ma.enset.bankaccountservice.dtos.BankAccountRequestDto;
import ma.enset.bankaccountservice.dtos.BankAccountResponseDto;
import ma.enset.bankaccountservice.dtos.TransactionRequestDto;
import ma.enset.bankaccountservice.dtos.TransactionResponseDto;
import ma.enset.bankaccountservice.exceptions.AccountNotFoundException;

import java.util.List;

public interface BankAccountService {
    public List<BankAccountResponseDto> getAccounts();
    public BankAccountResponseDto getAccount(String id) throws AccountNotFoundException;
    public BankAccountResponseDto addAccount(BankAccountRequestDto bankAccountRequestDto);

    List<TransactionResponseDto> getTransactions();

    List<TransactionResponseDto> getTransactionsByAccount(String accountId) throws AccountNotFoundException;

    public TransactionResponseDto debitTransaction(TransactionRequestDto transactionRequestDto) throws AccountNotFoundException;
    public TransactionResponseDto creditTransaction(TransactionRequestDto transactionRequestDto) throws AccountNotFoundException;
}
