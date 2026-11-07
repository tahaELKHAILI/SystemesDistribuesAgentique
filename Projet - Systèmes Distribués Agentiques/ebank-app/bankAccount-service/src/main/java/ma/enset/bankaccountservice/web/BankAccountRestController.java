package ma.enset.bankaccountservice.web;

import ma.enset.bankaccountservice.domain.BankAccount;
import ma.enset.bankaccountservice.dtos.BankAccountRequestDto;
import ma.enset.bankaccountservice.dtos.BankAccountResponseDto;
import ma.enset.bankaccountservice.exceptions.AccountNotFoundException;
import ma.enset.bankaccountservice.exceptions.CustomerNotFoundException;
import ma.enset.bankaccountservice.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankAccountRestController {
    @Autowired
    BankAccountService bankAccountService;

    @GetMapping("/bankAccounts")
    public List<BankAccountResponseDto> getAccounts(){
        return bankAccountService.getAccounts();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccountResponseDto getAccount(@PathVariable String id) throws AccountNotFoundException {
        return bankAccountService.getAccount(id);
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDto addAccount(@RequestBody BankAccountRequestDto bankAccountRequestDto) {
        return bankAccountService.addAccount(bankAccountRequestDto);
    }

    @ExceptionHandler
    public String exceptionhandler(Exception ex){
        return ex.getMessage();
    }
}
