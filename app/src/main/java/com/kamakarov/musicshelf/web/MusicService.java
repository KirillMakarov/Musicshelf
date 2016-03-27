package com.kamakarov.musicshelf.web;

import com.kamakarov.musicshelf.model.Singer;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface MusicService {

    @GET("artists.json")
    Observable<List<Singer>> getSingers();
}
