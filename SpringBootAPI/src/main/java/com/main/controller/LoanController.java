package com.main.controller;

import com.main.dto.FixedDepositDto;
import com.main.dto.LoanDto;
import com.main.dto.UserDto;
import com.main.model.FixedDeposit;
import com.main.model.Loan;
import com.main.model.UserAccount;
import com.main.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoanController
{
    @Autowired
    private LoanService loanService;

    @PostMapping("/getNewLoan")
    public Loan loan(@RequestBody LoanDto loanDto) //Maps the HTTP request body to a Java object
    {
        Loan loan = loanService.createLoan(loanDto);
        return loan;
    }

    @GetMapping("/getAllLoans")
    public List<Loan> getAllLoans()
    {
        return loanService.getAllLoans();
    }

    @GetMapping("/getLoanDetails/{accountNo}")
    public ResponseEntity<LoanDto> getUSer(@PathVariable long accountNo) {
        LoanDto loanDto = loanService.getLoanDetails(accountNo);

        if (loanDto != null) {
            return ResponseEntity.ok().body(loanDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateLoan")
    public ResponseEntity<LoanDto> updateLoan(@RequestBody LoanDto loanDto)
    {
        return ResponseEntity.ok(loanService.updateLoan(loanDto));

    }

    @DeleteMapping("/deleteLoan/{accountNo}")
    public ResponseEntity<?> deleteLoan(@PathVariable long accountNo)
    {
        loanService.deleteLoan(accountNo);
        return ResponseEntity.ok().body("Loan Deleted Successfully....");
    }
}
