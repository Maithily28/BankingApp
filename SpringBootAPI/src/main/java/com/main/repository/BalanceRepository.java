package com.main.repository;

import com.main.entity.FixedDeposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BalanceRepository extends JpaRepository<FixedDeposit, Long> {
     FixedDeposit findByAccountNo(long accountNo);

     List<FixedDeposit> findAllByAccountNo(long accountNo);
}
