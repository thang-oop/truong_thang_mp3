package com.example.thangmp3.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thangmp3.R;
import com.example.thangmp3.activity.ListMusicActivity;
import com.example.thangmp3.model.Song;
import com.example.thangmp3.service.APIService;
import com.example.thangmp3.service.DataService;

import java.util.List;

import lombok.Data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Data
public class ListMusicAdapter extends RecyclerView.Adapter<ListMusicAdapter.DataViewHolder> {

    private Context context;
    private List<Song> songs;

    public ListMusicAdapter(Context context, List<Song> songs) {
        this.context = context;
        this.songs = songs;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_list_song, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Song song = songs.get(position);
        holder.textViewNameSong.setText(song.getNameSong());
        holder.textViewSinger.setText(song.getSinger());
        holder.textViewIndex.setText(position + 1 + "");
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView textViewIndex, textViewNameSong, textViewSinger;
        ImageView imageLikes;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSinger = itemView.findViewById(R.id.textViewNameSinger);
            textViewIndex = itemView.findViewById(R.id.textViewIndex);
            textViewNameSong = itemView.findViewById(R.id.textViewNameSong);
            imageLikes = itemView.findViewById(R.id.imageViewLikeSong);
            imageLikes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageLikes.setImageResource(R.drawable.iconloved);
                    DataService dataservice = APIService.getService();
                    Call<String> callback = dataservice.updateLikes(1,songs.get(getPosition()).getIdSong());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("ok")){
                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Lỗi!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imageLikes.setEnabled(false);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListMusicActivity.class);
                    intent.putExtra("song", songs.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }

    }
}
