package com.main.controller;
//UserController is the entry point for handling API requests.

import com.main.dto.BalanceDto;
import com.main.dto.FixedDepositDto;
import com.main.dto.TransactionDto;
import com.main.dto.UserDto;
import com.main.entity.FixedDeposit;
import com.main.entity.UserAccount;
import com.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user-accounts")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Page<UserAccount> getSortedUserAccounts(@RequestParam int page,
                                                   @RequestParam int size,
                                                   @RequestParam(defaultValue = "id") String sortBy,
                                                   @RequestParam(defaultValue = "true") boolean ascending)
    {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return userService.getUserAccounts(page, size);
    }


    @PostMapping("/adduser")
    public ResponseEntity<UserAccount> createUser(@RequestBody UserDto userDTO) //Maps the HTTP request body to a Java object (UserDto)
    {
        return ResponseEntity.ok(userService.createUser(userDTO));

    }

    @GetMapping("/getAllUsers")
    public List<UserAccount> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/getUser/{accountNumber}")
    public ResponseEntity<UserDto> getUSer(@PathVariable long accountNumber) {
        UserDto userDto = userService.getUser(accountNumber);

        if (userDto != null) {
            return ResponseEntity.ok().body(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userDto));

    }

    @DeleteMapping("/deleteUser/{accountNo}")
    public ResponseEntity<?> deleteUser(@PathVariable long accountNo) {
        userService.deleteUser(accountNo);
        return ResponseEntity.ok().body("User Account Deleted Successfully....");

    }

    @GetMapping("/getBalance/{accountNo}")
    public ResponseEntity<BalanceDto> getBalance(@PathVariable long accountNo) {

        return ResponseEntity.ok().body(userService.getBalance(accountNo));

    }

    @PostMapping("/doTransaction")
    public ResponseEntity<String>  doTransaction(@RequestBody TransactionDto transactionDto)
    {
        userService.doTransaction(transactionDto);
        return  ResponseEntity.ok("Balance Transfer from "+transactionDto.getFromAccount()+" -->  to "+transactionDto.getToAccount()+" Successfully..!");
    }

    @PostMapping("/doFixedDeposit")
    public FixedDeposit doFixedDeposit(@RequestBody FixedDepositDto fixedDepositDto) //Maps the HTTP request body to a Java object
    {
        FixedDeposit fixedDeposit = userService.doFixedDeposit(fixedDepositDto);
        return fixedDeposit;
    }

    @GetMapping("/getFD/{accountNo}")
    public ResponseEntity<FixedDepositDto> getFixedDeposit(@PathVariable long accountNo) {
        FixedDepositDto fixedDepositDto = userService.getFixedDeposit(accountNo);

        if (fixedDepositDto != null) {
            return ResponseEntity.ok().body(fixedDepositDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllFD/{accountNo}")
    public List<FixedDeposit> getAllFD(@PathVariable long accountNo)
    {
        return userService.getAllFD(accountNo);
    }
}
