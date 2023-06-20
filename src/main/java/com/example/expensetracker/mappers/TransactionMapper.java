package com.example.expensetracker.mappers;
/**
 * @author Cecilia Yang
 */
import com.example.expensetracker.dto.TransactionDto;
import com.example.expensetracker.entity.Transaction;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface TransactionMapper {

    //Method that converts a TransactionDTO object to Transaction Object
    Transaction fromTransactionDTOtoTransaction(TransactionDto transactionDto);


    //Method that converts a Transaction object to TransactionDTO object
    TransactionDto fromTransactionToTransactionDTO(Transaction transaction);


}
