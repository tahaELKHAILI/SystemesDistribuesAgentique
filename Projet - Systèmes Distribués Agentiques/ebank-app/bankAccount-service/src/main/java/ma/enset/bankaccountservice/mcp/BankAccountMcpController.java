package ma.enset.bankaccountservice.mcp;

import ma.enset.bankaccountservice.dtos.BankAccountRequestDto;
import ma.enset.bankaccountservice.dtos.BankAccountResponseDto;
import ma.enset.bankaccountservice.dtos.TransactionRequestDto;
import ma.enset.bankaccountservice.dtos.TransactionResponseDto;
import ma.enset.bankaccountservice.enums.AccountType;
import ma.enset.bankaccountservice.enums.TransactionType;
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

    @McpTool(description = "Add or create a new account")
    public BankAccountResponseDto addAccount(
            @McpArg(description = "The Id of customer associated with this account") String customerID,
            @McpArg(description = "The type of account we are creating. Make sure to use CURRENT_ACCOUNT or SAVING_ACCOUNT") AccountType type,
            @McpArg(description = "The balance of the account is a number of type double. e.g, 5000.0") Double balance,
            @McpArg(description = "The currency of the account. Make sure to use acronyms e.g, EUR for EURO") String currency
    ){
        BankAccountRequestDto bankAccountRequestDto = new BankAccountRequestDto(balance,currency,type,customerID);
        return bankAccountService.addAccount(bankAccountRequestDto);
    }

    @McpTool(description = "Get all transactions")
    public List<TransactionResponseDto> getTransactions() {
        return bankAccountService.getTransactions();
    }

    @McpTool(description = "Get transaction of a particular account by its id")
    public List<TransactionResponseDto> getTransactionsByAccount(
            @McpArg(description = "This is the account id") String id) throws AccountNotFoundException {
        return bankAccountService.getTransactionsByAccount(id);
    }

    @McpTool(description = "Debit an amount of money from the account balance")
    public TransactionResponseDto debitTransaction(
            @McpArg(description = "The amount to be debited from the account. It's a number of type double. e.g, 50.0") Double amount,
            @McpArg(description = "The type of the transaction being performed. DEBIT") TransactionType type,
            @McpArg(description = "The id of the account to be debited") String accountId
    ){
        TransactionRequestDto transactionRequestDto = new TransactionRequestDto(amount,type,accountId);
        return bankAccountService.debitTransaction(transactionRequestDto);
    }

    @McpTool(description = "Credit an amount of money from the account balance")
    public TransactionResponseDto creditTransaction(
            @McpArg(description = "The amount to be credit from the account. It's a number of type double. e.g, 50.0") Double amount,
            @McpArg(description = "The type of the transaction being performed. CREDIT") TransactionType type,
            @McpArg(description = "The id of the account to be credited") String accountId
    ){
        TransactionRequestDto transactionRequestDto = new TransactionRequestDto(amount,type,accountId);
        return bankAccountService.creditTransaction(transactionRequestDto);
    }
}
