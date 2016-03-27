package com.kamakarov.musicshelf.core;

import com.kamakarov.musicshelf.gui.activities.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MusicshelfModule.class)
public interface CoreComponent {
    void inject(BaseActivity baseActivity);
}
