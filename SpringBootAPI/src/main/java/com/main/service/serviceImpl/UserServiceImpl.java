package com.main.service.serviceImpl;
//The UserServiceImpl class processes the business logic for user operations. It interacts with the repository layer

import com.main.dto.BalanceDto;
import com.main.dto.FixedDepositDto;
import com.main.dto.TransactionDto;
import com.main.dto.UserDto;
import com.main.exception.UserAccountException;
import com.main.model.FixedDeposit;
import com.main.model.UserAccount;
import com.main.repository.BalanceRepository;
import com.main.repository.UserRepository;
import com.main.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BalanceRepository balanceRepository;

    @Override
    public UserAccount createUser(UserDto userDto) //for creating new user
    {
      UserAccount userAccount1 =  userRepository.findByAccountNo(userDto.getAccountNo());
      if(userAccount1 != null)
      {
          throw  new UserAccountException("Already Account exist with this Account number : "+userDto.getAccountNo(), HttpStatus.BAD_REQUEST );
      }
        UserAccount userAccount = new UserAccount();
        userAccount.setId(userDto.getId());
        userAccount.setAccountNo(userDto.getAccountNo());
        userAccount.setAccountHolderName(userDto.getAccountHolderName());
        userAccount.setAddress(userDto.getAddress());
        userAccount.setCity(userDto.getCity());
        userAccount.setPincode(userDto.getPincode());
        userAccount.setPhoneNumber(userDto.getPhoneNumber());
        userAccount.setAccountType(userDto.getAccountType());
        userAccount.setBalance(userDto.getBalance());

        return userRepository.save(userAccount); //method is called to save the user in the database.
    }

    @Override
    public List<UserAccount> getAllUser() //for showing all users in the bank
    {
        return userRepository.findAll();
    }

    @Override
    public UserDto getUser(long accountNo) //for showing a single user through account no
    {

      UserAccount userAccount =  userRepository.findByAccountNo(accountNo);
        if(Objects.isNull(userAccount))
        {
            throw  new UserAccountException("Account does not exist  : "+ accountNo, HttpStatus.BAD_REQUEST );
        }
        UserDto userDto= new UserDto();
        userDto.setId(userAccount.getId());
        userDto.setAccountNo(userAccount.getAccountNo());
        userDto.setAccountHolderName(userAccount.getAccountHolderName());
        userDto.setAddress(userAccount.getAddress());
        userDto.setCity(userAccount.getCity());
        userDto.setPincode(userAccount.getPincode());
        userDto.setPhoneNumber(userAccount.getPhoneNumber());
        userDto.setAccountType(userAccount.getAccountType());
        userDto.setBalance(userAccount.getBalance());

        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) //for updating user account
    {
        try {
            UserAccount userAccount2 = userRepository.findByAccountNo(userDto.getAccountNo());
            if (userAccount2 == null) {
                throw new UserAccountException(
                        "No account found with account number: " + userDto.getAccountNo(),
                        HttpStatus.NOT_FOUND
                );
            }

            // Update fields

            userAccount2.setAccountNo(userDto.getAccountNo());
            userAccount2.setAccountHolderName(userDto.getAccountHolderName());
            userAccount2.setAddress(userDto.getAddress());
            userAccount2.setCity(userDto.getCity());
            userAccount2.setPincode(userDto.getPincode());
            userAccount2.setPhoneNumber(userDto.getPhoneNumber());
            userAccount2.setAccountType(userDto.getAccountType());
            userAccount2.setBalance(userDto.getBalance());

            // Save updated user account
            UserAccount userAccount1 = userRepository.save(userAccount2);

            // Map to UserDto
            UserDto userDto1 = new UserDto();

            userDto1.setAccountNo(userAccount1.getAccountNo());
            userDto1.setAccountHolderName(userAccount1.getAccountHolderName());
            userDto1.setAddress(userAccount1.getAddress());
            userDto1.setCity(userAccount1.getCity());
            userDto1.setPincode(userAccount1.getPincode());
            userDto1.setPhoneNumber(userAccount1.getPhoneNumber());
            userDto1.setAccountType(userAccount1.getAccountType());
            userDto1.setBalance(userAccount1.getBalance());

            return userDto1;
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            throw e; // Re-throw the exception to be handled by the exception handler
        }

    }

    @Override
    public void  deleteUser(long accountNo)//for deleting user account
    {
        UserAccount userAccount =  userRepository.findByAccountNo(accountNo);
        if(userAccount == null)
        {
            throw  new UserAccountException("Account does not exist  : "+ accountNo, HttpStatus.BAD_REQUEST );
        }else {
            userRepository.delete(userAccount);
        }
    }

    @Override
    public BalanceDto getBalance(long accountNo) //for checking the balance in the account
    {
        UserAccount userAccount =  userRepository.findByAccountNo(accountNo);
        if(userAccount == null)
        {
            throw  new UserAccountException("Account does not exist  : "+ accountNo, HttpStatus.BAD_REQUEST );
        }
        BalanceDto balanceDto = new BalanceDto();
        balanceDto.setAccountNo(userAccount.getAccountNo());
        balanceDto.setAccountHolderName(userAccount.getAccountHolderName());
        balanceDto.setBalance(userAccount.getBalance());

        return balanceDto;
    }

    @Override
    public void doTransaction(TransactionDto transactionDto) //for doing the transaction from one account to another account
    {
        log.info("enter in method doTransaction");

       UserAccount fromUser = userRepository.findByAccountNo(transactionDto.getFromAccount());
       if(fromUser.getBalance() <= transactionDto.getAmount())
       {
           throw new UserAccountException("Insufficient Balance : "+ fromUser.getAccountNo(), HttpStatus.BAD_REQUEST);
       }
       UserAccount toUser = userRepository.findByAccountNo((transactionDto.getToAccount()));

       fromUser.setBalance(fromUser.getBalance()-transactionDto.getAmount());
       userRepository.save(fromUser);
       double toBalance = toUser.getBalance()+transactionDto.getAmount();
       toUser.setBalance(toBalance);
       userRepository.save(toUser);

    }

    @Override
    public FixedDeposit doFixedDeposit(FixedDepositDto fixedDepositDto) //for doing fixed deposit
    {
        FixedDeposit fixedDeposit = new FixedDeposit();
        fixedDeposit.setUserAccount(userRepository.findByAccountNo(fixedDepositDto.getAccountNo()));
        fixedDeposit.setAccountNo(fixedDepositDto.getAccountNo());
        fixedDeposit.setDuration(fixedDepositDto.getDuration());
        fixedDeposit.setInterest(fixedDepositDto.getInterest());
        fixedDeposit.setAmount(fixedDepositDto.getAmount());
        double simpleInterest=((fixedDepositDto.getAmount() * fixedDeposit.getDuration() * fixedDeposit.getInterest()/(100)));
        double TotAmount=((fixedDepositDto.getAmount() + simpleInterest));
        fixedDeposit.setTotalAmount(TotAmount);

        return balanceRepository.save(fixedDeposit);
    }

    @Override
    public FixedDepositDto getFixedDeposit(long accountNo)//for showing the details of fixed deposit
    {
        FixedDeposit fixedDeposit1 = balanceRepository.findByAccountNo(accountNo);

        FixedDepositDto fixedDepositDto = new FixedDepositDto();

        fixedDepositDto.setAccountNo(fixedDeposit1.getUserAccount().getAccountNo());
        fixedDepositDto.setDuration(fixedDeposit1.getDuration());
        fixedDepositDto.setInterest(fixedDeposit1.getInterest());
        fixedDepositDto.setAmount(fixedDeposit1.getAmount());

        return fixedDepositDto;
    }

    @Override
    public List<FixedDeposit> getAllFD(long accountNo)
    {
        return balanceRepository.findAllByAccountNo(accountNo);
    }


}
