package com.example.library2.exception;

public class BadStatusCode extends RuntimeException{
    public BadStatusCode(String message) {
        super(message);
    }
}
