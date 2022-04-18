package com.example.thangmp3.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thangmp3.R;
import com.example.thangmp3.adapter.AllAlbumAdapter;
import com.example.thangmp3.model.Album;
import com.example.thangmp3.service.*;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAllAlbumActivity extends AppCompatActivity {
    RecyclerView recyclerViewAllAlbum;
    Toolbar toolbarAllAlbum;
    AllAlbumAdapter allAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_album);
        init();
        GetData();
    }

    private void GetData() {
        DataService dataservice = APIService.getService();
        Call<List<Album>> callback = dataservice.getAllAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> mangalbum = (ArrayList<Album>) response.body();
                allAlbumAdapter = new AllAlbumAdapter(ListAllAlbumActivity.this,mangalbum);
                recyclerViewAllAlbum.setLayoutManager(new GridLayoutManager(ListAllAlbumActivity.this,2));
                recyclerViewAllAlbum.setAdapter(allAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewAllAlbum = findViewById(R.id.recyclerViewAllAlbum);
        toolbarAllAlbum = findViewById(R.id.toolBarAllAlbum);
        setSupportActionBar(toolbarAllAlbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Các Album");
        toolbarAllAlbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
