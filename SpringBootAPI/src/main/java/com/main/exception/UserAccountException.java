package com.main.exception;

import org.springframework.http.HttpStatus;



public class UserAccountException extends RuntimeException {

    private final HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }

    public UserAccountException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
