package com.example.hsAssgn.exception;

public class OrderNotFoundException extends RuntimeException{
    public Long orderId;
    public OrderNotFoundException(Long orderId,String msg){
        super(msg);
        this.orderId = orderId;
    }

    public Long getOrderId(){
        return orderId;
    }
}
