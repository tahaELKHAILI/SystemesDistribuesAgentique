package ma.enset.bankaccountservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.bankaccountservice.enums.TransactionType;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class TransactionResponseDto {
    private String id;
    private Double amount;
    private TransactionType type;
    private String accountID;
}
