package com.aisle.test.network;

import android.content.SharedPreferences;

import com.aisle.test.Constant;
import com.aisle.test.models.LoginOtpRequest;
import com.aisle.test.models.LoginOtpResponse;
import com.aisle.test.models.LoginPhoneRequest;
import com.aisle.test.models.LoginPhoneResponse;
import com.aisle.test.models.ProfilesBaseResponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class ApiRepository {

    private final ApiInterface apiInterface;
    private final SharedPreferences pref;

    @Inject
    public ApiRepository(ApiInterface apiInterface, SharedPreferences sharedPreferences) {
        this.apiInterface = apiInterface;
        this.pref = sharedPreferences;
    }

    public Single<LoginPhoneResponse> getOtp(LoginPhoneRequest request) {
        return apiInterface.getOtp(request);
    }

    public Single<LoginOtpResponse> verifyOtp(LoginOtpRequest request) {
        return apiInterface.verifyOtp(request);
    }

    public void setToken(String userToken) {
        pref.edit().putString(Constant.PREF_TOKEN, userToken).apply();
    }

    public String getToken(){
        return pref.getString(Constant.PREF_TOKEN, null);
    }

    public Single<ProfilesBaseResponse> getProfiles() {
        return apiInterface.getProfileList(getToken());
    }
}
