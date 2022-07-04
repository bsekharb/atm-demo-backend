package com.org.service;

import org.springframework.http.ResponseEntity;
import com.org.model.ResponseWrapper;

/**
 * @author Raj
 * @since 02-07-2022
 * 
 */

public interface IAtmService {

    ResponseEntity<ResponseWrapper> calculateBankCash(Integer amount, Integer pin);
    ResponseEntity<ResponseWrapper> checkBalance();

}
