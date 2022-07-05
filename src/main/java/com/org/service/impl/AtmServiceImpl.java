package com.org.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.org.entity.BankAccount;
import com.org.exception.InvalidPinException;
import com.org.model.ResponseWrapper;
import com.org.repository.BankAccountRepository;
import com.org.service.IAtmService;
import com.org.service.helper.AtmServiceHelper;
import com.org.util.BankValidator;
import com.org.util.Constant;

/**
 * @author Raj
 * @since 02-07-2022
 * 
 */

@Service
public class AtmServiceImpl implements IAtmService{

    private final Logger logger = LoggerFactory.getLogger(AtmServiceImpl.class);

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AtmServiceHelper atmServiceHelper;
    
    @Override
    public ResponseEntity<ResponseWrapper> calculateBankCash(Integer amount, Integer pin) {
        String responseCode;
        String responseDesc;
        String responseStatus;
        ResponseWrapper responseWrapper = new ResponseWrapper();
        HashMap<Integer, Integer> atmCash = new HashMap<>();
        
        try {
            int[] bankAmounts = atmServiceHelper.getBankAmount();
            int[] bankValues = atmServiceHelper.getBankValues();
            
            for (int i = 0; i < bankValues.length; i++) {
				atmCash.put(bankValues[i], bankAmounts[i]);
			}
			
			bankValues = IntStream.of(bankValues).boxed().sorted(Comparator.reverseOrder()).mapToInt(i -> i).toArray();

			for (int i = 0; i < bankValues.length; i++) {
				int noOfNotes = atmCash.get(bankValues[i]);
				bankAmounts[i] = noOfNotes;
			}
            Optional<BankAccount> dbAccount = bankAccountRepository.findById(pin);
            if(!dbAccount.isPresent()) {
                  throw new InvalidPinException("Pin is Incorrect!");
                }

                int accountNumber = dbAccount.get().getAccountNumber();
                int openingBalance = dbAccount.get().getOpeningBalance();
                int overDraft = dbAccount.get().getOverDraft();
                int totalBalanceAmt = openingBalance + overDraft;

                BankValidator.validateWithdrawEligible(amount, totalBalanceAmt, accountNumber );
                BankValidator.validateAmount(amount);
                BankValidator.validateRemainingBalance(amount, bankAmounts, bankValues);
                List<int[]> bankList = atmServiceHelper.findBanks(amount, new int[bankAmounts.length], bankAmounts, bankValues, 0);
                int[] selectedBankList = BankValidator.validateRemainingNote(bankAmounts, bankList);
                int[] updatedBankList = atmServiceHelper.subtractBankAmt(bankAmounts, selectedBankList);
                atmServiceHelper.updateBalanceAmt(updatedBankList, bankValues);
                atmServiceHelper.updateBankAccountBalance(amount,pin);
                String accountBalDes = atmServiceHelper.getAccountBalanceDesc(pin);
                responseWrapper.setResponseBody(selectedBankList, bankValues);
                responseCode = Constant.SUCCESS_CODE;
                responseDesc = Constant.SUCCESS +"\n"+accountBalDes+"\n";
                responseStatus = Constant.SUCCESS;
            } catch (Exception e){
                logger.error("Got Exception while processing : {}", e.getMessage());
                responseCode = Constant.FAIL_CODE;
                responseDesc = e.getMessage();
                responseStatus = Constant.FAIL;
            }
            responseWrapper.setResponseCode(responseCode);
            responseWrapper.setResponseDesc(responseDesc);
            responseWrapper.setResponseStatus(responseStatus);
            ResponseEntity<ResponseWrapper> response = new ResponseEntity<>(responseWrapper, HttpStatus.OK);
            logger.info("Response {}", response);
            return response;
        }

    @Override
    public ResponseEntity<ResponseWrapper> checkBalance() {

        int[] bankAmounts = atmServiceHelper.getBankAmount();
        int[] bankValues = atmServiceHelper.getBankValues();
       
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setResponseBody(bankAmounts, bankValues);
        responseWrapper.setResponseCode(Constant.SUCCESS_CODE);
        responseWrapper.setResponseDesc(Constant.SUCCESS);
        responseWrapper.setResponseStatus(Constant.SUCCESS);
        ResponseEntity<ResponseWrapper> response = new ResponseEntity<>(responseWrapper, HttpStatus.OK);
        logger.info("Response {}", response);
        return response;
    }
   
}
