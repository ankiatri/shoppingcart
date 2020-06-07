package com.ethoca.elimininator.shoppingcart.exception;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ErrorResponse>> handle(ConstraintViolationException e) {
        List<ErrorResponse> errors = new ArrayList<>();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            ErrorResponse error = new ErrorResponse();
            error.setCode(violation.getMessageTemplate());
            error.setReason(violation.getMessage());
            errors.add(error);
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShoppingCartException.class)
    public ResponseEntity<ErrorResponse> handle(ShoppingCartException e) {
        ErrorResponse error = new ErrorResponse();
        error.setReason(HttpStatus.NOT_FOUND.getReasonPhrase());
        error.setCode(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<ErrorResponse> handlePersistenceException(PersistenceException e) {
        ErrorResponse error = new ErrorResponse();
        error.setReason(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
        error.setCode(e.getMessage());
        error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.name());
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
