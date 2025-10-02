package com.emmanuelanene.sequoia_air.exceptions;

public class PaymentProcessingException extends RuntimeException{
    public PaymentProcessingException(String message) {
        super(message);
    }
}
