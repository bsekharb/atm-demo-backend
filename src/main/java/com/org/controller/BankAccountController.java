package com.org.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.org.entity.BankAccount;
import com.org.exception.InvalidPinException;
import com.org.model.ResponseWrapper;
import com.org.service.IBankAccountService;


/**
 * @author Raj
 * @since 02-07-2022
 * 
 */

@RestController
@RequestMapping("/api")
public class BankAccountController {

    @Autowired
    private IBankAccountService bankAccountService;

    @PostMapping("/init-bankaccount")
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccount bankAccount) {
        return new ResponseEntity<>(bankAccountService.createBankAccount(bankAccount), HttpStatus.OK);
    }

    @GetMapping("/bankaccount")
    public ResponseEntity<List<BankAccount>> getAllBankAccount() {
        return new ResponseEntity<>(bankAccountService.getAllBankAccount(), HttpStatus.OK);
    }

    @GetMapping("/bankaccount/{pin}")
    public ResponseEntity<BankAccount> getAccountById(@PathVariable Integer pin) {
        return new ResponseEntity<>(bankAccountService.getAccountById(pin), HttpStatus.OK);

    }

    @GetMapping("/accountbalance/{pin}")
    public ResponseEntity<ResponseWrapper> checkAcountBalance(@PathVariable Integer pin) throws InvalidPinException {
        return bankAccountService.getAccountBalance(pin);

    }

}
