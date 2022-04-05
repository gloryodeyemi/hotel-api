package com.example.hotel.exceptions;

import javax.security.sasl.AuthenticationException;

public class StaffAlreadyExistException extends AuthenticationException {
    public StaffAlreadyExistException(final String message) {
        super(message);
    }
}
