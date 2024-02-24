package com.crm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LeadAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> LeadAlreadyExistExceptionHandler(Exception ex, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(), ex.getMessage(), webRequest.getDescription(false)
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LeadNotExistException.class)
    public ResponseEntity<ErrorDetails> leadNotExistsExceptionHandler(Exception ex, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(), ex.getMessage(), webRequest.getDescription(false)
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ContactNotExistException.class)
    public ResponseEntity<ErrorDetails> contactNotExistExceptionHandler(Exception ex, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(), ex.getMessage(), webRequest.getDescription(false)
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}