package com.example.expensetracker.repository;
/**
 * @author Cecilia Yang
 */

import com.example.expensetracker.entity.Transaction;
import com.example.expensetracker.entity.TransactionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

//    @Query("SELECT * FROM expense_table.expense_tracker WHERE transactionCategory = :transaction_category")
    public List<Transaction> findByTransactionCategory(TransactionCategory transactionCategory);

}
