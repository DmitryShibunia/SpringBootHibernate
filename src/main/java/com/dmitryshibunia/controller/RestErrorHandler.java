package com.dmitryshibunia.controller;

import com.dmitryshibunia.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleDataAccessException(RecordNotFoundException ex) {
        return "DataAccessException: " + ex.getLocalizedMessage();
    }

    @ExceptionHandler(NoSuchFieldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNoSuchFieldException(NoSuchFieldException ex) {
        return "DataAccessException: " + ex.getLocalizedMessage() + " field does not exist";
    }
}
