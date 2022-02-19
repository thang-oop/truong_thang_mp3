package com.example.thangmp3.service;

import com.example.thangmp3.model.Song;
import com.example.thangmp3.model.Advertise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

    @GET("/thangtv/server/songBanner.php")
    Call<List<Advertise>> getDataBanner();

    @FormUrlEncoded
    @POST("server/searchbaihat.php")
    Call<List<Song>> GetSearchBaihat(@Field("tukhoa") String tukhoa);
}
