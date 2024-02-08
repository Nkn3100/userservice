package org.scaler.userservice.exceptions;

public class TokenAlreadyExpiredOrNotFoundException extends Exception{
    public TokenAlreadyExpiredOrNotFoundException(String message) {
        super(message);
    }
}
