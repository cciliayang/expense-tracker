package com.example.expensetracker.service;

import com.example.expensetracker.entity.AmountByCategory;
import com.example.expensetracker.entity.Transaction;
import com.example.expensetracker.entity.TransactionCategory;
import com.example.expensetracker.repository.TransactionRepository;
import com.example.expensetracker.service.impl.ByCategoryServiceImpl;
import com.example.expensetracker.service.impl.CalculateServiceImpl;
import com.example.expensetracker.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static com.example.expensetracker.entity.TransactionCategory.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @InjectMocks
    private CalculateServiceImpl calculateService;

    @InjectMocks
    private ByCategoryServiceImpl byCategoryService;

    @Test
    void createTransaction() {

        Transaction transactionEntity = new Transaction();
        transactionEntity.setTransactionId(2L);
        transactionEntity.setTransactionName("Sushi");
        transactionEntity.setTransactionCategory(RESTAURANT);
        transactionEntity.setTransactionAmount(BigDecimal.valueOf(50.23));
        transactionEntity.setTransactionDate(LocalDate.parse("2023-03-24"));

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transactionEntity);

        Transaction newModel = transactionService.createTransaction(transactionEntity);

        System.out.println("Transaction Created:" + newModel);

        assertNotNull(newModel);
        assertEquals(2L, newModel.getTransactionId());
        assertEquals("Sushi", newModel.getTransactionName());
        assertEquals(RESTAURANT, newModel.getTransactionCategory());
        assertEquals(BigDecimal.valueOf(50.23), newModel.getTransactionAmount());
        assertEquals(LocalDate.parse("2023-03-24").getMonth(), newModel.getTransactionDate().getMonth());

    }

    @Test
    void updateTransaction() {

        Transaction transactionEntity = new Transaction();
        transactionEntity.setTransactionId(2L);
        transactionEntity.setTransactionName("Sushi");
        transactionEntity.setTransactionCategory(RESTAURANT);
        transactionEntity.setTransactionAmount(BigDecimal.valueOf(50.23));
        transactionEntity.setTransactionDate(LocalDate.parse("2023-03-24"));

        when(transactionRepository.findById(2L)).thenReturn(Optional.of(transactionEntity));

        Transaction newModel = transactionService.createTransaction(transactionEntity);

        transactionEntity.setTransactionCategory(GROCERIES);
        transactionEntity.setTransactionAmount(BigDecimal.valueOf(10.23));

        Transaction updateTransaction = transactionService.updateTransaction(transactionEntity, 2L);

        assertNotNull(transactionEntity);
        assertEquals(2L, transactionEntity.getTransactionId());
        assertEquals("Sushi", transactionEntity.getTransactionName());
        assertEquals(GROCERIES, transactionEntity.getTransactionCategory());
        assertEquals(BigDecimal.valueOf(10.23), transactionEntity.getTransactionAmount());
        assertEquals(LocalDate.parse("2023-03-24").getMonth(), transactionEntity.getTransactionDate().getMonth());

    }

    @Test
    void getAllTransactions() {

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(1L, "Sushi", RESTAURANT, new BigDecimal(54), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(2L, "Crabbys", RESTAURANT, new BigDecimal(23), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(1L, "Internet", UTILITIES, new BigDecimal(124), LocalDate.parse("2023-03-24")));

        when(transactionRepository.findAll()).thenReturn(transactionList);

        List<Transaction> findAllTransactionList = transactionService.getAllTransactions(new Transaction());

        System.out.println("transaction list: " + findAllTransactionList.stream().toList());

        assertThat(findAllTransactionList).isNotEmpty();
        assertThat(findAllTransactionList.size()).isEqualTo(3);
        assertThat(findAllTransactionList.get(0).getTransactionId()).isEqualTo(1L);
        assertThat(findAllTransactionList.get(0).getTransactionName()).isEqualTo("Sushi");
        assertThat(findAllTransactionList.get(0).getTransactionCategory().equals(RESTAURANT));
        assertThat(findAllTransactionList.get(0).getTransactionAmount()).isEqualTo(BigDecimal.valueOf(54));
        assertThat(findAllTransactionList.get(0).getTransactionDate()).isEqualTo(LocalDate.parse("2023-03-24"));

    }

    @Test
    void findTransactionById() {
//        List<Transaction> transactionList = new ArrayList<>();
//        transactionList.add(new Transaction(1L, "Sushi", RESTAURANT,new BigDecimal(54),LocalDate.parse("2023-03-24")));
//        transactionList.add(new Transaction(2L, "Crabbys", RESTAURANT,new BigDecimal(23),LocalDate.parse("2023-03-24")));
//        transactionList.add(new Transaction(3L, "Internet", UTILITIES,new BigDecimal(124),LocalDate.parse("2023-03-24")));

        //For mock
        Long transactionId = 3L;
        Transaction transaction = new Transaction(3L, "Internet", UTILITIES, new BigDecimal(124), LocalDate.parse("2023-03-24"));

        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));

