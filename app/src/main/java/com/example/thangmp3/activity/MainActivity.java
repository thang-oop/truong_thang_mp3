package com.example.thangmp3.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.thangmp3.R;
import com.example.thangmp3.adapter.MainViewPagerAdapter;
import com.example.thangmp3.fragment.FragmentHome;
import com.example.thangmp3.fragment.FragmentSearch;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();
        init();
    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new FragmentHome(), "Home");
        mainViewPagerAdapter.addFragment(new FragmentSearch(), "Search");

        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.homeicon3);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearchsong);
    }

    private void mapping() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
}