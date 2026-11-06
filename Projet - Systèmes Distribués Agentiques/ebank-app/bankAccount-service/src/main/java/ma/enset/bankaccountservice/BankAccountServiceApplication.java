package ma.enset.bankaccountservice;

import ma.enset.bankaccountservice.domain.BankAccount;
import ma.enset.bankaccountservice.enums.AccountType;
import ma.enset.bankaccountservice.service.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountService bankAccountService){
        return args -> {
            BankAccount bankAccount1 = BankAccount.builder()
                    .type(AccountType.CURRENT_ACCOUNT)
                    .currency("EUR")
                    .balance(5000.0)
                    .customerId("1234")
                    .build();

            bankAccountService.addAccount(bankAccount1);

            BankAccount bankAccount2 = BankAccount.builder()
                    .type(AccountType.SAVING_ACCOUNT)
                    .currency("EUR")
                    .balance(3000.33)
                    .customerId("1234")
                    .build();

            bankAccountService.addAccount(bankAccount2);
        };
    }
}
