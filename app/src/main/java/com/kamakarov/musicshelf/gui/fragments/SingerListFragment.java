package com.kamakarov.musicshelf.gui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kamakarov.musicshelf.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SingerListFragment extends FragmentBase {
    public static SingerListFragment newInstance() {
        Bundle args = new Bundle();

        SingerListFragment fragment = new SingerListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Bind(R.id.click_button_fragment_singer)
    Button button;

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
        button.setOnClickListener(v -> intentManager.openDetailedInfo(getActivity()));
    }
}
