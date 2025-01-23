package com.main.controller;
//UserController is the entry point for handling API requests.

import com.main.dto.BalanceDto;
import com.main.dto.FixedDepositDto;
import com.main.dto.TransactionDto;
import com.main.dto.UserDto;
import com.main.model.FixedDeposit;
import com.main.model.UserAccount;
import com.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

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
}
