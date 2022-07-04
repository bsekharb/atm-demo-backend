package com.org.controller;

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
import com.org.entity.BankCash;
import com.org.service.IBankCashService;
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
@WebMvcTest(BankCashController.class)
public class BankCashControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private IBankCashService bankCashService;

    @Test
    public void getAllBankCash() throws Exception {
      
        List<BankCash> bankCashList = new ArrayList<>();
        BankCash bankCash = new BankCash();
        bankCash.setAmount(10);
        bankCash.setType("Ten");
        bankCash.setValue(10);
        bankCashList.add(bankCash);

        when(bankCashService.getAllBankCash()).thenReturn(bankCashList);
        mvc.perform(MockMvcRequestBuilders
                .get("/api/bankcash")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].value").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].value").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].value").value(10));
    }
}
