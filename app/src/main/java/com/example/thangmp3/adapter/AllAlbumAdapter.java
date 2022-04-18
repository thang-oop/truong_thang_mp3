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
import com.example.thangmp3.model.Album;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllAlbumAdapter extends RecyclerView.Adapter<AllAlbumAdapter.AllAlbumViewHolder> {

    Context context;
    ArrayList<Album> albumArrayList;

    public AllAlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public AllAlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_all_album,parent,false);
        return new AllAlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllAlbumViewHolder holder, int position) {
        Album album = albumArrayList.get(position);
        Picasso.with(context).load(album.getNameAlbum()).into(holder.imageViewAlbum);
        holder.txtNameAlbum.setText(album.getNameAlbum());
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class AllAlbumViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewAlbum;
        TextView txtNameAlbum;
        public AllAlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewAlbum = itemView.findViewById(R.id.imageViewAllAlbum);
            txtNameAlbum = itemView.findViewById(R.id.textViewNameAllAlbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListMusicAdapter.class);
                    intent.putExtra("album", albumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
