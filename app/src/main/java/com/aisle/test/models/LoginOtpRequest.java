package com.aisle.test.models;

import com.google.gson.annotations.SerializedName;

public class LoginOtpRequest {

    public LoginOtpRequest(String phone, String otp) {
        this.number = "+91" + phone;
        this.otp = otp;
    }
    @SerializedName("number")
    private String number;
    @SerializedName("otp")
    private String otp;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
