package ma.enset.bankaccountservice;

import ma.enset.bankaccountservice.domain.BankAccount;
import ma.enset.bankaccountservice.dtos.BankAccountRequestDto;
import ma.enset.bankaccountservice.dtos.TransactionRequestDto;
import ma.enset.bankaccountservice.enums.AccountType;
import ma.enset.bankaccountservice.enums.TransactionType;
import ma.enset.bankaccountservice.feign.CustomerRestClient;
import ma.enset.bankaccountservice.service.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class BankAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountService bankAccountService, CustomerRestClient customerRestClient){
        return args -> {
            BankAccountRequestDto bankAccountRequestDto1 = BankAccountRequestDto.builder()
                    .type(AccountType.CURRENT_ACCOUNT)
                    .currency("EUR")
                    .balance(5000.0)
                    .customerId(customerRestClient.getCustomers().get(0).getId())
                    .build();

            bankAccountService.addAccount(bankAccountRequestDto1);

            BankAccountRequestDto bankAccountRequestDto2 = BankAccountRequestDto.builder()
                    .type(AccountType.SAVING_ACCOUNT)
                    .currency("EUR")
                    .balance(3000.33)
                    .customerId(customerRestClient.getCustomers().get(0).getId())
                    .build();

            bankAccountService.addAccount(bankAccountRequestDto2);

            String accountId= bankAccountService.getAccounts().get(0).getId();

            TransactionRequestDto transactionRequestDto = TransactionRequestDto.builder()
                    .type(TransactionType.CREDIT)
                    .amount(30000.0)
                    .accountID(accountId)
                    .build();

            bankAccountService.creditTransaction(transactionRequestDto);

            TransactionRequestDto transactionRequestDto2 = TransactionRequestDto.builder()
                    .type(TransactionType.DEBIT)
                    .amount(300.0)
                    .accountID(accountId)
                    .build();

            bankAccountService.creditTransaction(transactionRequestDto2);

        };
    }
}
