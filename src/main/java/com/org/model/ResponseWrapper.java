package com.org.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.org.util.BankMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raj
 * @since 02-07-2022
 * 
 */

@Service
public class ResponseWrapper {
    @JsonIgnore
    private List<BankCash> banks;
    private String responseCode;
    private String responseDesc;
    private String responseStatus;

    public List<BankCash> getBanks() {
        return banks;
    }

    public void setBanks(List<BankCash> banks) {
        this.banks = banks;
    }

    public ResponseWrapper() {
        this.banks = new ArrayList<>();
    }

    public void setResponseBody(int[] banks, int[] bankValues) {
        for (int i = 0; i < banks.length; i++) {
            if (banks[i] > 0) {
                BankCash bank = new BankCash(BankMapper.getBankType(bankValues[i]), bankValues[i]);
                bank.setAmount(banks[i]);
                this.banks.add(bank);
            }
        }
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public List<BankCash> getResponseBody() {
        return banks;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    @Override
    public String toString() {
        return "ResponseWrapper:{" +
                " resultCode=" + getResponseCode() +
                " resultStatus=" + getResponseStatus() +
                " resultDesc=" + getResponseDesc() +
                " resultBody=" + getResponseBody() +
                " }";
    }
}
