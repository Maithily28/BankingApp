package com.main.model;

import com.main.constant.AccountType;
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
@Table(name = "UserAccount")
public class UserAccount
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "accountNumber")
    private long accountNo;

    @Column(name = "accountHolderName")
    private String accountHolderName;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "pincode")
    private int pincode;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "balance")
    private double balance;

}
