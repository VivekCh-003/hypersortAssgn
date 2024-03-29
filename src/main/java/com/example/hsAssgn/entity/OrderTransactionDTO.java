package com.example.hsAssgn.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderTransactionDTO {
    private Long orderId;
    private Double amount;
    private LocalDate date;
    private String coupon;
    private String transactionId;
    private String status;
}
