package com.kamakarov.musicshelf.gui;

import android.content.Context;
import android.content.Intent;

import com.kamakarov.musicshelf.gui.activities.DetailInfoActivity;
import com.kamakarov.musicshelf.model.Singer;

public class IntentManagerImpl implements IIntentManager {
    @Override
    public void openDetailedInfo(Context activity, Singer singer) {
        Intent intent = new Intent(activity, DetailInfoActivity.class);
        intent.putExtra(DetailInfoActivity.SINGER_OBJECT_KEY, singer);
        activity.startActivity(intent);
    }
}
