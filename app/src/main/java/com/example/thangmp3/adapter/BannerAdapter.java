package com.example.thangmp3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.thangmp3.R;
import com.example.thangmp3.model.Quangcao;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {

    private Context context;

    private ArrayList<Quangcao> listBanner;

    public BannerAdapter(Context context, ArrayList<Quangcao> listBanner) {
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
        View view = inflater.inflate(R.layout.c)
        return super.instantiateItem(container, position);
    }
}
