package com.example.expensetracker.dto;
/**
 * @author Cecilia Yang
 */
import com.example.expensetracker.entity.TransactionCategory;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Entity
public class TransactionDto {

    @Id
    private Long transactionId;

    @NotNull
    private String transactionName;

    @NotNull
    private TransactionCategory transactionCategory;

    @NotNull
    private BigDecimal transactionAmount;

    @NotNull
    private String transactionDate;

    private boolean isNecessary = true ;

}
