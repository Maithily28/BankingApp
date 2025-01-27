package com.main.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanDto
{
    private long id;
    private long accountNo;
    private String loanType;
    private Double loanAmount;
    private Double interestRate;
    private Integer loanTerm;
    private Double totalAmount;
    private Double monthlyPay;
}
