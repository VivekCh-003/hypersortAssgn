package com.example.hsAssgn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<String> handleInvalidQuantityException(InvalidQuantityException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"description\": " + ex.getMessage() + "\"}");
    }

    @ExceptionHandler(InvalidCouponException.class)
    public ResponseEntity<String> handleInvalidCouponException(InvalidCouponException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"description\": " + ex.getMessage() + "\"}");
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"orderId\": \"" + ex.getOrderId() + "\", \"description\": \"" + ex.getMessage() + "\"}");
    }


}
