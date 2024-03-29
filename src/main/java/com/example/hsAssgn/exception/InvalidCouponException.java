package com.example.hsAssgn.exception;

public class InvalidCouponException extends RuntimeException{
    public InvalidCouponException(String msg){
        super(msg);
    }
}
