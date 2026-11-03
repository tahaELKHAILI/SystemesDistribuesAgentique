package ma.enset.bankaccountmicroservice.web;

import ma.enset.bankaccountmicroservice.dtos.BankAccountRequestDTO;
import ma.enset.bankaccountmicroservice.dtos.BankAccountResponseDTO;
import ma.enset.bankaccountmicroservice.entities.BankAccount;
import ma.enset.bankaccountmicroservice.exceptions.AccountNotFoundException;
import ma.enset.bankaccountmicroservice.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphqlController {
    @Autowired
    BankAccountService bankAccountService;

    @QueryMapping
    public List<BankAccountResponseDTO> accountsList(){
        return bankAccountService.getAccounts();
    }
    @QueryMapping
    public BankAccountResponseDTO accountById(@Argument String id) throws AccountNotFoundException {
        return bankAccountService.getAccount(id);
    }

    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccountRequestDTO){
        return bankAccountService.addAccount(bankAccountRequestDTO);
    }

    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id, @Argument BankAccountRequestDTO bankAccountRequestDTO) throws AccountNotFoundException {
        return bankAccountService.updateAccount(id, bankAccountRequestDTO);
    }

    @MutationMapping
    public boolean deleteAccount(@Argument String id){
        bankAccountService.deleteAccount(id);
        return true;
    }
}
