package com.example.expensetracker.controller;
/**
 * @author Cecilia Yang
 */

import com.example.expensetracker.entity.Transaction;
import com.example.expensetracker.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("api/expense")
public class TransactionController {

    Logger logger = LoggerFactory.getLogger(TransactionController.class);
    @Autowired
    private TransactionService transactionService;

    //Create and save a Transaction
    @PostMapping("/save")
    public ResponseEntity createExpense(@RequestBody Transaction transaction){

        logger.info("Create a Transaction");
        transactionService.createTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateExpense(@RequestBody Transaction transaction, @PathVariable Long id){
        logger.info("Update a Transaction");

        Transaction updatedTransaction = transactionService.updateTransaction(transaction, id);
        return ResponseEntity.ok(updatedTransaction);
    }

    //Find Transaction by ID
    @GetMapping("/{id}")
    public ResponseEntity findExpenseById(@PathVariable("id") Long id){
        logger.info("Find a Transaction by ID");
        return ResponseEntity.ok(transactionService.findTransactionById(id));
    }

    //Get All Transactions expenses
    @GetMapping("/expenses")
    public ResponseEntity getAllExpenses(Transaction transaction){
        logger.info("Return all Transactions");
        return ResponseEntity.ok(transactionService.getAllTransactions(transaction));
    }

    @GetMapping(value = "bymonth/{month}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Transaction> getTransactionsByMonth(@PathVariable("month") String month)
    {
        logger.info("Return all Transactions by Month");
        return transactionService.getTransactionsByMonth(month);
    }


//Works
//    @GetMapping("/calculate")
//    public ResponseEntity calculateAmount(Transaction transaction){
//        List<Transaction> transactions = transactionService.getAllTransactions(transaction);
//
//        return ResponseEntity.ok(transactionService.calculateAmount(transactions));
//    }
}
