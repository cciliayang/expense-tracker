package com.example.expensetracker.service;

import com.example.expensetracker.entity.Transaction;
import com.example.expensetracker.entity.TransactionCategory;

import java.util.List;

public interface ByCategoryService {
    public List<Transaction> findByCategory(TransactionCategory category);
}
