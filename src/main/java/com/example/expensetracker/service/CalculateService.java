package com.example.expensetracker.service;

import com.example.expensetracker.dto.TransactionsDto;
import com.example.expensetracker.entity.AmountByCategory;
import com.example.expensetracker.entity.Transaction;
import com.example.expensetracker.entity.TransactionCategory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface CalculateService {
    public BigDecimal calculateAllTransactions(List<Transaction> transactions);

    public TransactionsDto calculateAmount(List<Transaction> transactions);

    public BigDecimal getTransactionsByCategory(List<Transaction> transactions, TransactionCategory category);

    public AmountByCategory calculateCategoryAmount(List<Transaction> transactions, TransactionCategory category);

    public BigDecimal calculateTransactionsByMonth(List<Transaction> transactions, String month);

    public TransactionsDto getMonthlyCost(List<Transaction> transactions, String month);

    public BigDecimal calculateTransactionsMonthlyByCategory(List<Transaction> transactions, TransactionCategory transactionCategory,String month);

//    public AmountByCategory calculateMonthlyCategoryAmount(TransactionCategory category,String month);


}
