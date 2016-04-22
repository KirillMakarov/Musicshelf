package com.kamakarov.musicshelf.store;

import com.kamakarov.musicshelf.model.Singer;

import java.util.List;

import rx.Observable;

public interface DbManager {
    void addSingers(List<Singer> singerList);

    Observable<List<Singer>> getSingers();
}
