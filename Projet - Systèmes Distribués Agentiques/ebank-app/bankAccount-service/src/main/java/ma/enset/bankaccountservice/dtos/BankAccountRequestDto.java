package ma.enset.bankaccountservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.bankaccountservice.enums.AccountType;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class BankAccountRequestDto {
    private Double balance;
    private String currency;
    private AccountType type;
    private String customerId;
}
