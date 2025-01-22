package com.main.exception;

import com.main.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //Used to awake exceptions
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(UserAccountException.class)
    public ResponseEntity<ErrorResponse> handleAccountUserException(UserAccountException ex) {
        ex.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse("User Account Error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("General Error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
