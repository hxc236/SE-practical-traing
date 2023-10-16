package com.example.shop.service;

public interface VerifyService {
    public void sendVerifyCode(String email);

    public boolean doVerify(String email, String code);
}
