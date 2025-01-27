package com.main.service;

import com.main.dto.LoanDto;
import com.main.dto.UserDto;
import com.main.model.Loan;
import com.main.model.UserAccount;

import java.util.List;

public interface LoanService
{
    public Loan createLoan(LoanDto loanDto);
    public List<Loan> getAllLoans();
    public LoanDto getLoanDetails (long accountNo);
    public LoanDto updateLoan(LoanDto loanDto);
    public void deleteLoan(long accountNo);

}
