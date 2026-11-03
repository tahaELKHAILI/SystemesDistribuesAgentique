package ma.enset.bankaccountmicroservice.web;

import ma.enset.bankaccountmicroservice.dtos.BankAccountRequestDTO;
import ma.enset.bankaccountmicroservice.dtos.BankAccountResponseDTO;
import ma.enset.bankaccountmicroservice.entities.BankAccount;
import ma.enset.bankaccountmicroservice.exceptions.AccountNotFoundException;
import ma.enset.bankaccountmicroservice.repository.BankAccountRepository;
import ma.enset.bankaccountmicroservice.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BankAccountRestController {

    @Autowired
    BankAccountService bankAccountService;

    @GetMapping("/bankAccounts")
    public List<BankAccountResponseDTO> getAccounts(){
        return bankAccountService.getAccounts();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccountResponseDTO getAccount(@PathVariable String id) throws AccountNotFoundException{
        return bankAccountService.getAccount(id);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
        bankAccountService.deleteAccount(id);
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO addAccount(@RequestBody BankAccountRequestDTO bankAccountRequestDTO){
        return bankAccountService.addAccount(bankAccountRequestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccountResponseDTO updateAccount(@PathVariable String id, @RequestBody BankAccountRequestDTO bankAccountRequestDTO) throws AccountNotFoundException{
        return bankAccountService.updateAccount(id, bankAccountRequestDTO);
    }

    @ExceptionHandler
    public String exceptionhandler(Exception exception){
        return exception.getMessage();
    }

    /*
    @Autowired
    BankAccountRepository bankAccountRepository;

    @GetMapping("/bankAccounts")
    public List<BankAccount> getAccounts(){
        List<BankAccount> accounts = bankAccountRepository.findAll();
        return accounts;
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount getAccount(@PathVariable String id) throws AccountNotFoundException{
        BankAccount account = bankAccountRepository.findById(id).orElseThrow(
                ()->new AccountNotFoundException(String.format("The account %s does not exit",id))
        );
        return account;
    }

    @PostMapping("/bankAccounts")
    public BankAccount addAccount(@RequestBody BankAccount bankAccount){
        return bankAccountRepository.save(bankAccount);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount updateAccount(@PathVariable String id, @RequestBody BankAccount bankAccount) throws AccountNotFoundException{
        BankAccount account = bankAccountRepository.findById(id).orElseThrow(
                ()->new AccountNotFoundException(String.format("The account %s does not exit",id))
        );
        if(bankAccount.getBalance() != null) account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCurrency() != null) account.setCurrency(bankAccount.getCurrency());
        if(bankAccount.getType() != null) account.setType(bankAccount.getType());
        if(bankAccount.getCreatedAt() != null) account.setCreatedAt(bankAccount.getCreatedAt());

        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }

    @ExceptionHandler
    public String exceptionhandler(Exception exception){
        return exception.getMessage();
    }
    */
}
