package ma.enset.bankaccountservice.mcp;

import ma.enset.bankaccountservice.dtos.BankAccountRequestDto;
import ma.enset.bankaccountservice.dtos.BankAccountResponseDto;
import ma.enset.bankaccountservice.dtos.TransactionRequestDto;
import ma.enset.bankaccountservice.dtos.TransactionResponseDto;
import ma.enset.bankaccountservice.exceptions.AccountNotFoundException;
import ma.enset.bankaccountservice.service.BankAccountService;
import org.springframework.ai.mcp.annotation.McpArg;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BankAccountMcpController {

    @Autowired
    private BankAccountService bankAccountService;

    @McpTool(description = "Get all accounts")
    public List<BankAccountResponseDto> getAccounts() {
        return bankAccountService.getAccounts();
    }

    @McpTool(description = "Get account by id")
    public BankAccountResponseDto getAccount(@McpArg(description = "This is the account id") String id) throws AccountNotFoundException {
        return bankAccountService.getAccount(id);
    }

    @McpTool(description = "Add a new account")
    public BankAccountResponseDto addAccount(
            @McpArg(description = "Account information (balance, currency, type and customerId)") BankAccountRequestDto bankAccountRequestDto) {
        return bankAccountService.addAccount(bankAccountRequestDto);
    }

    @McpTool(description = "Get all transactions")
    public List<TransactionResponseDto> getTransactions() {
        return bankAccountService.getTransactions();
    }

    @McpTool(description = "Get transaction of a particular account by its id")
    public List<TransactionResponseDto> getTransactionsByCustomer(
            @McpArg(description = "This is the account id") String id) throws AccountNotFoundException {
        return bankAccountService.getTransactionsByCustomer(id);
    }

    @McpTool(description = "Debit an amount of money from the account balance")
    public TransactionResponseDto debitTransaction(
            @McpArg(description = "Transaction information (amount, type and accountId") TransactionRequestDto transactionRequestDto) throws AccountNotFoundException {
        return bankAccountService.debitTransaction(transactionRequestDto);
    }

    @McpTool(description = "Credit an amount of money from the account balance")
    public TransactionResponseDto creditTransaction(
            @McpArg(description = "Transaction information (amount, type and accountId") TransactionRequestDto transactionRequestDto) throws AccountNotFoundException {
        return bankAccountService.creditTransaction(transactionRequestDto);
    }
}
