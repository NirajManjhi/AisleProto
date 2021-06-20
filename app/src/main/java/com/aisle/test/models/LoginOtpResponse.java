package com.aisle.test.models;

import com.google.gson.annotations.SerializedName;

public class LoginOtpResponse {
    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
