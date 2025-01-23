package com.main.repository;

import com.main.model.FixedDeposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<FixedDeposit, Long> {
     FixedDeposit findByAccountNo(long accountNo);
}
