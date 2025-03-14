package com.main.service;

import com.main.dto.BalanceDto;
import com.main.dto.FixedDepositDto;
import com.main.dto.TransactionDto;
import com.main.dto.UserDto;
import com.main.entity.FixedDeposit;
import com.main.entity.UserAccount;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    public UserAccount createUser(UserDto userDto);
    public List<UserAccount> getAllUser();
    public UserDto getUser(long accountNo);
    public UserDto updateUser(UserDto userDto);
    public void deleteUser(long accountNo);
    public BalanceDto getBalance(long accountNo);
    public void doTransaction(TransactionDto transactionDto);
    public FixedDeposit doFixedDeposit(FixedDepositDto fixedDepositDto);
    public FixedDepositDto getFixedDeposit(long accountNo);
    public List<FixedDeposit> getAllFD(long accountNo);
    Page<UserAccount> getUserAccounts(int page, int size);
}
