package com.society.exception;


public class InvalidBillException extends RuntimeException {
    public InvalidBillException(String message) {
        super(message);
    }
}
