package com.kamakarov.musicshelf.gui.activities;

import android.support.v4.app.Fragment;

import com.kamakarov.musicshelf.gui.fragments.SingerDetailInfoFragment;
import com.kamakarov.musicshelf.model.Singer;

public final class DetailInfoActivity extends SingleFragmentActivity {

    public static final String SINGER_OBJECT_KEY = "singer_object_key";

    @Override
    protected Fragment createFragment() {
        Singer singer = getIntent().getParcelableExtra(SINGER_OBJECT_KEY);
        return SingerDetailInfoFragment.newInstance(singer);
    }
}
