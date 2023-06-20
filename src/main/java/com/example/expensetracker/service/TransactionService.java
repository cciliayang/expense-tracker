package com.example.expensetracker.service;

import com.example.expensetracker.entity.Transaction;
import com.example.expensetracker.entity.TransactionCategory;

import java.time.LocalDate;
import java.util.List;

//@Service
public interface TransactionService {

    public Transaction createTransaction(Transaction transaction);

    public Transaction updateTransaction (Transaction transaction, Long id);

    public Transaction findTransactionById(Long id);
    public List<Transaction> getAllTransactions(Transaction transaction);

    public List<Transaction> getTransactionByMonth(LocalDate transactionDate);

    public List<Transaction> getTransactionsByMonth(String month);

//    public BigDecimal monthlyTransactions(String month);



}
