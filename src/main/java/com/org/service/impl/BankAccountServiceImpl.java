package com.org.service.impl;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.org.entity.BankAccount;
import com.org.exception.InvalidPinException;
import com.org.model.ResponseWrapper;
import com.org.repository.BankAccountRepository;
import com.org.service.IBankAccountService;
import com.org.util.Constant;

/**
 * @author Raj
 * @since 02-07-2022
 * 
 */

@Service
public class BankAccountServiceImpl implements IBankAccountService {

    private final Logger logger = LoggerFactory.getLogger(AtmServiceImpl.class);

    @Autowired
    private BankAccountRepository bankAccountRepository;

    String responseCode;
    String responseDesc;
    String responseStatus;

    @Override
    public BankAccount createBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public List<BankAccount> getAllBankAccount() {
        return (List<BankAccount>) bankAccountRepository.findAll();
    }

    @Override
    public BankAccount getAccountById(Integer id) {
        Optional<BankAccount> dbAccount = bankAccountRepository.findById(id);
        return dbAccount.orElse(null);
    }

    @Override
    public ResponseEntity<ResponseWrapper> getAccountBalance(Integer id) {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        Optional<BankAccount> dbAccount = bankAccountRepository.findById(id);
        try {
            if (!dbAccount.isPresent()) {
                throw new InvalidPinException("unable to check your balance due to Pin is Incorrect!");
            } else {
                responseCode = Constant.SUCCESS_CODE;
                responseDesc = "Account Balance : " + dbAccount.get().getOpeningBalance();
                responseStatus = Constant.SUCCESS;
            }
        } catch (Exception e) {
            logger.error("Got Exception while processing : {}", e.getMessage());
            responseCode = Constant.FAIL_CODE;
            responseDesc = e.getMessage();
            responseStatus = Constant.FAIL;
        }
        responseWrapper.setResponseCode(responseCode);
        responseWrapper.setResponseDesc(responseDesc);
        responseWrapper.setResponseStatus(responseStatus);
        ResponseEntity<ResponseWrapper> response = new ResponseEntity<>(responseWrapper, HttpStatus.OK);
        logger.info("Response {}", response);
        return response;
    }

}
