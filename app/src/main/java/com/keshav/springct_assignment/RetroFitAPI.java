package com.keshav.springct_assignment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetroFitAPI {

    @POST("user")
    Call<User> createPost(@Body User user);
}
