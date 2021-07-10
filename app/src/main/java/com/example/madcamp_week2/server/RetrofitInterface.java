package com.example.madcamp_week2.server;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);
/*
    @POST("/test/signup")
    Call<Void> executeSignup(@Body HashMap<String, String> map);
*/
    @POST("/post/add")
    Call<Void> executePostAdd(@Body HashMap<String,String> map);

    @POST("/user/add")
    Call<Void> executeSingup(@Body HashMap<String,String> map);



    @GET("/rest/getall")
    Call<List<RestResult>> executeGetAllRest();
//    Call<RestResult> executeGetAllRest();
}
