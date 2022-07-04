package com.org.controller;

import com.org.entity.BankAccount;
import com.org.service.IBankAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author Raj
 * @since 03-07-2022
 * 
 */

@RunWith(SpringRunner.class)
@WebMvcTest(BankAccountController.class)
public class BankAccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IBankAccountService bankAccountService;

    @Test
    public void getAllBankAccount() throws Exception {

        List<BankAccount> bankAccountList = new ArrayList<>();
        BankAccount bankAccount = new BankAccount();
        bankAccount.setPin(1234);
        bankAccount.setAccountNumber(1234567);
        bankAccount.setOverDraft(200);
        bankAccount.setOpeningBalance(800);
        bankAccountList.add(bankAccount);

        when(bankAccountService.getAllBankAccount()).thenReturn(bankAccountList);
        mvc.perform(MockMvcRequestBuilders
                .get("/api/bankaccount")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].pin").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].pin").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].pin").value(1234));
    }
}
