package com.kamakarov.musicshelf.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;

import com.kamakarov.musicshelf.gui.activities.DetailInfoActivity;
import com.kamakarov.musicshelf.model.Singer;

public class IntentManagerImpl implements IIntentManager {
    @Override
    public void openDetailedInfo(Context activity, Singer singer) {
        openDetailedInfoWithAnimation(activity, singer, null);
    }

    @Override
    public void openDetailedInfoWithAnimation(Context activity, Singer singer, ActivityOptionsCompat activityOptionsCompat) {
        Intent intent = new Intent(activity, DetailInfoActivity.class);
        intent.putExtra(DetailInfoActivity.SINGER_OBJECT_KEY, singer);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && activityOptionsCompat != null) {
            activity.startActivity(intent, activityOptionsCompat.toBundle());
        } else {
            activity.startActivity(intent);
        }
    }
}
