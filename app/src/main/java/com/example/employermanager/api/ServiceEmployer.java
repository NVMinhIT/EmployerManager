package com.example.employermanager.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceEmployer {
    private static final String BASE_URL = "http://dummy.restapiexample.com/api/v1/";
    private static Retrofit retrofit;
//    static Gson gsonBuilder = new
//            GsonBuilder().setLenient().create();

    private static OkHttpClient.Builder okHttpClientBuilder =
            new OkHttpClient.Builder();
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());
    private static OkHttpClient okHttpClient = okHttpClientBuilder.build();

    public static <T> T createService(Class<T> serviceClass) {
        if (retrofit == null) {
            retrofit = builder.client(okHttpClient).
                    build();
        }
        return retrofit.create(serviceClass);
    }
}
