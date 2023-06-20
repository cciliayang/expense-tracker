package com.example.expensetracker.mappers;

import com.example.expensetracker.dto.TransactionDto;
import com.example.expensetracker.entity.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.example.expensetracker.entity.TransactionCategory.GROCERIES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransactionMapperImplTest {
//
//    @Test
//    void toTransactionDTO() {
//
//        TransactionMapperImpl transactionMapper = new TransactionMapperImpl();
//
//        TransactionDto transactionDto = new TransactionDto();
//        transactionDto.setTransactionId(1L);
//        transactionDto.setTransactionName("Chocolate");
//        transactionDto.setTransactionCategory(GROCERIES);
//        transactionDto.setTransactionAmount(BigDecimal.valueOf(3.3));
//        transactionDto.setTransactionDate(String.valueOf(LocalDate.parse("2023-03-23")));
//        transactionDto.isNecessary();
//
//        Transaction transaction = transactionMapper.fromTransactionDTOtoTransaction(transactionDto);
//        System.out.println("Convert to Transaction Entity:" + transaction);
//        System.out.println("ORIGINAL: " + transactionDto);
//
//        assertNotNull(transaction);
//        assertEquals(1L, transaction.getTransactionId());
//        assertEquals("Chocolate", transaction.getTransactionName());
//        assertEquals(GROCERIES, transaction.getTransactionCategory());
//        assertEquals(BigDecimal.valueOf(3.3), transaction.getTransactionAmount());
//        assertEquals("2023-03-23", transactionDto.getTransactionDate());
//        assertEquals((boolean) true, transactionDto.isNecessary());
//
//    }
//
//    @Test
//    void toTransactionEntity() {
//        TransactionMapperImpl transactionMapper = new TransactionMapperImpl();
//
//        Transaction transactionEntity = new Transaction();
//
//        transactionEntity.setTransactionId(1L);
//        transactionEntity.setTransactionName("Chocolate");
//        transactionEntity.setTransactionCategory(GROCERIES);
//        transactionEntity.setTransactionAmount(BigDecimal.valueOf(3.3));
//        transactionEntity.setTransactionDate(LocalDate.parse("2023-03-23"));
//
//        TransactionDto transactionDto = transactionMapper.fromTransactionToTransactionDTO(transactionEntity);
//
//        System.out.println("Converted to Entity: " + transactionDto);
//
//        System.out.println("ORIGINAL:" + transactionEntity);
//
//        assertNotNull(transactionDto);
//        assertEquals(1L, transactionDto.getTransactionId());
//        assertEquals("Chocolate", transactionDto.getTransactionName());
//        assertEquals(GROCERIES, transactionDto.getTransactionCategory());
//        assertEquals(BigDecimal.valueOf(3.3), transactionDto.getTransactionAmount());
//        assertEquals("2023-03-23", transactionDto.getTransactionDate());
//        assertEquals((boolean) true, transactionDto.isNecessary());
//
//    }
}