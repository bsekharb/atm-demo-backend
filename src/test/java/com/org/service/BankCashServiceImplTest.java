package com.org.service;

import com.org.entity.BankCash;
import com.org.repository.BankCashRepository;
import com.org.service.impl.BankCashServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

/**
 * @author Raj
 * @since 03-07-2022
 * 
 */

@RunWith(MockitoJUnitRunner.class)
public class BankCashServiceImplTest {

    @InjectMocks
    private BankCashServiceImpl bankCashService;

    @Mock
    private BankCashRepository bankCashRepository;

    @Test
    public void whenGetAllBankCash_thenReturnBankAccountList() {

        List<BankCash> BankCashList = new ArrayList<>();
        BankCash bankCash = new BankCash();
        bankCash.setAmount(10);
        bankCash.setType("Fifty");
        bankCash.setValue(50);
        BankCashList.add(bankCash);

        Mockito.when(bankCashRepository.findAll()).thenReturn(BankCashList);
        List<BankCash> returnedBankAccountList = bankCashService.getAllBankCash();
        assertEquals(returnedBankAccountList.size(), BankCashList.size());

    }
}
