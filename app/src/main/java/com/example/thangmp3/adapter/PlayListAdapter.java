package com.example.thangmp3.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thangmp3.R;
import com.example.thangmp3.model.PlayList;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayListAdapter extends ArrayAdapter<PlayList> {

    public PlayListAdapter(@NonNull Context context, int resource, @NonNull List<PlayList> objects) {
        super(context, resource, objects);
    }

    public class ViewHolder {
        private TextView namePLayList;
        private ImageView imgBackGround;
        private ImageView imgPlayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_playlist,null);

            viewHolder = new ViewHolder();
            viewHolder.namePLayList = convertView.findViewById(R.id.textViewNamePlaylist);
            viewHolder.imgBackGround = convertView.findViewById(R.id.imageViewBackgroundPlaylist);
            viewHolder.imgPlayList = convertView.findViewById(R.id.imageViewRowPlaylist);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PlayList playlist = getItem(position);
        Picasso.with(getContext()).load(playlist.getImagePlayList()).into(viewHolder.imgBackGround);
        Picasso.with(getContext()).load(playlist.getIcon()).into(viewHolder.imgPlayList);
        viewHolder.namePLayList.setText(playlist.getName());

        return convertView;
    }
}
