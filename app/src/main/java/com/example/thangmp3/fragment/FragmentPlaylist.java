package com.example.thangmp3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.thangmp3.R;
import com.example.thangmp3.activity.ListMusicActivity;
import com.example.thangmp3.activity.ListOfPlaylistActivity;
import com.example.thangmp3.adapter.PlayListAdapter;
import com.example.thangmp3.model.PlayList;
import com.example.thangmp3.service.APIService;
import com.example.thangmp3.service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPlaylist extends Fragment {

    private View view;
    private ListView listViewPlayList;
    private TextView titlePLayList;
    private TextView textViewSeeMore;
    private PlayListAdapter playListAdapter;
    private List<PlayList> arrayPlaylist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist,container,false);
        listViewPlayList = view.findViewById(R.id.listViewPlaylist);
        titlePLayList = view.findViewById(R.id.titlePLayList);
        textViewSeeMore= view.findViewById(R.id.textViewMorePlaylist);
        GetData();
        textViewSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListOfPlaylistActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void GetData() {
        DataService dataservice = APIService.getService();
        Call<List<PlayList>> callback = dataservice.getPlaylistCurrentDay();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                arrayPlaylist = (ArrayList<PlayList>) response.body();
                Log.d("AAA", arrayPlaylist.get(0).getName());
                playListAdapter = new PlayListAdapter(getActivity(),android.R.layout.simple_list_item_1, arrayPlaylist);
                listViewPlayList.setAdapter(playListAdapter);
                setListViewHeightBasedOnChildren(listViewPlayList);
                listViewPlayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), ListMusicActivity.class);
                        intent.putExtra("itemPlaylist", arrayPlaylist.get(position));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
