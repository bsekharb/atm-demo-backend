package com.org.exception;

/**
 * @author Raj
 * @since 02-07-2022
 * 
 */

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
