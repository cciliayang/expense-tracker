package com.example.expensetracker.controller;
/**
 * @author Cecilia Yang
 */
import com.example.expensetracker.entity.Transaction;
import com.example.expensetracker.entity.TransactionCategory;
import com.example.expensetracker.service.ByCategoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("api/category")
public class CategoryController {

    Logger logger = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private ByCategoryService byCategoryService;


    @GetMapping("/restaurant")
    public List<Transaction> getRestaurantTransactions(TransactionCategory transactionCategory){
        logger.info("Return the Restaurant Category");

        logger.debug("{transactionCategory}:");
        List<Transaction> transactions = byCategoryService.findByCategory(transactionCategory.RESTAURANT);

        return transactions;
    }

    @GetMapping("/entertainment")
    public List<Transaction> getEntertainmentTransactions(TransactionCategory transactionCategory){

        logger.info("Return the Entertainment Category");

        logger.debug("{transactionCategory}:");
        List<Transaction> transactions = byCategoryService.findByCategory(transactionCategory.ENTERTAINMENT);

        return transactions;
    }

    @GetMapping("/utilities")
    public List<Transaction> getUtilitiesTransactions(TransactionCategory transactionCategory){
        logger.info("Return the Utilities Category");

        logger.debug("{transactionCategory}:");
        List<Transaction> transactions = byCategoryService.findByCategory(transactionCategory.UTILITIES);

        return transactions;
    }

    @GetMapping("/groceries")

    public List<Transaction> getGroceriesTransactions(TransactionCategory transactionCategory){
        logger.info("Return the Groceries Category");

        List<Transaction> transactions = byCategoryService.findByCategory(transactionCategory.GROCERIES);

        return transactions;
    }

    @GetMapping("/misc")
    public List<Transaction> getMiscellaneousTransactions(TransactionCategory transactionCategory){

        logger.info("Return the Miscellaneous Category");

        List<Transaction> transactions = byCategoryService.findByCategory(transactionCategory.MISCELLANEOUS);

        return transactions;
    }


}
