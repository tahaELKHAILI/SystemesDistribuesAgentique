package ma.enset.bankaccountservice.repository;

import ma.enset.bankaccountservice.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    public List<Transaction> findTransactionByAccountID(String id);
}
