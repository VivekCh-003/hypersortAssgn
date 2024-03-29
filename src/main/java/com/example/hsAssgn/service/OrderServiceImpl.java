package com.example.hsAssgn.service;

import com.example.hsAssgn.entity.*;
import com.example.hsAssgn.exception.InvalidCouponException;
import com.example.hsAssgn.exception.InvalidQuantityException;
import com.example.hsAssgn.exception.OrderNotFoundException;
import com.example.hsAssgn.repository.CouponRepository;
import com.example.hsAssgn.repository.OrderRepository;
import com.example.hsAssgn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CouponService couponService;

    @Override
    public int getTotalOrderedQuantity() {
        Integer quantity = orderRepository.getTotalOrderQuantity();
        if(quantity == null){
            return 0;
        }
        return quantity;
    }

    @Override
    public OrderDTO placeOrder(Long userId, Long quantity, String couponName) throws Exception {
        if (quantity < 1 || quantity > 100 - getTotalOrderedQuantity()) {
            throw new InvalidQuantityException("Invalid quantity");
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));

        if (!couponIsValid(user, couponName)) {
            throw new InvalidCouponException("Invalid Coupon");
        }

        Coupon tempCoupon = couponService.findByCouponName(couponName);

        Double discountAmount = ((quantity * 100) * tempCoupon.getDiscount()) / 100.0;

        Order order = new Order();
        order.setUser(user);
        order.setQuantity(quantity);
        order.setAmount((quantity * 100) - discountAmount);
        order.setDate(LocalDate.now());
        order.setCoupon(couponName);

        orderRepository.save(order);


        OrderDTO orderDTO = convertOrderToOrderDTO(order);

        user.getOrders().add(order);
        userRepository.save(user);

        return orderDTO;
    }

    @Override
    public List<UserOrdersDTO> getOrdersByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        List<UserOrdersDTO> userOrdersDTOS = new ArrayList<>();
        for (Order order : orders){
            UserOrdersDTO userOrdersDTO = convertOrderToUserOrderDTO(order);
            userOrdersDTOS.add(userOrdersDTO);
        }

        return userOrdersDTOS;
    }

    private UserOrdersDTO convertOrderToUserOrderDTO(Order order) {
        UserOrdersDTO userOrdersDTO = new UserOrdersDTO();
        userOrdersDTO.setOrderId(order.getOrderId());
        userOrdersDTO.setAmount(order.getAmount());
        userOrdersDTO.setDate(order.getDate());
        userOrdersDTO.setCoupon(order.getCoupon());

        return userOrdersDTO;
    }

    @Override
    public Optional<Order> getOrderById(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);

        return Optional.ofNullable(order.orElse(null));
    }


    @Override
    public List<OrderTransactionDTO> getOrderswithTransactions(Long userId, Long orderId) throws Exception{

        Order optOrders = orderRepository.findByUserIdAndOrderId(userId,orderId);
        if(optOrders == null){
            throw new OrderNotFoundException(orderId,"Order not found");
        }

        List<Transaction> transactions = optOrders.getTransactions();
        List<OrderTransactionDTO> transactionDTOs = new ArrayList<>();

        for (Transaction transaction : transactions) {
            OrderTransactionDTO transactionDTO = mapToOrderTransactionDTO(transaction, optOrders);
            transactionDTOs.add(transactionDTO);
        }

        return transactionDTOs;
    }

    private OrderTransactionDTO mapToOrderTransactionDTO(Transaction transaction,Order order) {
        OrderTransactionDTO transactionDTO = new OrderTransactionDTO();
        transactionDTO.setOrderId(transaction.getOrderId());
        transactionDTO.setAmount(order.getAmount());
        transactionDTO.setDate(order.getDate());
        transactionDTO.setCoupon(transaction.getOrder().getCoupon());
        transactionDTO.setTransactionId(transaction.getTransactionId());
        transactionDTO.setStatus(transaction.getStatus());
        return transactionDTO;
    }


    private boolean couponIsValid(User user, String couponName) {
        if(user.getCoupons() == null || user.getCoupons().isEmpty()){
            user.setCoupons(Collections.singletonList(couponName));
            return true;
        }
        if(user.getCoupons().contains(couponName)){
            return false;
        }else{
            user.getCoupons().add(couponName);
        }
        Coupon tempCoupon = couponService.findByCouponName(couponName);
        if(!tempCoupon.getCouponName().equals(couponName)){
            return false;
        }

        return true;
    }

    private OrderDTO convertOrderToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setUserId(order.getUser().getId());
        orderDTO.setAmount(order.getAmount());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setCoupon(order.getCoupon());

        return orderDTO;
    }
}
