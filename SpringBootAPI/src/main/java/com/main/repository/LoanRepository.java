package com.main.repository;

import com.main.model.FixedDeposit;
import com.main.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long>
{
    Loan findByAccountNo(long accountNo);
}
