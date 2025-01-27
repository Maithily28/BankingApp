package com.main.service.serviceImpl;

import com.main.dto.LoanDto;
import com.main.dto.LoanPaymentDto;
import com.main.exception.UserAccountException;
import com.main.model.Loan;
import com.main.model.UserAccount;
import com.main.repository.LoanRepository;
import com.main.repository.UserRepository;
import com.main.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LoanServiceImpl implements LoanService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Loan createLoan(LoanDto loanDto) //get loan from the bank
    {
        Loan loan1 =  loanRepository.findByAccountNo(loanDto.getAccountNo());
        if(loan1 != null)
        {
            throw  new UserAccountException("Sorry!, One User can only get one loan : "+loanDto.getAccountNo(), HttpStatus.BAD_REQUEST );
        }

        Loan loan = new Loan();
        loan.setUserAccount(userRepository.findByAccountNo(loanDto.getAccountNo()));
        loan.setAccountNo(loanDto.getAccountNo());
        loan.setLoanType(loanDto.getLoanType());
        loan.setLoanAmount(loanDto.getLoanAmount());
        loan.setInterestRate(loanDto.getInterestRate());
        loan.setLoanTerm(loanDto.getLoanTerm());

        double simpleInterest = ((loanDto.getLoanAmount() * loanDto.getInterestRate() * loanDto.getLoanTerm()) / (100.0));
        double totAmt = (loanDto.getLoanAmount() + simpleInterest);
        loan.setTotalAmount(totAmt);

        double monthlyPayment = (totAmt/ ((double)loanDto.getLoanTerm()*12));
        loan.setMonthlyPay(monthlyPayment);

        loan.setLoanAmountPaid(0.0);
        loan.setLoanAmountRemaining(loan.getTotalAmount()-loan.getLoanAmountPaid());

        return loanRepository.save(loan);
    }

    @Override
    public List<Loan> getAllLoans() //showing the details of all loans in the bank
    {
        return loanRepository.findAll();
    }

    @Override
    public LoanDto getLoanDetails(long accountNo) //showing loan details of single user
    {
        Loan loan1 =  loanRepository.findByAccountNo(accountNo);
        if(Objects.isNull(loan1))
        {
            throw  new UserAccountException("Account does not exist : "+accountNo, HttpStatus.BAD_REQUEST );
        }

        LoanDto loanDto = new LoanDto();
        loanDto.setId(loan1.getLoanId());
        loanDto.setAccountNo(loan1.getAccountNo());
        loanDto.setLoanType(loan1.getLoanType());
        loanDto.setLoanAmount(loan1.getLoanAmount());
        loanDto.setInterestRate(loan1.getInterestRate());
        loanDto.setLoanTerm(loan1.getLoanTerm());

        return loanDto;
    }

    @Override
    public LoanDto updateLoan(LoanDto loanDto)//updating loan details
    {
        Loan loan1 =  loanRepository.findByAccountNo(loanDto.getAccountNo());
        if(Objects.isNull(loan1))
        {
            throw  new UserAccountException("Account does not exist : "+loanDto.getAccountNo(), HttpStatus.BAD_REQUEST );
        }

        loan1.setAccountNo(loanDto.getAccountNo());
        loan1.setLoanType(loanDto.getLoanType());
        loan1.setLoanAmount(loanDto.getLoanAmount());
        loan1.setInterestRate(loanDto.getInterestRate());
        loan1.setLoanTerm(loanDto.getLoanTerm());

        Loan loan2 = loanRepository.save(loan1);

        LoanDto loanDto1 = new LoanDto();
        loanDto1.setAccountNo(loan2.getAccountNo());
        loanDto1.setLoanType(loan2.getLoanType());
        loanDto1.setLoanAmount(loan2.getLoanAmount());
        loanDto1.setInterestRate(loan2.getInterestRate());
        loanDto1.setLoanTerm(loan2.getLoanTerm());

        return loanDto1;
    }

    @Override
    public void deleteLoan(long accountNo) //delete loan
    {
        Loan loan = loanRepository.findByAccountNo(accountNo);
        if(Objects.isNull(loan))
        {
            throw  new UserAccountException("Loan does not exist on this account: "+accountNo, HttpStatus.BAD_REQUEST );
        }else {
            loanRepository.delete(loan);
        }
    }

    @Override
    public void payLoan(LoanPaymentDto loanPaymentDto)
    {
        Loan loan = loanRepository.findByAccountNo(loanPaymentDto.getAccountNo());
        if(Objects.isNull(loan))
        {
            throw  new UserAccountException("Loan does not exist on this account: "+loanPaymentDto.getAccountNo(), HttpStatus.BAD_REQUEST );
        }
        if(loanPaymentDto.getPayingAmount() == loan.getMonthlyPay())
        {
            loan.setLoanAmountPaid(loan.getLoanAmountPaid() + loanPaymentDto.getPayingAmount());
            loan.setLoanAmountRemaining(loan.getLoanAmountRemaining() - loanPaymentDto.getPayingAmount());
        }

        loanRepository.save(loan);

    }
}
