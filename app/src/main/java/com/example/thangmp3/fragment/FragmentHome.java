package com.example.thangmp3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.thangmp3.R;

public class FragmentHome extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, Bundle saveInstanceState) {
        view = layoutInflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }
}
