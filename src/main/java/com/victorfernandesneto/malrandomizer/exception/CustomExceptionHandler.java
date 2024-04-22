package com.victorfernandesneto.malrandomizer.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserPrivateOrEmptyListException.class)
    public ResponseEntity<Object> handleUserPrivateOrEmptyListException(
            UserPrivateOrEmptyListException exception) {

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(
            UserNotFoundException exception) {

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }
}