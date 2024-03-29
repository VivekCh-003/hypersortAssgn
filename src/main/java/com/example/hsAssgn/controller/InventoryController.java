package com.example.hsAssgn.controller;

import com.example.hsAssgn.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private static int productPrice = 100;
    private static int productAvailability = 100;


    @Autowired
    private OrderService orderService;


    @GetMapping
    public ResponseEntity<Map<String,Integer>> getInventory(){
        int orderedQuantity = orderService.getTotalOrderedQuantity();
        int available = productAvailability - orderedQuantity;

        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("ordered", orderedQuantity);
        inventory.put("price", productPrice);
        inventory.put("available", available);

        return ResponseEntity.ok(inventory);
    }
}
