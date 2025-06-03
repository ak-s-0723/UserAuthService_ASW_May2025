package org.example.userauthservice_asw_may2025.controllers;

import org.example.userauthservice_asw_may2025.exceptions.PasswordMismatchException;
import org.example.userauthservice_asw_may2025.exceptions.UserAlreadySignedException;
import org.example.userauthservice_asw_may2025.exceptions.UserNotRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler({PasswordMismatchException.class, UserNotRegisteredException.class, UserAlreadySignedException.class})
    public ResponseEntity<String> handleExceptions(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
