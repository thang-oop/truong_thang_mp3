package com.example.thangmp3.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thangmp3.R;
import com.example.thangmp3.activity.ListMusicActivity;
import com.example.thangmp3.model.PlayList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListOfPlaylistsAdapter extends RecyclerView.Adapter<ListOfPlaylistsAdapter.ViewHolder> {

    Context context;
    ArrayList<PlayList> playLists;

    public ListOfPlaylistsAdapter(Context context, ArrayList<PlayList> playLists) {
        this.context = context;
        this.playLists = playLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_list_of_playlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlayList playlist = playLists.get(position);
        Picasso.with(context).load(playlist.getImagePlayList()).into(holder.imghinhnen);
        holder.txttenplaylist.setText(playlist.getName());
    }

    @Override
    public int getItemCount() {
        return playLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imghinhnen;
        TextView txttenplaylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhnen = itemView.findViewById(R.id.imageviewdanhsachcacplaylist);
            txttenplaylist = itemView.findViewById(R.id.textviewtendanhsachcacplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListMusicActivity.class);
                    for (int i = 0; i < playLists.size() ; i++) {
                        intent.putExtra("itemPlaylist",playLists.get(i));
                    }
                    intent.putExtra("itemPlaylist", playLists.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
