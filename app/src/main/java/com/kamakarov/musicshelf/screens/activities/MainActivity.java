package com.kamakarov.musicshelf.screens.activities;

import android.os.Bundle;

import com.kamakarov.musicshelf.R;

import butterknife.ButterKnife;

public final class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}