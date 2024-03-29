package com.example.hsAssgn.repository;

import com.example.hsAssgn.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("SELECT SUM(o.quantity) FROM Order o")
    Integer getTotalOrderQuantity();

    List<Order> findByUserId(Long userId);

    Order findByUserIdAndOrderId(Long userId, Long orderId);
}
