package com.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FixedDepositDto
{
    private long accountNo;
    private double duration;
    private double interest;
    private double amount;

}
