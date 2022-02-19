package com.example.thangmp3.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.thangmp3.R;
import com.example.thangmp3.adapter.BannerAdapter;
import com.example.thangmp3.model.Advertise;
import com.example.thangmp3.service.APIService;
import com.example.thangmp3.service.DataService;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBanner extends Fragment {

    private View view;

    private ViewPager viewPager;

    private CircleIndicator circleIndicator;

    private BannerAdapter bannerAdapter;

    private Runnable runnable;

    private Handler handler;

    private int currentItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);
        mapping();
        getData();
        return view;
    }

    private void mapping() {
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.indicatorDefault);
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<Advertise>> callback = dataService.getDataBanner();

        callback.enqueue(new Callback<List<Advertise>>() {
            @Override
            public void onResponse(Call<List<Advertise>> call, Response<List<Advertise>> response) {
                ArrayList<Advertise> banners = (ArrayList<Advertise>) response.body();

                if(banners != null) {
                    bannerAdapter = new BannerAdapter(getActivity(), banners);
                    viewPager.setAdapter(bannerAdapter);
                    circleIndicator.setViewPager(viewPager);
                    //tu dong chuyen sang quang cao khac
                    handler = new android.os.Handler();
                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            currentItem = viewPager.getCurrentItem();
                            currentItem++;
                            if (currentItem >= viewPager.getAdapter().getCount()) {
                                currentItem = 0; //neu next den quang cao cuoi cung thi quay lai dau tien
                            }
                            viewPager.setCurrentItem(currentItem, true);
                            handler.postDelayed(runnable, 3000); //sau 4s next 1 lan
                        }
                    };
                    handler.postDelayed(runnable, 3000);
                }
            }

            @Override
            public void onFailure(Call<List<Advertise>> call, Throwable t) {
            }
        });
    }
}
