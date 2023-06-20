package com.example.expensetracker.dto;
/**
 * @author Cecilia Yang
 */

import com.example.expensetracker.entity.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class TransactionsDto {
    private BigDecimal totalAmount;
    private List<Transaction> transactions;
}
