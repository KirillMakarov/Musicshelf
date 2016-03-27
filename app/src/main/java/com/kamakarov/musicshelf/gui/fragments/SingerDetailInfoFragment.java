package com.kamakarov.musicshelf.gui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kamakarov.musicshelf.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public final class SingerDetailInfoFragment extends FragmentBase {
    private static final String ID_KEY = "id_key";

    public static SingerDetailInfoFragment newInstance(int smth) {

        Bundle args = new Bundle();
        args.putInt(ID_KEY, smth);

        SingerDetailInfoFragment fragment = new SingerDetailInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Bind(R.id.id_textview_fragment_singer_info)
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_singer_info, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        int id = bundle.getInt(ID_KEY);
        textView.setText("Now id: " + id);
    }
}
