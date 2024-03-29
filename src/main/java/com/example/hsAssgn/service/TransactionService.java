package com.example.hsAssgn.service;

import com.example.hsAssgn.entity.OrderTransactionDTO;
import com.example.hsAssgn.entity.Transaction;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TransactionService {
    ResponseEntity<Transaction> makePayment(Long userId, Long orderId, Double amount);

}
