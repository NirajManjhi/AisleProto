package com.aisle.test.models;

import com.google.gson.annotations.SerializedName;

public class LoginPhoneRequest {

    public LoginPhoneRequest(String phone) {
        this.number = "+91" + phone;
    }
    @SerializedName("number")
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
