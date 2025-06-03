package org.example.userauthservice_asw_may2025.exceptions;

public class AccountSuspendedException extends RuntimeException {
    public AccountSuspendedException(String message) {
        super(message);
    }
}
