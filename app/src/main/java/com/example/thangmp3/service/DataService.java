package com.example.thangmp3.service;

import com.example.thangmp3.model.Album;
import com.example.thangmp3.model.PlayList;
import com.example.thangmp3.model.Song;
import com.example.thangmp3.model.Advertise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

    @GET("thangtv/server/songBanner.php")
    Call<List<Advertise>> getDataBanner();

    @FormUrlEncoded
    @POST("thangtv/server/searchSong.php")
    Call<List<Song>> getSearchSong(@Field("key") String key);

    @FormUrlEncoded
    @POST("thangtv/server/updateLikes.php")
    Call<String> updateLikes(@Field("likes") int likes,
                                 @Field("idSong") String idSong);

    @FormUrlEncoded
    @POST("thangtv/server/playlist.php")
    Call<List<Song>> getPlaylistByAdvertise(@Field("idAdvertise") String idAdvertise);

    @FormUrlEncoded
    @POST("thangtv/server/playlist.php")
    Call<List<Song>> getPlaylistByCategory(@Field("idCategory") String idCategory);

    @FormUrlEncoded
    @POST("thangtv/server/playlist.php")
    Call<List<Song>> getPlaylistByAlbum(@Field("idAlbum") String idAlbum);

    @FormUrlEncoded
    @POST("thangtv/server/playlist.php")
    Call<List<Song>> getPlaylistByPlaylist(@Field("idPlaylist") String idPlaylist);

    @GET("thangtv/server/playListForCurrentDay.php")
    Call<List<PlayList>> getPlaylistCurrentDay();

    @GET("server/getListOfPlaylist.php")
    Call<List<PlayList>> getListOfPlaylist();

    @GET("thangtv/server/getAllAlbum.php")
    Call<List<Album>> getAllAlbum();

    @GET("thangtv/server/albumHot.php")
    Call<List<Album>> getAlbumHot();

}
