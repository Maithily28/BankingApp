import com.main.constant.AccountType;
import com.main.dto.FixedDepositDto;
import com.main.dto.TransactionDto;
import com.main.dto.UserDto;
import com.main.model.FixedDeposit;
import com.main.model.UserAccount;
import com.main.repository.BalanceRepository;
import com.main.repository.UserRepository;
import com.main.service.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private BalanceRepository balanceRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl; //This means userServiceImpl will use the mocked version of UserRepository


    @Test
    void createUserTest1() {
        UserDto userDto = new UserDto();
        userDto.setCity("Nagpur");
        userDto.setAddress("Om nagar");
        userDto.setPincode(440035);
        userDto.setAccountType(AccountType.SAVING_ACCOUNT);
        userDto.setBalance(2345);
        userDto.setAccountHolderName("Jay");

        UserAccount userAccount = new UserAccount();
        userAccount.setId(1);
        userAccount.setAccountNo(9876);
        userAccount.setAccountHolderName("Kiran");
        userAccount.setAddress("Om nagar");
        userAccount.setCity("Pune");
        userAccount.setPincode(440098);
        userAccount.setPhoneNumber("755869524");
        userAccount.setAccountType(AccountType.SAVING_ACCOUNT);
        userAccount.setBalance(5000);

        Mockito.when(userRepository.findByAccountNo(Mockito.anyLong())).thenReturn(null);
        Mockito.when(userRepository.save(Mockito.any(UserAccount.class))).thenReturn(userAccount);
        Assertions.assertDoesNotThrow(() -> userServiceImpl.createUser(userDto));

    }

    @Test
    void getAllUserTest() {
        ArrayList<UserAccount> list = new ArrayList<>();
        UserAccount userAccount = new UserAccount();
        userAccount.setId(2);
        list.add(userAccount);
        Mockito.when(userRepository.findAll()).thenReturn(list);
        Assertions.assertDoesNotThrow(() -> userServiceImpl.getAllUser());
    }

    @Test
    void getUserTest() {
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountNo(12350);
        Mockito.when(userRepository.findByAccountNo(Mockito.anyLong())).thenReturn(userAccount);
        Assertions.assertDoesNotThrow(() -> userServiceImpl.getUser(userAccount.getAccountNo()));

    }


    @Test
    void updateUserTest() {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setAccountHolderName("Diya");
        userDto.setCity("Turkmenistan");

        UserAccount userAccount = new UserAccount();
        userAccount.setId(2);
        userAccount.setAccountHolderName("Ayushi");
        userAccount.setCity("Warora");

        Mockito.when(userRepository.findByAccountNo(Mockito.anyLong())).thenReturn(userAccount);
        Mockito.when(userRepository.save(Mockito.any(UserAccount.class))).thenReturn(userAccount);
        Assertions.assertDoesNotThrow(() -> userServiceImpl.updateUser(userDto));
    }

    @Test
    void updateUserTest2() {
        UserDto userDto = new UserDto();
        Mockito.when(userRepository.findByAccountNo(Mockito.anyLong())).thenReturn(null);
        Assertions.assertThrows(RuntimeException.class, () -> userServiceImpl.updateUser(userDto));
    }

    @Test
    void deleteUserTest() {
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountNo(8852);
        Mockito.when(userRepository.findByAccountNo(Mockito.anyLong())).thenReturn(userAccount);
        Mockito.doNothing().when(userRepository).delete(Mockito.any(UserAccount.class));

        userServiceImpl.deleteUser(8852);

    }

    @Test
    void getBalanceTest()
    {
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountNo(12350);
        userAccount.setAccountHolderName("Maya");
        userAccount.setBalance(2000);
        Mockito.when(userRepository.findByAccountNo(Mockito.anyLong())).thenReturn(userAccount);
        Assertions.assertDoesNotThrow(() -> userServiceImpl.getBalance(userAccount.getAccountNo()));

    }

    @Test
    void getBalanceTest2() {
        UserAccount userAccount = new UserAccount();
        Mockito.when(userRepository.findByAccountNo(Mockito.anyLong())).thenReturn(null);
        Assertions.assertThrows(RuntimeException.class, () -> userServiceImpl.getBalance(userAccount.getAccountNo()));
    }

    @Test
    void doTransactionTest()
    {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setFromAccount(1212);
        transactionDto.setToAccount(1515);
        transactionDto.setAmount(500);

        UserAccount userAccount = new UserAccount();
        userAccount.setBalance(5000);

        Mockito.when(userRepository.findByAccountNo(transactionDto.getFromAccount())).thenReturn(userAccount);
        Mockito.when(userRepository.findByAccountNo(transactionDto.getToAccount())).thenReturn(userAccount);
        Assertions.assertDoesNotThrow(() -> userServiceImpl.doTransaction(transactionDto));

    }

    @Test
    void doTransactionTest2()
    {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setFromAccount(1212);
        transactionDto.setToAccount(1515);
        transactionDto.setAmount(500);

        UserAccount userAccount = new UserAccount();

        Mockito.when(userRepository.findByAccountNo(transactionDto.getFromAccount())).thenReturn(userAccount);
        Assertions.assertThrows(RuntimeException.class, () -> userServiceImpl.doTransaction(transactionDto));
    }

    @Test
    void doFixedDeposit()
    {
        FixedDeposit fixedDeposit = new FixedDeposit();
        fixedDeposit.setAccountNo(1215);
        fixedDeposit.setInterest(5);
        fixedDeposit.setDuration(10);

        FixedDepositDto fixedDepositDto = new FixedDepositDto();

        Mockito.when(balanceRepository.save(Mockito.any(FixedDeposit.class))).thenReturn(fixedDeposit);
        Assertions.assertDoesNotThrow( () -> userServiceImpl.doFixedDeposit(fixedDepositDto));
    }

    @Test
    void getFixedDepositTest()
    {
        FixedDeposit fixedDeposit = new FixedDeposit();
        fixedDeposit.setAccountNo(12350);
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountNo(12350);
        fixedDeposit.setUserAccount(userAccount);

        FixedDepositDto fixedDepositDto = new FixedDepositDto();
        fixedDepositDto.setAccountNo(12350);

        Mockito.when(balanceRepository.findByAccountNo(Mockito.anyLong())).thenReturn(fixedDeposit);
        Assertions.assertDoesNotThrow(() -> userServiceImpl.getFixedDeposit(fixedDeposit.getAccountNo()));
    }

}
