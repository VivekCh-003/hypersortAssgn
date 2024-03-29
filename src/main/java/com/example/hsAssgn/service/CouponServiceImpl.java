package com.example.hsAssgn.service;

import com.example.hsAssgn.entity.Coupon;
import com.example.hsAssgn.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponRepository couponRepository;

    @Override
    public List<Coupon> findAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public Coupon findByCouponName(String couponName) {
        return couponRepository.findByCouponName(couponName);
    }
}
