package ma.enset.bankaccountservice.web;

import ma.enset.bankaccountservice.domain.BankAccount;
import ma.enset.bankaccountservice.exceptions.AccountNotFoundException;
import ma.enset.bankaccountservice.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankAccountRestController {
    @Autowired
    BankAccountService bankAccountService;

    @GetMapping("/bankAccounts")
    public List<BankAccount> getAccounts(){
        return bankAccountService.getAccounts();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount getAccount(@PathVariable String id) throws AccountNotFoundException {
        return bankAccountService.getAccount(id);
    }

    @PostMapping("/bankAccounts")
    public BankAccount addAccount(@RequestBody BankAccount bankAccount){
        return bankAccountService.addAccount(bankAccount);
    }

    @ExceptionHandler
    public String exceptionhandler(Exception ex){
        return ex.getMessage();
    }
}
