package com.kamakarov.musicshelf.gui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.kamakarov.musicshelf.R;
import com.kamakarov.musicshelf.gui.fragments.SingerListFragment;

public final class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return SingerListFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.artists_label);
    }
}