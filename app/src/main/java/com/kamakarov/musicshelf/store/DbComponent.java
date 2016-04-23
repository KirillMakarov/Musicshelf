package com.kamakarov.musicshelf.store;

import dagger.Component;

@Component(modules = {DbModule.class})
public interface DbComponent {
    void inject(DbManagerImpl managerimpl);
}
