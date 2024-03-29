package com.example.hsAssgn.controller;

import com.example.hsAssgn.entity.OrderDTO;
import com.example.hsAssgn.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/{userId}/order")
    public ResponseEntity<OrderDTO> placeOrder(@PathVariable Long userId, @RequestParam Long quantity,
                                               @RequestParam String coupon) throws Exception {
        OrderDTO orderDTO = orderService.placeOrder(userId,quantity,coupon);

        return ResponseEntity.ok(orderDTO);
    }



}
