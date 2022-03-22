package com.example.thangmp3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class Category implements Serializable {

    @SerializedName("IdCategory")
    @Expose
    private String idCategory;

    @SerializedName("IdTopic")
    @Expose
    private String idTopic;

    @SerializedName("NameCategory")
    @Expose
    private String nameCategory;

    @SerializedName("ImageCategory")
    @Expose
    private String imageCategory;

}
