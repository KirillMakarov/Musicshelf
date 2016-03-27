package com.kamakarov.musicshelf.web;

import com.kamakarov.musicshelf.model.Singer;

import java.util.List;

import rx.Observable;

public interface IMusicApi {
    Observable<List<Singer>> getSingers();
}
