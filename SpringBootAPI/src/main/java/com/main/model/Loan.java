package com.main.model;

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

    @Column(name = "interstRate")
    private Double interestRate;

    @Column(name = "loanTerm")
    private Integer loanTerm;

    @OneToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

}
