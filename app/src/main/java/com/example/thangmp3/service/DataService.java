package com.example.thangmp3.service;

import com.example.thangmp3.model.Baihat;
import com.example.thangmp3.model.Quangcao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

    @GET("server/songBanner.php")
    Call<List<Quangcao>> getDataBanner();

    @FormUrlEncoded
    @POST("server/searchbaihat.php")
    Call<List<Baihat>> GetSearchBaihat(@Field("tukhoa") String tukhoa);
}
