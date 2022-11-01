package com.org.service;

import com.org.entity.BankAccount;
import com.org.repository.BankAccountRepository;
import com.org.service.impl.BankAccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * @author Raj
 * @since 03-07-2022
 * 
 */

@RunWith(MockitoJUnitRunner.class)
public class BankAccountServiceImplTest {

    @InjectMocks
    private BankAccountServiceImpl bankAccountService;

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Test
    public void whenGetAllBankAccount_thenReturnBankAccountList() {

        List<BankAccount> BankAccountList = new ArrayList<>();
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber(123456789);
        bankAccount.setPin(1234);
        bankAccount.setOverDraft(200);
        bankAccount.setOpeningBalance(700);
        BankAccountList.add(bankAccount);

        Mockito.when(bankAccountRepository.findAll()).thenReturn(BankAccountList);

        List<BankAccount> returnedBankAccountList = bankAccountService.getAllBankAccount();
        bankAccountService.createBankAccount(bankAccount);
        Mockito.when(bankAccountRepository.findById(bankAccount.getPin())).
        thenReturn(Optional.of(new BankAccount()));
        
       int statusCode = bankAccountService.getAccountBalance(bankAccount.getPin()).getStatusCodeValue();
      Mockito.when(bankAccountRepository.
      findById(bankAccount.getPin())).thenReturn(Optional.of(new BankAccount()));
      bankAccountService.getAccountById(bankAccount.getPin());
     
      assertEquals(statusCode,200);
       assertEquals(returnedBankAccountList.size(), BankAccountList.size());
    }
}
