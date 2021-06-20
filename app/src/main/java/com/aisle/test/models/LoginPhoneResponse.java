package com.aisle.test.models;

import com.google.gson.annotations.SerializedName;

public class LoginPhoneResponse {
    @SerializedName("status")
    private boolean status;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
