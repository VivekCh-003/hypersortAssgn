package com.example.hsAssgn.service;

import com.example.hsAssgn.entity.Order;
import com.example.hsAssgn.entity.Transaction;
import com.example.hsAssgn.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private OrderService orderService;

    @Override
    public ResponseEntity<Transaction> makePayment(Long userId, Long orderId, Double amount) {
        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setOrderId(orderId);

        transactionRepository.save(transaction);

        String transactionId = generateTransactionId(userId,orderId,transaction.getId());
        transaction.setTransactionId(transactionId);

        Optional<Order> order = orderService.getOrderById(orderId);
        if(order == null){
            return ResponseEntity.notFound().build();
        }

        if ("paid".equals(order.get().getStatus())){
            transaction.setStatus("failed");
            transaction.setDescription("Order is already paid for");
            transactionRepository.save(transaction);
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(transaction);
        }

        int paymentStatus = mockPayment();
        if(paymentStatus == 200){
            transaction.setStatus("successful");
            transaction.setDescription("payment successful");
            transactionRepository.save(transaction);
            return ResponseEntity.ok(transaction);
        } else if (paymentStatus == 504) {
            transaction.setStatus("failed");
            transaction.setDescription("No response from payment server");

            transactionRepository.save(transaction);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(transaction);
        } else if (paymentStatus == 405) {
            transaction.setStatus("failed");
            transaction.setDescription("Order is already paid for");

            transactionRepository.save(transaction);
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(transaction);
        } else if (paymentStatus == 400) {
            transaction.setStatus("failed");
            transaction.setDescription("payment failed");

            transactionRepository.save(transaction);
            return ResponseEntity.badRequest().body(transaction);
        }

        return ResponseEntity.ok(transaction);

    }


    private int mockPayment() {
        Random random = new Random();
        int status = random.nextInt(5); // Generates values 0 to 3
        return switch (status) {
            case 0 -> 200; // Successful payment
            case 1 -> 400; // Payment failed due to invalid amount
            case 2 -> 504; // No response from payment server
            case 3 -> 405; // order is already paid for
            default -> 400; // Other payment failures
        };
    }

    private String generateTransactionId(Long userId, Long orderId,Long transactionId) {
        return "tran0"+ userId + "0" + orderId + "00" + transactionId;
    }
}
