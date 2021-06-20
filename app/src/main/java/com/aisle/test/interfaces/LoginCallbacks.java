package com.aisle.test.interfaces;

public interface LoginCallbacks {
    void onPhoneSubmitted(String phone);
    void onOtpSubmitted(String phone, String otp);
}
