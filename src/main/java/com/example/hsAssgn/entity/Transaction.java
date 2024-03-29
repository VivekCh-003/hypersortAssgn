package com.example.hsAssgn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "transaction")
public class Transaction {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionId;
    private Long userId;
    private Long orderId;
    private String status;
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId",insertable=false, updatable=false)
    private Order order;

    public Transaction(){

    }

    public Transaction(Long userId, Long orderId, String status, String description) {
        this.userId = userId;
        this.orderId = orderId;
        this.status = status;
        this.description = description;
    }
}
