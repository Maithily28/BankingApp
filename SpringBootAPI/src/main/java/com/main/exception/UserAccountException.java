package com.main.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class UserAccountException extends RuntimeException {

    private final HttpStatus status;

    public UserAccountException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
