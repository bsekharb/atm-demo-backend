package com.org.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.org.entity.BankAccount;
import com.org.exception.InvalidPinException;
import com.org.model.ResponseWrapper;

/**
 * @author Raj
 * @since 02-07-2022
 * 
 */

public interface IBankAccountService {

    BankAccount createBankAccount(BankAccount bankAccount);
    List<BankAccount> getAllBankAccount();
    BankAccount getAccountById(Integer pin);
    ResponseEntity<ResponseWrapper> getAccountBalance(Integer pin) throws InvalidPinException;
}
