package com.society.exception;

public class InvalidComplaintException extends RuntimeException {
    public InvalidComplaintException(String message) {
        super(message);
    }
}
