package com.kamakarov.musicshelf.gui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kamakarov.musicshelf.R;

import butterknife.ButterKnife;

public class SingerListFragmentBase extends FragmentBase {
    public static SingerListFragmentBase newInstance() {
        Bundle args = new Bundle();

        SingerListFragmentBase fragment = new SingerListFragmentBase();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_singer_list, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
