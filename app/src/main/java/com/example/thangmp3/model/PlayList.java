package com.example.thangmp3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class PlayList implements Serializable {

    @SerializedName("IdPlaylist")
    @Expose
    private String idPlayList;

    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("ImagePlayList")
    @Expose
    private String imagePlayList;

    @SerializedName("Icon")
    @Expose
    private String icon;
}
