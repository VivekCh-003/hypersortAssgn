package com.example.hsAssgn.repository;

import com.example.hsAssgn.entity.Order;
import com.example.hsAssgn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
}
