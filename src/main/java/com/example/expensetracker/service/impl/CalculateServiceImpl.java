package com.example.expensetracker.service.impl;
/**
 * @author Cecilia Yang
 */
import com.example.expensetracker.dto.TransactionsDto;
import com.example.expensetracker.entity.AmountByCategory;
import com.example.expensetracker.entity.Transaction;
import com.example.expensetracker.entity.TransactionCategory;
import com.example.expensetracker.repository.TransactionRepository;
import com.example.expensetracker.service.ByCategoryService;
import com.example.expensetracker.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service
public class CalculateServiceImpl implements CalculateService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ByCategoryService byCategoryService;

    @Override
    public BigDecimal calculateAllTransactions(List<Transaction> transactions) {
        BigDecimal total = new BigDecimal(BigInteger.ZERO);
        for (Transaction transaction : transactions) {
            total = total.add(transaction.getTransactionAmount());
//            System.out.println("Total amount spent is: "+ total);

        }
        return total;
    }

    @Override
    public TransactionsDto calculateAmount(List<Transaction> transactions) {
        TransactionsDto transactionDto = new TransactionsDto();
        transactionDto.setTotalAmount(calculateAllTransactions(transactions));
        transactionDto.setTransactions(transactions);

        return transactionDto;
    }

    @Override
    public BigDecimal getTransactionsByCategory(List<Transaction> transactions, TransactionCategory category){

        BigDecimal calc = calculateAllTransactions(transactions);
        List<Transaction> transactionsByCat = transactionRepository.findByTransactionCategory(category);

        BigDecimal total = new BigDecimal(BigInteger.ZERO);

        for(Transaction transaction : transactions){
            if (transaction.getTransactionCategory().equals(category)){
                total = total.add(transaction.getTransactionAmount());

            }
 }
        return total;
    }

    @Override
    public AmountByCategory calculateCategoryAmount(List<Transaction> transactions, TransactionCategory category) {
        AmountByCategory amountByCategory = new AmountByCategory();
        amountByCategory.setTotalAmount(calculateAllTransactions(transactions));
        amountByCategory.setTransactions(transactions);

        return amountByCategory;
    }


    @Override
    public BigDecimal calculateTransactionsByMonth(List<Transaction> transactions, String month) {

        BigDecimal total = new BigDecimal(BigInteger.ZERO);


        for(Transaction transaction : transactions){
            if(month.equalsIgnoreCase(transaction.getTransactionDate().getMonth().name())){
                total = total.add(transaction.getTransactionAmount());
            }
        }
        return total;
    }

    @Override
    public TransactionsDto getMonthlyCost(List<Transaction> transactions, String month) {

        TransactionsDto transactionsDto = new TransactionsDto();
        transactionsDto.setTransactions(transactions);
        transactionsDto.setTotalAmount(calculateTransactionsByMonth(transactions, month));
        return transactionsDto;
    }

    @Override
    public BigDecimal calculateTransactionsMonthlyByCategory(List<Transaction> transactions, TransactionCategory transactionCategory, String month) {

//        List<Transaction> transactionsByCat = transactionRepository.findAll();

        BigDecimal total = new BigDecimal(BigInteger.ZERO);

        for(Transaction transaction : transactions){
            if (transaction.getTransactionCategory().equals(transactionCategory) && transaction.getTransactionDate().getMonth().toString().equals(month)) {
                total = total.add(transaction.getTransactionAmount());
            }
        }
        return total;

    }

//    @Override
//    public AmountByCategory calculateMonthlyCategoryAmount(TransactionCategory category, String month) {
//        List<Transaction> transactions = transactionRepository.findAll();
//        AmountByCategory amountByCategory = new AmountByCategory();
//        amountByCategory.setTransactions(transactions);
//        amountByCategory.setTotalAmount(calculateTransactionsMonthlyByCategory(transactions, category, month));
//
//        return amountByCategory;
//    }


}