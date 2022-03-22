package com.example.thangmp3.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thangmp3.R;
import com.example.thangmp3.adapter.ListMusicAdapter;
import com.example.thangmp3.model.Advertise;
import com.example.thangmp3.model.Album;
import com.example.thangmp3.model.Category;
import com.example.thangmp3.model.PlayList;
import com.example.thangmp3.model.Song;
import com.example.thangmp3.service.APIService;
import com.example.thangmp3.service.DataService;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Data
public class ListMusicActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private RecyclerView recyclerViewListMusic;
    private FloatingActionButton floatingActionButton;
    private ImageView imageViewListMusic;

    private Advertise advertise;
    private ArrayList<Song> songs;
    private ListMusicAdapter listMusicAdapter;
    private PlayList playList;
    private Album album;
    private Category category;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_music);

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        dataIntent();
        mapping();
        init();
        checkDataAdvertise();
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }

    private void mapping() {
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolBar);
        toolbar = findViewById(R.id.toolBarList);
        recyclerViewListMusic = findViewById(R.id.recyclerViewPlaylist);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        imageViewListMusic = findViewById(R.id.imageViewLikeSong);
    }

    private void dataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                advertise = intent.getParcelableExtra("banner");
                Toast.makeText(this, advertise.getNameSong(), Toast.LENGTH_SHORT).show();
            }
            if(intent.hasExtra("itemPlaylist")){
                playList = (PlayList) intent.getSerializableExtra("itemPlaylist");
            }
            if(intent.hasExtra("idCategory")){
                category = (Category) intent.getSerializableExtra("idCategory");
            }
            if(intent.hasExtra("album")){
                album = (Album) intent.getSerializableExtra("album");
            }
        }
    }

    private void checkDataAdvertise() {
        if(advertise != null && !advertise.getNameSong().equals("")){
            setValueInView(advertise.getNameSong(), advertise.getImageSong());
            getDataById(advertise.getIdAd());
        }
        if(playList != null && !playList.getName().equals("")){
            setValueInView(playList.getName(), playList.getImagePlayList());
            getDataById(playList.getIdPlayList());
        }
        if (category != null && !category.getNameCategory().equals("")){
            setValueInView(category.getNameCategory(),category.getImageCategory());
            getDataById(category.getIdCategory());
        }
        if(album != null && !album.getNameAlbum().equals("")){
            setValueInView(album.getNameAlbum(),album.getImageAlbum());
            getDataById(album.getIdAlbum());
        }
    }

    private void setValueInView(String name, String image) {
        collapsingToolbarLayout.setTitle(name);
        try {
            URL url = new URL(image);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(image).into(imageViewListMusic);
    }

    private void getDataById(String id) {
        DataService dataservice = APIService.getService();
        Call<List<Song>> callback = dataservice.getPlaylistByAlbum(id);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songs = (ArrayList<Song>) response.body();
                listMusicAdapter = new ListMusicAdapter(ListMusicActivity.this, songs);
                recyclerViewListMusic.setLayoutManager(new LinearLayoutManager(ListMusicActivity.this));
                recyclerViewListMusic.setAdapter(listMusicAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(ListMusicActivity.this, PlayMusicActivity.class);
            intent.putExtra("songs", songs);
            startActivity(intent);
        });
    }
}
