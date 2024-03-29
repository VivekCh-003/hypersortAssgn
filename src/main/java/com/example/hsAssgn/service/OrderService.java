package com.example.hsAssgn.service;

import com.example.hsAssgn.entity.Order;
import com.example.hsAssgn.entity.OrderDTO;
import com.example.hsAssgn.entity.OrderTransactionDTO;
import com.example.hsAssgn.entity.UserOrdersDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    int getTotalOrderedQuantity();

    OrderDTO placeOrder(Long userId, Long quantity, String coupon) throws Exception;

    List<UserOrdersDTO> getOrdersByUserId(Long userId);

    Optional<Order> getOrderById(Long orderId);



    List<OrderTransactionDTO> getOrderswithTransactions(Long userId, Long orderId) throws Exception;
}
