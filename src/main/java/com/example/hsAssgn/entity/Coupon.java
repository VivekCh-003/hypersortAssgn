package com.example.hsAssgn.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponId;
    private String couponName;
    private Double discount;

}
