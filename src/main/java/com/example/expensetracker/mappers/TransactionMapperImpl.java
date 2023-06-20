package com.example.expensetracker.mappers;
/**
 * @author Cecilia Yang
 */
import com.example.expensetracker.dto.TransactionDto;
import com.example.expensetracker.entity.Transaction;
import org.mapstruct.Mapper;

import java.time.LocalDate;
@Mapper
public class TransactionMapperImpl implements TransactionMapper{
    @Override
    public Transaction fromTransactionDTOtoTransaction(TransactionDto transactionDto) {

        if (transactionDto == null){
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setTransactionId(transactionDto.getTransactionId());
        transaction.setTransactionName(transactionDto.getTransactionName());
        transaction.setTransactionCategory(transactionDto.getTransactionCategory());
        transaction.setTransactionAmount(transactionDto.getTransactionAmount());
        transaction.setTransactionDate(LocalDate.parse(transactionDto.getTransactionDate()));

        return transaction;
    }

    @Override
    public TransactionDto fromTransactionToTransactionDTO(Transaction transaction) {

        if (transaction == null){
            return null;
        }

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionId(transaction.getTransactionId());
        transactionDto.setTransactionName(transaction.getTransactionName());
        transactionDto.setTransactionCategory(transaction.getTransactionCategory());
        transactionDto.setTransactionAmount(transaction.getTransactionAmount());
        transactionDto.setTransactionDate(String.valueOf(transaction.getTransactionDate()));
        transactionDto.isNecessary();

        return transactionDto;
    }


}
