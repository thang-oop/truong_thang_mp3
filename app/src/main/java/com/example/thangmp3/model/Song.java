package com.example.thangmp3.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Song implements Parcelable {

    @SerializedName("IdSong")
    @Expose
    private String idSong;

    @SerializedName("NameSong")
    @Expose
    private String nameSong;

    @SerializedName("ImageSong")
    @Expose
    private String imageSong;

    @SerializedName("Singer")
    @Expose
    private String singer;

    @SerializedName("LinkSong")
    @Expose
    private String linkSong;

    @SerializedName("Likes")
    @Expose
    private int likes;

    protected Song(Parcel in) {
        idSong = in.readString();
        nameSong = in.readString();
        imageSong = in.readString();
        singer = in.readString();
        linkSong = in.readString();
        likes = in.readInt();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idSong);
        dest.writeString(nameSong);
        dest.writeString(imageSong);
        dest.writeString(singer);
        dest.writeString(linkSong);
        dest.writeInt(likes);
    }
}