package com.org.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.org.entity.BankCash;
import com.org.service.IBankCashService;

/**
 * @author Raj
 * @since 02-07-2022
 * 
 */

@RestController
@RequestMapping("/api")
public class BankCashController {
    @Autowired
    private IBankCashService bankCashService;

    @PostMapping("/init-bankcash")
    public ResponseEntity<BankCash> loadBankCash(@RequestBody BankCash bankCash) {
        return new ResponseEntity<>(bankCashService.createBankCash(bankCash), HttpStatus.OK);
    }

    @GetMapping("/bankcash")
    public ResponseEntity<List<BankCash>> getAllCash() {
        return new ResponseEntity<>(bankCashService.getAllBankCash(), HttpStatus.OK);
    }
}