//        doReturn(Optional.of(transaction)).when(transactionRepository).findById(transactionId);

        //make the service call
        Transaction findId = transactionService.findTransactionById(transaction.getTransactionId());

        assertThat(transaction).isNotNull();
        assertThat(transaction.getTransactionId()).isEqualTo(3L);
        assertThat(transaction.getTransactionName()).isEqualTo("Internet");
        assertThat(transaction.getTransactionCategory().equals(UTILITIES));
        assertThat(transaction.getTransactionAmount()).isEqualTo(BigDecimal.valueOf(124));
        assertThat(transaction.getTransactionDate()).isEqualTo(LocalDate.parse("2023-03-24"));
        assertEquals(transactionId, findId.getTransactionId());

    }

//    @Test
//    void getTransactionByCategory() {
//        //for mock
//        TransactionCategory transactionCategory = RESTAURANT;
////        TransactionCategory transactionCategory = UTILITIES;
//        //List added with transactions
//
//        List<Transaction> transactionList = new ArrayList<>();
//        transactionList.add(new Transaction(1L, "Sushi", RESTAURANT,new BigDecimal(54),LocalDate.parse("2023-03-24")));
//        transactionList.add(new Transaction(2L, "Crabbys", RESTAURANT,new BigDecimal(23),LocalDate.parse("2023-03-24")));
//        transactionList.add(new Transaction(3L, "Internet", UTILITIES,new BigDecimal(124),LocalDate.parse("2023-03-24")));
//        transactionList.add(new Transaction(4L, "Phone", UTILITIES,new BigDecimal(30),LocalDate.parse("2023-03-24")));
//
//        when(transactionRepository.findByTransactionCategory(any(TransactionCategory.class))).thenReturn(transactionList);
//
//        Map<TransactionCategory, List<Transaction>> transactionsByCategory = transactionService.findTransactionsByCategory(transactionList);
//
//        assertEquals(5, transactionsByCategory.size());

