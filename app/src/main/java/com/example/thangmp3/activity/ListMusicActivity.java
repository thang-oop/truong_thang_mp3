package com.example.thangmp3.activity;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thangmp3.model.Advertise;
import com.example.thangmp3.model.Song;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import lombok.Data;

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
}
