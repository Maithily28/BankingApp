package com.main.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FixedDeposit")
public class FixedDeposit
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "AccountNo")
    private long accountNo;

    @Column(name = "Duration")
    private double duration;

    @Column(name = "Interest")
    private double interest;

    @Column(name = "Amount")
    private double amount;

    @Column(name = "TotalAmount")
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

}
