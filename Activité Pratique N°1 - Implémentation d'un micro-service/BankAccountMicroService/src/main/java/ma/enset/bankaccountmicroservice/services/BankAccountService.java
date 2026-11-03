package ma.enset.bankaccountmicroservice.services;


import ma.enset.bankaccountmicroservice.dtos.BankAccountRequestDTO;
import ma.enset.bankaccountmicroservice.dtos.BankAccountResponseDTO;
import ma.enset.bankaccountmicroservice.exceptions.AccountNotFoundException;

import java.util.List;

public interface BankAccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO);
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountRequestDTO) throws AccountNotFoundException;
    public List<BankAccountResponseDTO> getAccounts();
    public BankAccountResponseDTO getAccount(String id) throws AccountNotFoundException;
    public void deleteAccount(String id);
}
