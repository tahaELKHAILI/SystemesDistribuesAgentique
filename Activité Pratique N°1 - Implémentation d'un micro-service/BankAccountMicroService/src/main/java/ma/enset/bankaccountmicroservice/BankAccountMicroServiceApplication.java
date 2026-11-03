package ma.enset.bankaccountmicroservice;

import ma.enset.bankaccountmicroservice.entities.BankAccount;
import ma.enset.bankaccountmicroservice.enums.AccountType;
import ma.enset.bankaccountmicroservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class BankAccountMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountMicroServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository){
        return args -> {
            BankAccount account = BankAccount.builder()
                    .type(AccountType.CURRENT_ACCOUNT)
                    .balance(2000.0)
                    .currency("MAD")
                    .createdAt(new Date())
                    .build();

            bankAccountRepository.save(account);

            BankAccount account2 = BankAccount.builder()
                    .type(AccountType.SAVING_ACCOUNT)
                    .balance(500.0)
                    .currency("MAD")
                    .createdAt(new Date())
                    .build();

            bankAccountRepository.save(account2);
        };
    }
}
