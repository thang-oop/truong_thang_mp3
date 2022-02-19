package com.example.thangmp3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class Advertise implements Serializable {

    @SerializedName("IdAd")
    @Expose
    private String idAd;

    @SerializedName("Image")
    @Expose
    private String image;

    @SerializedName("Content")
    @Expose
    private String content;

    @SerializedName("IdSong")
    @Expose
    private String idSong;

    @SerializedName("NameSong")
    @Expose
    private String nameSong;

    @SerializedName("ImageSong")
    @Expose
    private String imageSong;
}
