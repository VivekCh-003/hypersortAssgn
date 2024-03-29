package com.example.hsAssgn.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    List<String> coupons;

    @OneToMany(targetEntity = Order.class)
    List<Order> orders;
}
