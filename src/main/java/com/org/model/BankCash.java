package com.org.model;

/**
 * @author Raj
 * @since 02-07-2022
 * 
 */

public class BankCash {

    private String type;
    private int value;
    private int amount;

    public BankCash() {
        // empty constructor
    }

    public BankCash(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BankCash{" +
                " type=" + getType() +
                " value=" + getValue() +
                " amount=" + getAmount() +
                " }";
    }

}
