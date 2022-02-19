package com.example.thangmp3.service;

public class APIService {

    private static String base_url = "https://truongvanthangshop.000webhostapp.com/";

    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
