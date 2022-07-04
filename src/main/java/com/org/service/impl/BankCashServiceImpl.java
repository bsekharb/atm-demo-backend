package com.org.service.impl;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.org.entity.BankCash;
import com.org.repository.BankCashRepository;
import com.org.service.IBankCashService;

/**
 * @author Raj
 * @since 02-07-2022
 * 
 */

@Service
public class BankCashServiceImpl implements IBankCashService {

    @Autowired
    private BankCashRepository bankCashRepository;

    @Override
    public BankCash createBankCash(BankCash bankCash) {
        return bankCashRepository.save(bankCash);
    }

    @Override
    public List<BankCash> getAllBankCash() {
        return (List<BankCash>) bankCashRepository.findAll();
    }

}
