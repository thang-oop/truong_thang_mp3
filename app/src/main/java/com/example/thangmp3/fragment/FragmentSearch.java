package com.example.thangmp3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thangmp3.R;
import com.example.thangmp3.adapter.SearchSongAdapter;
import com.example.thangmp3.model.Song;
import com.example.thangmp3.service.APIService;
import com.example.thangmp3.service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSearch extends Fragment {

    private View view;

    private Toolbar toolbar;

    private RecyclerView recyclerViewSearchSong;

    private TextView dataNull;

    private SearchSongAdapter searchSongAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        toolbar = view.findViewById(R.id.toolBarSearchSong);
        recyclerViewSearchSong = view.findViewById(R.id.recyclerViewSearchSong);
//        dataNull = view.findViewById(R.id.textViewNoData);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchSong(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void searchSong(String query) {
        DataService dataservice = APIService.getService();
        retrofit2.Call<List<Song>> callback = dataservice.getSearchSong(query);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Song>> call, Response<List<Song>> response) {
                ArrayList<Song> mangbaihat = (ArrayList<Song>) response.body();
                if (mangbaihat.size() > 0) {
                    searchSongAdapter = new SearchSongAdapter(getActivity(), mangbaihat);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewSearchSong.setLayoutManager(linearLayoutManager);
                    recyclerViewSearchSong.setAdapter(searchSongAdapter);
                    dataNull.setVisibility(View.GONE);
                    recyclerViewSearchSong.setVisibility(View.VISIBLE);
                } else {
                    recyclerViewSearchSong.setVisibility(View.GONE);
                    dataNull.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }
}
