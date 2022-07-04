package com.org.service.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.org.entity.BankAccount;
import com.org.entity.BankCash;
import com.org.exception.InvalidPinException;
import com.org.repository.BankAccountRepository;
import com.org.repository.BankCashRepository;

/**
 * @author Raj
 * @since 02-07-2022
 * 
 */

@Service
public class AtmServiceHelper {

    private final Logger logger = LoggerFactory.getLogger(AtmServiceHelper.class);

    @Autowired
    private BankCashRepository bankCashRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;
    private int[] bankValues;
    public int[] getBankAmount() {

        List<BankCash> banks = (List<BankCash>) bankCashRepository.findAll();
        bankValues = new int[banks.size()];
        int bankAmount[] = new int[banks.size()];
        for (int i = 0; i < banks.size(); i++) {
            bankAmount[i] = banks.get(i).getAmount();
            bankValues[i] = banks.get(i).getValue();
            logger.info("bankValues are --->raj: " + bankValues[i]);
            logger.info("bankAmount are --->raj: " + bankAmount[i]);
        }
        logger.info("Got data from database : {}", banks);
        return bankAmount;
    }

    public int[] getBankValues() {
        return bankValues;
    }

    public BankCash getBankCashById(int id) {

        Optional<BankCash> dbBankCash = bankCashRepository.findById(id);
        return dbBankCash.orElse(null);
    }

    public BankAccount getBankAccountById(int pin) {

        Optional<BankAccount> dbAccount = bankAccountRepository.findById(pin);
        return dbAccount.orElse(null);
    }

    public void updateBalanceAmt(int[] updatedBankAmt, int[] bankValues) {

        for (int i = 0; i < updatedBankAmt.length; i++) {
            int bankValue = bankValues[i];

            BankCash dbBankCash = getBankCashById(bankValue);
            dbBankCash.setAmount(updatedBankAmt[i]);
            bankCashRepository.save(dbBankCash);
        }
    }

    public void updateBankAccountBalance(int amount, int pin) {

        BankAccount dbBankAccount = getBankAccountById(pin);
        int openingBal = dbBankAccount.getOpeningBalance();
        openingBal = openingBal - amount;
        dbBankAccount.setOpeningBalance(openingBal);
        bankAccountRepository.save(dbBankAccount);
    }

    public List<int[]> findBanks(int amount, int[] currentBankAmt, int[] balanceBankAmt,
         int[] bankValues, int position) {

        List<int[]> bankCombination = new ArrayList<>();
        int totalAmt = calCurrentTotalAmt(currentBankAmt, bankValues);
        if (totalAmt < amount) {
            for (int i = position; i < currentBankAmt.length; i++) {
                if (balanceBankAmt[i] > currentBankAmt[i]) {
                    int newCurrentBankAmt[] = currentBankAmt.clone();
                    newCurrentBankAmt[i]++;
                    List<int[]> resultList = findBanks(amount, newCurrentBankAmt, balanceBankAmt, bankValues, i);
                    if (resultList != null) {
                        bankCombination.addAll(resultList);
                    }
                }
            }
        } else if (totalAmt == amount) {
            bankCombination.add(currentBankAmt);
        }
        return bankCombination;
    }

    public int[] subtractBankAmt(int[] balanceBanks, int[] banks) {

        for (int i = 0; i < banks.length; i++) {
            balanceBanks[i] -= banks[i];
        }
        return balanceBanks;
    }

    public int calCurrentTotalAmt(int[] bankAmounts, int[] bankValues) {
        
        int totalAmt = 0;
        for (int i = 0; i < bankAmounts.length; i++) {
            totalAmt += bankAmounts[i] * bankValues[i];
        }
        return totalAmt;
    }

    public String getAccountBalanceDesc(Integer id) {

        String accountBalanceDesc; 
       
        Optional<BankAccount> dbAccount = bankAccountRepository.findById(id);
        try {
            if (!dbAccount.isPresent()) {
                throw new InvalidPinException("unable to check your balance due to Pin is Incorrect!");
            } else {
               
                accountBalanceDesc = "Account Balance : " + dbAccount.get().getOpeningBalance();
               
            }
        } catch (Exception e) {
            logger.error("Got Exception while processing : {}", e.getMessage());
            accountBalanceDesc = e.getMessage();
        }
            return accountBalanceDesc;
    }
}
