package com.kamakarov.musicshelf.gui;

import android.content.Context;

import com.kamakarov.musicshelf.model.Singer;

public interface IIntentManager {
    void openDetailedInfo(Context activity, Singer singer);
}
