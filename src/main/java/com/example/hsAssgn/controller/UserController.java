package com.example.hsAssgn.controller;

import com.example.hsAssgn.entity.OrderTransactionDTO;
import com.example.hsAssgn.entity.UserOrdersDTO;
import com.example.hsAssgn.service.OrderService;
import com.example.hsAssgn.service.TransactionService;
import com.example.hsAssgn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<UserOrdersDTO>> getOrders(@PathVariable Long userId){
        List<UserOrdersDTO> orders = orderService.getOrdersByUserId(userId);

        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{userId}/orders/{orderId}")
    private ResponseEntity<List<OrderTransactionDTO>> getOrdersByOrderId(@PathVariable Long userId, @PathVariable Long orderId) throws Exception{
        List<OrderTransactionDTO> transactionsDTOS = orderService.getOrderswithTransactions(userId,orderId);

        return ResponseEntity.ok(transactionsDTOS);
    }
}
