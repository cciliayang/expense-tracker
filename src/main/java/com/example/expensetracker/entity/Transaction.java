package com.example.expensetracker.entity;
/**
 * @author Cecilia Yang
 */
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "expense_tracker")
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name = "transactionName")
    private String transactionName;

    @Column(name = "transactionCategory")
    private TransactionCategory transactionCategory;

    @Column(name = "transactionAmount")
    private BigDecimal transactionAmount;

    @Column(name = "transactionDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate transactionDate;
}
