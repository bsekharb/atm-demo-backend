package com.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.org.exception.InsufficientBalanceException;
import com.org.exception.InsufficientNoteException;
import com.org.exception.InvalidAmountException;
import com.org.exception.InvalidPinException;
import com.org.model.ResponseWrapper;
import com.org.service.IAtmService;

/**
 * @author Raj
 * @since 02-07-2022
 * 
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class AtmController {

    @Autowired
    IAtmService atmService;

    @GetMapping("/dispense")
    public ResponseEntity<ResponseWrapper> withDraw(
            @RequestParam("amount") int amount,
            @RequestParam("pin") int pin)
            throws InsufficientNoteException, InsufficientBalanceException, InvalidAmountException,
            InvalidPinException {
        return atmService.calculateBankCash(amount, pin);
    }

    @GetMapping("/checkBalance")
    public ResponseEntity<ResponseWrapper> checkBalance() {
        return atmService.checkBalance();
    }
}
