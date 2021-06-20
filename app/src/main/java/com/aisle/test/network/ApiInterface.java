package com.aisle.test.network;

import com.aisle.test.models.LoginOtpRequest;
import com.aisle.test.models.LoginOtpResponse;
import com.aisle.test.models.LoginPhoneRequest;
import com.aisle.test.models.LoginPhoneResponse;
import com.aisle.test.models.ProfilesBaseResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("phone_number_login")
    Single<LoginPhoneResponse> getOtp(@Body LoginPhoneRequest request);

    @Headers("Content-Type: application/json")
    @POST("verify_otp")
    Single<LoginOtpResponse> verifyOtp(@Body LoginOtpRequest request);

    @Headers("Content-Type: application/json")
    @GET("test_profile_list")
    Single<ProfilesBaseResponse> getProfileList(@Header("Authorization") String token);

}
