package com.org.service;

import java.util.List;
import com.org.entity.BankCash;

/**
 * @author Raj
 * @since 02-07-2022
 * 
 */

public interface IBankCashService {

    BankCash createBankCash(BankCash bankAccount);
    List<BankCash> getAllBankCash();
}
