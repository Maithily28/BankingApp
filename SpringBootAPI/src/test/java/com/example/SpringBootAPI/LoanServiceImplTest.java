package com.example.SpringBootAPI;

import com.main.dto.LoanDto;
import com.main.dto.LoanPaymentDto;
import com.main.entity.Loan;
import com.main.repository.LoanRepository;
import com.main.repository.UserRepository;
import com.main.service.serviceImpl.LoanServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class LoanServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanServiceImpl loanServiceImpl;

    @Test
    void createLoanTest()
    {
        LoanDto loanDto = new LoanDto();
        loanDto.setLoanType("Home Loan");
        loanDto.setLoanAmount(10000D);
        loanDto.setLoanTerm(5);
        loanDto.setId(5);
        loanDto.setInterestRate(5.5);

        Loan loan = new Loan();
        loan.setLoanAmount(200.0);
        loan.setLoanId(5);
        loan.setLoanType("Home Loan");
        loan.setInterestRate(5.5);

        Mockito.when(loanRepository.findByAccountNo(Mockito.anyLong())).thenReturn(null);
        Mockito.when(loanRepository.save(Mockito.any(Loan.class))).thenReturn(loan);
        Assertions.assertDoesNotThrow(() -> loanServiceImpl.createLoan(loanDto));
    }

    @Test
    void getAllLoansTest()
    {
        ArrayList<Loan> list = new  ArrayList<>();
        Loan loan = new Loan();
        loan.setLoanId(2);
        list.add(loan);
        Mockito.when(loanRepository.findAll()).thenReturn(list);
        Assertions.assertDoesNotThrow(() -> loanServiceImpl.getAllLoans());
    }

    @Test
    void getLoanDetailsTest()
    {
        Loan loan = new Loan();
        loan.setLoanId(5);
        Mockito.when(loanRepository.findByAccountNo(Mockito.anyLong())).thenReturn(loan);
        Assertions.assertDoesNotThrow(()-> loanServiceImpl.getLoanDetails(loan.getAccountNo()));

    }

    @Test
    void updateLoanTest()
    {
        LoanDto loanDto = new LoanDto();
        loanDto.setLoanType("Home Loan");
        loanDto.setLoanAmount(10000D);
        loanDto.setLoanTerm(5);
        loanDto.setId(5);
        loanDto.setInterestRate(5.5);

        Loan loan = new Loan();
        loan.setLoanAmount(200.0);
        loan.setLoanId(5);
        loan.setLoanType("Home Loan");
        loan.setInterestRate(5.5);

        Mockito.when(loanRepository.findByAccountNo(Mockito.anyLong())).thenReturn(loan);
        Mockito.when(loanRepository.save(Mockito.any(Loan.class))).thenReturn(loan);
        Assertions.assertDoesNotThrow(() -> loanServiceImpl.updateLoan(loanDto));
    }

    @Test
    void updateLoanTest2() {
        LoanDto loanDto = new LoanDto();
        Mockito.when(loanRepository.findByAccountNo(Mockito.anyLong())).thenReturn(null);
        Assertions.assertThrows(RuntimeException.class, () ->loanServiceImpl.updateLoan(loanDto));
    }

    @Test
    void deleteLoan()
    {
        Loan loan = new Loan();
        loan.setLoanId(5);
        Mockito.when(loanRepository.findByAccountNo(Mockito.anyLong())).thenReturn(loan);
        Mockito.doNothing().when(loanRepository).delete(Mockito.any(Loan.class));
        loanServiceImpl.deleteLoan(2205);
    }

    @Test
    void payLoanTest()
    {
        LoanPaymentDto loanPaymentDto = new LoanPaymentDto();
        loanPaymentDto.setAccountNo(1212);
        loanPaymentDto.setPayingAmount(500);

        Loan loan = new Loan();
        loan.setLoanId(5);
        loan.setMonthlyPay(500);

        Mockito.when(loanRepository.findByAccountNo(loanPaymentDto.getAccountNo())).thenReturn(loan);
        Mockito.when(loanRepository.save(Mockito.any(Loan.class))).thenReturn(loan);
        Assertions.assertDoesNotThrow(() -> loanServiceImpl.payLoan(loanPaymentDto));
    }
}
