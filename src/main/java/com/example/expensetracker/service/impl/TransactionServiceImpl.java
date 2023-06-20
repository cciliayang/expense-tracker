package com.example.expensetracker.service.impl;
/**
 * @author Cecilia Yang
 */
import com.example.expensetracker.entity.Transaction;
import com.example.expensetracker.exception.DataNotFoundException;
import com.example.expensetracker.repository.TransactionRepository;
import com.example.expensetracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction, Long transactionId) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);

        if (transactionOptional.isPresent()) {
            transactionOptional.get().setTransactionName(transaction.getTransactionName());
            transactionOptional.get().setTransactionCategory(transaction.getTransactionCategory());
            transactionOptional.get().setTransactionAmount(transaction.getTransactionAmount());
            transactionOptional.get().setTransactionDate(transaction.getTransactionDate());
            return transactionRepository.save(transactionOptional.get());
        }
        throw new DataNotFoundException("Transaction Id not found");
    }

    @Override
    public Transaction findTransactionById(Long id) {
        Optional<Transaction> findTransaction = transactionRepository.findById(id);

        return findTransaction.get();
    }

    @Override
    public List<Transaction> getAllTransactions(Transaction transaction) {

        List<Transaction> findAllTransaction = transactionRepository.findAll();

        List<Transaction> allTransactionList = new ArrayList<>();

        if (findAllTransaction != null) {

            for (int i = 0; i < findAllTransaction.size(); i++) {
                Transaction aTransaction = new Transaction();
                aTransaction.setTransactionId(findAllTransaction.get(i).getTransactionId());
                aTransaction.setTransactionName(findAllTransaction.get(i).getTransactionName());
                aTransaction.setTransactionCategory(findAllTransaction.get(i).getTransactionCategory());
                aTransaction.setTransactionAmount(findAllTransaction.get(i).getTransactionAmount());
                aTransaction.setTransactionDate(findAllTransaction.get(i).getTransactionDate());
                allTransactionList.add(aTransaction);
            }

            System.out.println("List of all transactions: " + allTransactionList.toString());
            return allTransactionList;
        }
        return allTransactionList;
    }

    @Override
    public List<Transaction> getTransactionByMonth(LocalDate transactionDate) {
        List<Transaction> result = new ArrayList<Transaction>();
        for (Transaction transaction : transactionRepository.findAll()) {
            if (transaction.getTransactionDate().getMonth().equals(transactionDate.getMonth())) {
                result.add(transaction);
            }
        }
        System.out.println(result);
        return result;
    }

    @Override
    public List<Transaction> getTransactionsByMonth(String month) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactionRepository.findAll()) {
            if (month.equalsIgnoreCase(transaction.getTransactionDate().getMonth().name())) {
                result.add(transaction);
            }
        }
        return result;
    }
}

//    @Override
//    public List<Transaction> getTransactionByMonth(LocalDate transactionDate) {
//        List <Transaction> findByMonth = transactionRepository.findByMonth(transactionDate);
//
//        List<Transaction> findMonthTransactionList = new ArrayList<>();
//
//        for (Transaction transaction : findByMonth ){
//            if (transaction.getTransactionDate().getMonth().equals(findByMonth.get(0).getTransactionDate().getMonth())){
//                findMonthTransactionList.add(transaction);
//            }
//        }
//
//        return findMonthTransactionList;
//    }

//    @Override
//    public Map<TransactionCategory, List<Transaction>> findTransactionsByCategory(List<Transaction> transactions) {
//        List<Transaction> entertainmentList = new ArrayList<>();
//        List<Transaction> utilitiesList = new ArrayList<>();
//        List<Transaction> groceriesList = new ArrayList<>();
//        List<Transaction> miscanellousList = new ArrayList<>();
//        List<Transaction> restaurantList = new ArrayList<>();
//
//        Map<TransactionCategory, List<Transaction>> map = new HashMap<>();
//
//        for (Transaction transaction : transactions) {
//            if (transaction.getTransactionCategory().equals(ENTERTAINMENT)) {
//                entertainmentList.add(transaction);
//            } else if (transaction.getTransactionCategory().equals(UTILITIES)) {
//                utilitiesList.add(transaction);
//            } else if (transaction.getTransactionCategory().equals(GROCERIES)) {
//                groceriesList.add(transaction);
//            } else if (transaction.getTransactionCategory().equals(RESTAURANT)) {
//                restaurantList.add(transaction);
//            } else if (transaction.getTransactionCategory().equals(MISCELLANEOUS)) {
//                miscanellousList.add(transaction);
//            }
//        }
//        map.put(ENTERTAINMENT, entertainmentList);
//        map.put(UTILITIES, utilitiesList);
//        map.put(GROCERIES, groceriesList);
//        map.put(RESTAURANT, restaurantList);
//        map.put(MISCELLANEOUS, miscanellousList);


//        System.out.println(map.get(ENTERTAINMENT));
//        System.out.println(map.get(RESTAURANT));

//        return map;
//    }


//}





