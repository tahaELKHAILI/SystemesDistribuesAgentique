package ma.enset.bankaccountservice.mappers;

import ma.enset.bankaccountservice.domain.Transaction;
import ma.enset.bankaccountservice.dtos.TransactionResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionResponseDto fromTransaction(Transaction transaction){
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        BeanUtils.copyProperties(transaction, transactionResponseDto);

        return transactionResponseDto;
    }
}
