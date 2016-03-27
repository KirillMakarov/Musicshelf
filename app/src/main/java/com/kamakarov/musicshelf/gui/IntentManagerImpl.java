package com.kamakarov.musicshelf.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.kamakarov.musicshelf.gui.activities.DetailInfoActivity;

public class IntentManagerImpl implements IIntentManager {
    @Override
    public void openDetailedInfo(Context activity) {
        Intent intent = new Intent(activity, DetailInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(DetailInfoActivity.ID_KEY, 100500);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
}
