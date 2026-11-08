package ma.enset.bankaccountservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.bankaccountservice.enums.TransactionType;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Transaction {
    @Id
    private String id;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private String accountID;
}
