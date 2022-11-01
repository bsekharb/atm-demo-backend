package com.org.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;
import com.org.exception.InsufficientBalanceException;
import com.org.exception.InsufficientNoteException;
import com.org.exception.InvalidAmountException;
import com.org.exception.InvalidPinException;
import com.org.model.ResponseWrapper;
import com.org.repository.BankAccountRepository;

/**
 * @author Raj
 * @since 03-07-2022
 * This is test for atm controller test
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtmControllerTest {

    @Mock
    BankAccountRepository bankAccountRepository;

    @InjectMocks
    @Autowired
    AtmController atmController;

    @Test
    public void calculateBankWorkCorrectlyWhenSuccess() throws InsufficientNoteException, InsufficientBalanceException,
            InvalidAmountException, InvalidPinException {

        String expectedResultCode = "1";
        String expectedResultDesc = "Pin is Incorrect!";
        ResponseEntity<ResponseWrapper> response = atmController.withDraw(10, 123);
        Assert.assertEquals(expectedResultDesc, response.getBody().getResponseDesc());
        Assert.assertEquals(expectedResultCode, response.getBody().getResponseCode());

    }

    @Test
    public void calculateBankWorkCorrectlyWhenFail() throws InsufficientNoteException, InsufficientBalanceException,
            InvalidAmountException, InvalidPinException {

        String expectedResultCode = "1";
        ResponseEntity<ResponseWrapper> response = atmController.withDraw(10, 123);
        Assert.assertEquals(expectedResultCode, response.getBody().getResponseCode());
    }

    @Test
    public void AtmCashBalanceWhenSuccess() {

        String expectedResultCode = "0";
        ResponseEntity<ResponseWrapper> response = atmController.checkBalance();
        Assert.assertEquals(expectedResultCode, response.getBody().getResponseCode());

    }

}
