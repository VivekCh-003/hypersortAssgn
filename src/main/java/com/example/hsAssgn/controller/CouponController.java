package com.example.hsAssgn.controller;

import com.example.hsAssgn.entity.Coupon;
import com.example.hsAssgn.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CouponController {
    @Autowired
    private CouponService couponService;

    @GetMapping("/fetchCoupons")
    public ResponseEntity<List<Coupon>> fetchCoupons(){
        List<Coupon> coupons = couponService.findAllCoupons();

        return ResponseEntity.ok(coupons);
    }

    @GetMapping("/getCoupon")
    public Coupon getCoupon(@RequestParam String couponName){
        return couponService.findByCouponName(couponName);
    }
}
