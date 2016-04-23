package com.kamakarov.musicshelf.gui;

import android.content.Context;
import android.support.v4.app.ActivityOptionsCompat;

import com.kamakarov.musicshelf.model.Singer;

public interface IIntentManager {
    void openDetailedInfo(Context activity, Singer singer);

    void openDetailedInfoWithAnimation(Context activity, Singer singer, ActivityOptionsCompat activityOptionsCompat);
}
