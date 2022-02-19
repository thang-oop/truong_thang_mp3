package com.example.thangmp3.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String base_url) {//truyền url để cấu hình được retrofit
        OkHttpClient okHttpClient = new OkHttpClient.Builder()// tương tác mạng với server
                .readTimeout(100000, TimeUnit.MILLISECONDS)
                .writeTimeout(100000, TimeUnit.MILLISECONDS)
                .connectTimeout(100000, TimeUnit.MILLISECONDS)//ngắt kết nối nếu quá 10s
                .retryOnConnectionFailure(true)//cố kết nối khi mạng lỗi
//                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .build();

        //Convert json sang biến của java
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }
}
