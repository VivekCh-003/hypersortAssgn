package com.example.hsAssgn.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserOrdersDTO {
    private Long orderId;
    private Double amount;
    private LocalDate date;
    private String coupon;
}
