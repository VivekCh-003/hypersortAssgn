package com.example.hsAssgn.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
//@Embeddable
public class OrderDTO {
    private Long orderId;
    private Long userId;
    private Long quantity;
    private Double amount;
    private String coupon;
}