//    }

    @Test
    void findByCategory() {
        //for mock
        TransactionCategory transactionCategory = UTILITIES;
        //List added with transactions

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(1L, "Sushi", RESTAURANT, new BigDecimal(54), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(2L, "Crabbys", RESTAURANT, new BigDecimal(23), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(3L, "Internet", UTILITIES, new BigDecimal(124), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(4L, "Phone", UTILITIES, new BigDecimal(30), LocalDate.parse("2023-03-24")));

        when(transactionRepository.findByTransactionCategory(any(TransactionCategory.class))).thenReturn(transactionList);

        List<Transaction> transactions = byCategoryService.findByCategory(transactionCategory);

        assertEquals(4, transactions.size());
        assertEquals(UTILITIES, transactions.get(0).getTransactionCategory());
        assertEquals(RESTAURANT, transactionList.get(0).getTransactionCategory());
    }

    @Test
    void calculateAllTransactions() {

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(1L, "Sushi", RESTAURANT, new BigDecimal(54), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(2L, "Crabbys", RESTAURANT, new BigDecimal(23), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(3L, "Internet", UTILITIES, new BigDecimal(124), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(4L, "Phone", UTILITIES, new BigDecimal(30), LocalDate.parse("2023-03-24")));

        BigDecimal transactions = calculateService.calculateAllTransactions(transactionList);


        assertEquals(new BigDecimal(231), transactions);
    }

    @Test
    void calculateAmount() {

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(1L, "Sushi", RESTAURANT, new BigDecimal(54), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(2L, "Crabbys", RESTAURANT, new BigDecimal(23), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(3L, "Internet", UTILITIES, new BigDecimal(124), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(4L, "Phone", UTILITIES, new BigDecimal(30), LocalDate.parse("2023-03-24")));

        calculateService.calculateAmount(transactionList);

        assertThat(transactionList.get(0).getTransactionId().equals(1L));

    }

    @Test
    void calculateByCategory() {

        TransactionCategory transactionCategory = ENTERTAINMENT;

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(1L, "Museum", ENTERTAINMENT, new BigDecimal(54), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(2L, "Crabbys", RESTAURANT, new BigDecimal(23), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(3L, "Internet", UTILITIES, new BigDecimal(124), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(4L, "Bar", ENTERTAINMENT, new BigDecimal(30), LocalDate.parse("2023-03-24")));

        when(transactionRepository.findByTransactionCategory(any(TransactionCategory.class))).thenReturn(transactionList);
//
//        List<Transaction> transactions = calculateService.getTransactionsByCategory(transactionCategory);

//
        BigDecimal transactions = calculateService.getTransactionsByCategory(transactionList, transactionCategory);

        assertThat(transactionList.get(0).getTransactionCategory().equals(ENTERTAINMENT));
    }


    @Test
    void getTransactionByMonth() {

        LocalDate transactionDate = LocalDate.parse("2023-03-24");
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(1L, "Sushi", RESTAURANT, new BigDecimal(54), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(2L, "Crabbys", RESTAURANT, new BigDecimal(23), LocalDate.parse("2023-04-14")));
        transactionList.add(new Transaction(3L, "Internet", UTILITIES, new BigDecimal(124), LocalDate.parse("2023-05-04")));
        transactionList.add(new Transaction(4L, "Phone", UTILITIES, new BigDecimal(30), LocalDate.parse("2023-06-24")));

//        when(transactionRepository.findByMonth(transactionDate)).thenReturn(transactionList);
        when(transactionRepository.findAll()).thenReturn(transactionList);

        List<Transaction> transactions = transactionService.getTransactionByMonth(transactionDate);

        assertEquals(1, transactions.size());
        assertEquals(transactionDate.getMonth(), transactions.get(0).getTransactionDate().getMonth());
    }

    @Test
    void getTransactionsByMonth() {

        String month = "APRIL";
//        LocalDate transactionDate = LocalDate.parse("2023-03-24");
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(1L, "Sushi", RESTAURANT, new BigDecimal(54), LocalDate.parse("2023-04-24")));
        transactionList.add(new Transaction(2L, "Crabbys", RESTAURANT, new BigDecimal(23), LocalDate.parse("2023-04-14")));
        transactionList.add(new Transaction(3L, "Internet", UTILITIES, new BigDecimal(124), LocalDate.parse("2023-05-04")));
        transactionList.add(new Transaction(4L, "Phone", UTILITIES, new BigDecimal(30), LocalDate.parse("2023-06-24")));


        when(transactionRepository.findAll()).thenReturn(transactionList);

        List<Transaction> transactions = transactionService.getTransactionsByMonth(month);

        assertEquals(2, transactions.size());
        assertEquals(month, transactions.get(0).getTransactionDate().getMonth().name());
    }

    @Test
    void calculateTransactionsByMonthTest() {

        String month = "MARCH";
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(1L, "Sushi", GROCERIES, new BigDecimal(54), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(2L, "WILD CRAFT", RESTAURANT, new BigDecimal(23), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(3L, "Internet", UTILITIES, new BigDecimal(124), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(4L, "Phone", UTILITIES, new BigDecimal(30), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(5L, "Gas", UTILITIES, new BigDecimal(30), LocalDate.parse("2023-04-04")));

        BigDecimal transactions = calculateService.calculateTransactionsByMonth(transactionList,month);

//        when(transactionRepository.findAll()).thenReturn();
//
////        AmountByCategory transactions = calculateService.calculateByMonth(transactionList, month);
//
//        AmountByCategory transactions = calculateService.calculateByMonth(month);

        assertEquals(new BigDecimal(231), transactions);


    }

    @Test
    void calculateTransactionsMonthlyByCategory(){
        String month = "MARCH";
        TransactionCategory transactionCategory = UTILITIES;

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(1L, "Sushi", GROCERIES, new BigDecimal(54), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(2L, "WILD CRAFT", RESTAURANT, new BigDecimal(23), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(3L, "Internet", UTILITIES, new BigDecimal(124), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(4L, "Phone", UTILITIES, new BigDecimal(30), LocalDate.parse("2023-03-24")));
        transactionList.add(new Transaction(5L, "Gas", UTILITIES, new BigDecimal(30), LocalDate.parse("2023-04-04")));

        BigDecimal res = calculateService.calculateTransactionsMonthlyByCategory(transactionList, transactionCategory, month);

        assertEquals(new BigDecimal(154), res);

    }

//    @Test
//    void calculateMonthlyCategoryAmount(){
//        String month = "MARCH";
//
//        TransactionCategory transactionCategory = UTILITIES;
//
//        List<Transaction> transactionList = new ArrayList<>();
//        transactionList.add(new Transaction(1L, "Sushi", GROCERIES, new BigDecimal(54), LocalDate.parse("2023-03-24")));
//        transactionList.add(new Transaction(2L, "WILD CRAFT", RESTAURANT, new BigDecimal(23), LocalDate.parse("2023-03-24")));
//        transactionList.add(new Transaction(3L, "Internet", UTILITIES, new BigDecimal(124), LocalDate.parse("2023-03-24")));
//        transactionList.add(new Transaction(4L, "Phone", UTILITIES, new BigDecimal(30), LocalDate.parse("2023-03-24")));
//        transactionList.add(new Transaction(5L, "Gas", UTILITIES, new BigDecimal(30), LocalDate.parse("2023-04-04")));
//
//        when(transactionRepository.findAll()).thenReturn(transactionList);
//
//        AmountByCategory res = calculateService.calculateMonthlyCategoryAmount(transactionCategory, month);
//
//        assertEquals(5, res.getTransactions().size());
////        assertEquals(new BigDecimal(154), res.getTotalAmount());
//
//    }



















//
//    @Test
//    void getMonthlyTransactions(){
//        String month = "MARCH";
//        List<Transaction> transactionList = new ArrayList<>();
//        transactionList.add(new Transaction(1L, "Sushi", GROCERIES, new BigDecimal(54), LocalDate.parse("2023-03-24")));
//        transactionList.add(new Transaction(2L, "WILD CRAFT", RESTAURANT, new BigDecimal(23), LocalDate.parse("2023-03-24")));
//        transactionList.add(new Transaction(3L, "Internet", UTILITIES, new BigDecimal(124), LocalDate.parse("2023-03-24")));
//        transactionList.add(new Transaction(4L, "Phone", UTILITIES, new BigDecimal(30), LocalDate.parse("2023-03-24")));
//        transactionList.add(new Transaction(5L, "Gas", UTILITIES, new BigDecimal(30), LocalDate.parse("2023-04-04")));
//
//        when(transactionRepository.findAll()).thenReturn(transactionList);
//
//        TransactionsDto transactionsDto = calculateService.getTransactionsByMonth(transactionList, month);
//
//        assertEquals(5, transactionsDto.getTransactions().size());

//
//    }

}