package com.example.hsAssgn.service;

import com.example.hsAssgn.entity.Order;
import com.example.hsAssgn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

}
