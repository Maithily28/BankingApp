package com.main.dto;

import com.main.constant.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private long id;
    private long accountNo;
    private String accountHolderName;
    private String address;
    private String city;
    private int pincode;
    private String phoneNumber;
    private AccountType accountType;
    private double balance;
}
