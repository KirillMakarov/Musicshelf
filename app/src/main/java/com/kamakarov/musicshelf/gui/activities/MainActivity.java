package com.kamakarov.musicshelf.gui.activities;

import android.support.v4.app.Fragment;

import com.kamakarov.musicshelf.gui.fragments.SingerListFragmentBase;

public final class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return SingerListFragmentBase.newInstance();
    }
}