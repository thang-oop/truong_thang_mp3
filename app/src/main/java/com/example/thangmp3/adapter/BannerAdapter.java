package com.example.thangmp3.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.thangmp3.R;
import com.example.thangmp3.activity.ListMusicActivity;
import com.example.thangmp3.model.Advertise;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BannerAdapter extends PagerAdapter {

    private Context context;

    private List<Advertise> listBanner;

    public BannerAdapter(Context context, ArrayList<Advertise> listBanner) {
        this.context = context;
        this.listBanner = listBanner;
    }

    @Override
    public int getCount() {
        return listBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_banner,null);

        ImageView imageViewBackgroundBanner = view.findViewById(R.id.imageViewBackgroundBanner);
        ImageView imageViewSongBanner = view.findViewById(R.id.imageViewBanner);
        TextView textViewTitleBannerSong = view.findViewById(R.id.textViewTitleBannerSong);
        TextView textViewContent = view.findViewById(R.id.textViewContent);

        Picasso.with(context).load(listBanner.get(position).getImage()).into(imageViewBackgroundBanner);
        Picasso.with(context).load(listBanner.get(position).getImageSong()).into(imageViewSongBanner);

        textViewTitleBannerSong.setText(listBanner.get(position).getNameSong());
        textViewContent.setText(listBanner.get(position).getContent());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListMusicActivity.class);
                intent.putExtra("banner", listBanner.get(position));
                context.startActivity(intent);
            }
        });
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
