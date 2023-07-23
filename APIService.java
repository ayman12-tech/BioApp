package com.example.test.RestApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @FormUrlEncoded
    @POST("/api/Users/authenticate")
        @Headers({
                "Accept: application/json",
                "Content-Type: application/json",
        })
    Call<ResponseBody> getToken(
                                @Field("Name") String name,
                                @Field("Password") String password);
}
