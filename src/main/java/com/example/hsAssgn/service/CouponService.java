package com.example.hsAssgn.service;

import com.example.hsAssgn.entity.Coupon;

import java.util.List;

public interface CouponService {
    List<Coupon> findAllCoupons();

    Coupon findByCouponName(String couponName);
}
