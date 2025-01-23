package com.main.exception;

import com.main.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //Used to awake exceptions
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(UserAccountException.class)
    public ResponseEntity<ErrorResponse> handleAccountUserException(UserAccountException ex) {
        ErrorResponse errorResponse = new ErrorResponse("User Account Error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }


}
