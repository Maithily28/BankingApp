package com.main.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Loan")
public class Loan
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long loanId;

    @Column(name = "AccountNo")
    private long accountNo;

    @Column(name = "loanType")
    private String loanType;

    @Column(name = "loanAmount")
    private Double loanAmount;

    @Column(name = "interestRate")
    private Double interestRate;

    @Column(name = "loanTerm")
    private Integer loanTerm;

    @Column(name = "totalAmount")
    private double totalAmount;

    @Column(name = "monthlyPay")
    private double monthlyPay;

    @Column(name = "loanAmountPaid")
    private double loanAmountPaid;

    @Column(name = "loanAmountRemaining")
    private double loanAmountRemaining;

    @OneToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

}
