package com.example.hsAssgn.controller;

import com.example.hsAssgn.entity.Transaction;
import com.example.hsAssgn.service.OrderService;
import com.example.hsAssgn.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/{userId}/{orderId}/pay")
    public Transaction makePayment(@PathVariable Long userId, @PathVariable Long orderId,
                                                   @RequestParam Double amount){

        Transaction transaction = transactionService.makePayment(userId,orderId,amount).getBody();

        return transaction;
    }
}
