package com.org.util;

import com.org.exception.InsufficientBalanceException;
import com.org.exception.InsufficientNoteException;
import com.org.exception.InvalidAmountException;
import java.util.List;

/**
 * @author Raj
 * @since 02-07-2022
 * 
 */

public class BankValidator {

    public static boolean validateAmount(int amount) throws InvalidAmountException {
        if (amount < 5) {
            throw new InvalidAmountException("Amount less than min amount");
        }
        return true;
    }

    public static boolean validateWithdrawEligible(int amount, int totalBalanceAmt, int accountNumber)
            throws InvalidAmountException {

        if (amount > totalBalanceAmt) {
            throw new InvalidAmountException("Account holder: " + accountNumber + " having Insuffient amount");
        }
        return true;
    }

    public static boolean validateRemainingBalance(int amount, int[] bankAmount, int[] bankValues)
            throws InsufficientBalanceException {

        int totalAmt = 0;
        for (int i = 0; i < bankAmount.length; i++) {
            totalAmt += bankValues[i] * bankAmount[i];
        }
        if (totalAmt < amount) {
            throw new InsufficientBalanceException("Remaining balance less than dispensed amount");
        }
        return true;
    }

    public static int[] validateRemainingNote(int[] bankLeft, List<int[]> totalBankNeeded)
            throws InsufficientNoteException {

        for (int[] bankNeeded : totalBankNeeded) {
            int[] resultBankSet = validateRemainingNote(bankLeft, bankNeeded);
            if (resultBankSet != null) {
                return resultBankSet;
            }
        }
        throw new InsufficientNoteException("Insufficient note number. Try dispensing a different amount.");
    }

    private static int[] validateRemainingNote(int[] bankLeft, int[] bankNeeded) {
        
        for (int i = 0; i < bankLeft.length; i++) {
            if (bankLeft[i] < bankNeeded[i] && bankNeeded[i] > 0) {
                return null;
            }
        }
        return bankNeeded;
    }
}
