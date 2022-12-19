package com.example.library2.controller;

import com.example.library2.exception.BadStatusCode;
import com.example.library2.exception.NoEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(NoEntityException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleAuthorException(NoEntityException ex) {
        return ex.getMessage();
    }
    @ExceptionHandler(BadStatusCode.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleBadStatusCodeException(NoEntityException ex) {
        return ex.getMessage();
    }
}
