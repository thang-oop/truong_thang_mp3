package com.example.thangmp3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class Album implements Serializable {

    @SerializedName("IdAlbum")
    @Expose
    private String idAlbum;

    @SerializedName("NameAlbum")
    @Expose
    private String nameAlbum;

    @SerializedName("NameSingerAlbum")
    @Expose
    private String nameSingerAlbum;

    @SerializedName("ImageAlbum")
    @Expose
    private String imageAlbum;
}
