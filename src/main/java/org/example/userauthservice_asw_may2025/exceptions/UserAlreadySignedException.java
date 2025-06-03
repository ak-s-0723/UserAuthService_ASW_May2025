package org.example.userauthservice_asw_may2025.exceptions;

public class UserAlreadySignedException extends RuntimeException {
    public UserAlreadySignedException(String message) {
        super(message);
    }
}
