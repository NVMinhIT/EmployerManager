package com.example.employermanager.api;

import com.example.employermanager.api.response.DeleteResult;
import com.example.employermanager.api.response.EmployBody;
import com.example.employermanager.api.response.Employer;
import com.example.employermanager.api.response.ResponseEmployee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IEmployer {
    //@Headers("Content-Type: application/json ")
    @GET("employees")
    Call<List<Employer>> getResultEmployer();


    @Headers("Content-Type: application/json ")
    @DELETE("delete/{id}")
    Call<DeleteResult> getAllEmployAfterDelete(@Path("id") String Id);


    @Headers("Content-Type: application/json ")
    @POST("create")
    Call<ResponseEmployee> getAllEmployAfterCreate(@Body EmployBody employBody);

    @Headers("Content-Type: application/json ")
    @PUT("update/{id}")
    Call<EmployBody> getEmployUpdate(@Path("id") String id, @Body EmployBody employBody);
}
