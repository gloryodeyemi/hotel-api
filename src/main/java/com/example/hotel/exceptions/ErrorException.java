package com.example.hotel.exceptions;

import javax.security.sasl.AuthenticationException;

public class ErrorException extends AuthenticationException {
    public ErrorException(final String message) {
        super(message);
    }
}

