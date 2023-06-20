package com.example.expensetracker.service.impl;
/**
 * @author Cecilia Yang
 */
import com.example.expensetracker.entity.Transaction;
import com.example.expensetracker.entity.TransactionCategory;
import com.example.expensetracker.repository.TransactionRepository;
import com.example.expensetracker.service.ByCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.expensetracker.entity.TransactionCategory.*;

@Service
public class ByCategoryServiceImpl implements ByCategoryService {
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public List<Transaction> findByCategory(TransactionCategory category) {

        List <Transaction> list = transactionRepository.findByTransactionCategory(category);

        List<Transaction> findCatTransactionList = new ArrayList<>();

        for (Transaction transaction : list ){
            if (transaction.getTransactionCategory().equals(GROCERIES)){
                findCatTransactionList.add(transaction);
            }
        }

        for (Transaction transaction : list ){
            if (transaction.getTransactionCategory().equals(ENTERTAINMENT)){
                findCatTransactionList.add(transaction);
            }
        }

        for (Transaction transaction : list ){
            if (transaction.getTransactionCategory().equals(UTILITIES)){
                findCatTransactionList.add(transaction);
            }
        }
        for (Transaction transaction : list ){
            if (transaction.getTransactionCategory().equals(MISCELLANEOUS)){
                findCatTransactionList.add(transaction);
            }
        }

        for (Transaction transaction : list ){
            if (transaction.getTransactionCategory().equals(RESTAURANT)){
                findCatTransactionList.add(transaction);
            }
        }
        return findCatTransactionList;
    }
}
