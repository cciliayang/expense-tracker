package com.example.expensetracker.controller;
/**
 * @author Cecilia Yang
 */

import com.example.expensetracker.entity.Transaction;
import com.example.expensetracker.entity.TransactionCategory;
import com.example.expensetracker.service.ByCategoryService;
import com.example.expensetracker.service.CalculateService;
import com.example.expensetracker.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("api/calculate")
public class CalculateController {
    Logger logger = LoggerFactory.getLogger(CalculateController.class);
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CalculateService calculateService;

    @Autowired
    private ByCategoryService byCategoryService;

    @GetMapping("/total-amount")
    public ResponseEntity calculateAmount(Transaction transaction) {
        logger.info("Calculate Total Transactions Amount:");

        List<Transaction> transactions = transactionService.getAllTransactions(transaction);

        logger.debug("Transactions",transactionService.getAllTransactions(transaction));


        return ResponseEntity.ok(calculateService.calculateAmount(transactions));
    }

    @GetMapping("/category/{transactionCategory}")
    public ResponseEntity calculateByCategory(Transaction transaction, TransactionCategory category) {
        logger.info("Calculate all Transactions by Category:");

        List<Transaction> transactions = byCategoryService.findByCategory(transaction.getTransactionCategory());

        return ResponseEntity.ok(calculateService.calculateCategoryAmount(transactions, category));
    }

    @GetMapping(value = "bymonth/{month}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity calculateTotalByMonth(@PathVariable("month") String month) {

        logger.info("Calculate all Transactions by Month:");

        List<Transaction> transactions = transactionService.getTransactionsByMonth(month);

        return ResponseEntity.ok(calculateService.calculateTransactionsByMonth(transactions, month));
    }

    @GetMapping(value = "monthly/{month}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTotalCostMonthly(@PathVariable("month") String month) {
        logger.info("Calculate Total Transactions Amount:");

        List<Transaction> transactions = transactionService.getTransactionsByMonth(month);

        return ResponseEntity.ok(calculateService.getMonthlyCost(transactions, month));
    }

    //doesnt work need to revisit
    @GetMapping(value = "/{month}/{transactionCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getByCategoryMonthly(@PathVariable("month")String month, Transaction transaction, TransactionCategory transactionCategory) {
        logger.warn("Not working -- need to fix");

        logger.info("Calculate all Transactions Amount by Category monthly:");

        String month2 = "MARCH";

        List<Transaction>transactions = transactionService.getAllTransactions(transaction);

        return ResponseEntity.ok(calculateService.calculateTransactionsMonthlyByCategory(transactions, transactionCategory, month2));
    }

}



