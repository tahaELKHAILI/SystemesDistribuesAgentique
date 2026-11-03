package ma.enset.bankaccountmicroservice.entities;

import ma.enset.bankaccountmicroservice.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = BankAccount.class, name = "projection1")
public interface BankAccountProjection {
    public String getId();
    public AccountType getType();
    public Double getBalance();
}
