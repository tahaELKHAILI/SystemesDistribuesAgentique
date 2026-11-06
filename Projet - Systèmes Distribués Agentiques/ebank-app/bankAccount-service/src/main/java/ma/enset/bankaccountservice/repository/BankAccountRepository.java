package ma.enset.bankaccountservice.repository;

import ma.enset.bankaccountservice.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
