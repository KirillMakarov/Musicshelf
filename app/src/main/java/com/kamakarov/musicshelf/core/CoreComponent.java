package com.kamakarov.musicshelf.core;

import com.kamakarov.musicshelf.gui.fragments.FragmentBase;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MusicshelfModule.class)
public interface CoreComponent {
    void inject(FragmentBase fragmentBase);
}
