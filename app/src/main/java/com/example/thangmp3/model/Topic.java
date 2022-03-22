package com.example.thangmp3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Topic implements Serializable {

    @SerializedName("IdChuDe")
    @Expose
    private String idTopic;

    @SerializedName("TenChuDe")
    @Expose
    private String nameTopic;

    @SerializedName("HinhChuDe")
    @Expose
    private String imageTopic;
}
