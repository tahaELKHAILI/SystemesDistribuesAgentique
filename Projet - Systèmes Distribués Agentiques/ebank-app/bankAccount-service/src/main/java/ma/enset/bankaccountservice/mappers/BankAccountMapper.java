package ma.enset.bankaccountservice.mappers;

import ma.enset.bankaccountservice.domain.BankAccount;
import ma.enset.bankaccountservice.dtos.BankAccountResponseDto;
import ma.enset.bankaccountservice.model.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {

    public BankAccountResponseDto fromBankAccount(BankAccount bankAccount){
        BankAccountResponseDto bankAccountResponseDto = new BankAccountResponseDto();
        BeanUtils.copyProperties(bankAccount, bankAccountResponseDto);
        Customer customer = bankAccount.getCustomer();

        if(customer != null) {
            bankAccountResponseDto.getCustomer().setId(bankAccount.getCustomer().getId());
            bankAccountResponseDto.getCustomer().setName(bankAccount.getCustomer().getName());
            bankAccountResponseDto.getCustomer().setEmail(bankAccount.getCustomer().getEmail());
        }

        return bankAccountResponseDto;
    }
}
