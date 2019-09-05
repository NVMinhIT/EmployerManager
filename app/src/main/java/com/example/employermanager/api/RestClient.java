package com.example.employermanager.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static IEmployer service;

    public static IEmployer getClient() {
        if (service == null) {
            Gson gson = new GsonBuilder()
                    //.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();

            // Add logging into retrofit 2.0
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.interceptors().add(logging);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://dummy.restapiexample.com/api/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build()).build();

            service = retrofit.create(IEmployer.class);
        }
        return service;
    }
}
