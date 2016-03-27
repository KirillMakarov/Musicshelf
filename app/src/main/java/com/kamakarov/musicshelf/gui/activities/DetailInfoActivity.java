package com.kamakarov.musicshelf.gui.activities;

import android.support.v4.app.Fragment;

import com.kamakarov.musicshelf.gui.fragments.SingerDetailInfoFragment;

public final class DetailInfoActivity extends SingleFragmentActivity {

    public static final String ID_KEY = "id_key_activity";

    @Override
    protected Fragment createFragment() {
        int id = getIntent().getIntExtra(ID_KEY, 0);
        return SingerDetailInfoFragment.newInstance(id);
    }
}
