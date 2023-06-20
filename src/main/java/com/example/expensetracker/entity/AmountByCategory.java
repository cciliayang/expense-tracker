package com.example.expensetracker.entity;
/**
 * @author Cecilia Yang
 */

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class AmountByCategory {
    private BigDecimal totalAmount;
    private List<Transaction> transactions;

}
