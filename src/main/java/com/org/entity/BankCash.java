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
@Table(name = "bankCash")
public class BankCash {
    @Id
    private int value;

    private String type;

    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BankCash() {

    }

    public BankCash(String type, int value) {
        this.type = type;
        this.value = value;
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
        return "BankCash [type=" + type + ", value=" + value + "]";
    }

}
