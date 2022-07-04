package com.org.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Raj
 * @since 02-07-2022
 * 
 */

@Entity
@Table(name = "bankAccounts")
public class BankAccount {
    @Id
    private int pin;
    private int accountNumber;
    private int openingBalance;
    private int overDraft;

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(int openingBalance) {
        this.openingBalance = openingBalance;
    }

    public int getOverDraft() {
        return overDraft;
    }

    public void setOverDraft(int overDraft) {
        this.overDraft = overDraft;
    }

    @Override
    public String toString() {
        return "BankAccount [accountNumber=" + accountNumber + ", openingBalance=" + openingBalance + ", overDraft="
                + overDraft + ", pin=" + pin + "]";
    }

}
