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
import com.example.thangmp3.model.Baihat;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchSongAdapter extends RecyclerView.Adapter<SearchSongAdapter.ViewHolder> {

    private Context context;

    List<Baihat> listSongs; //TODO edit name

    public SearchSongAdapter(Context context, List<Baihat> listSongs) {
        this.context = context;
        this.listSongs = listSongs;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_search_song,parent, false); //TODO row search song
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat = listSongs.get(position);
        holder.textViewNameSong.setText(baihat.getTenbaihat());
        holder.textViewNameSinger.setText(baihat.getCasi());
        Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.imageViewSong);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewNameSong, textViewNameSinger;

        private ImageView imageViewSong, imageViewLike;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNameSong = itemView.findViewById(R.id.textViewSearchNameSong);
            textViewNameSinger = itemView.findViewById(R.id.textViewSearchNameSinger);
            imageViewSong = itemView.findViewById(R.id.imageSearchSong);
            imageViewLike = itemView.findViewById(R.id.imageViewSearchLike);

            //TODO
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    if(mediaPlayer.isPlaying()){
////                        mediaPlayer.stop();
////                    }else {
//                    Intent intent = new Intent(context, PlayNhacActivity.class);
//                    intent.putExtra("cakhuc", mangbaihat.get(getPosition()));
//                    context.startActivity(intent);
//                }
////                }
//            });
        }


    }
}
