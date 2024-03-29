package com.example.hsAssgn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "`order`")
@SequenceGenerator(name = "seq",initialValue = 100)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq")
    private Long orderId;
    private Long quantity;
    private Double amount;
    private LocalDate date;
    private String coupon;
    private String status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order")
    private List<Transaction> transactions;
}
