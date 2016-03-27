package com.kamakarov.musicshelf.web;

import com.kamakarov.musicshelf.model.Singer;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class MusicApiImpl implements IMusicApi {

    private static final String WEB_URL = "http://cache-default06f.cdn.yandex.net/download.cdn.yandex.net/mobilization-2016/";
    private MusicService musicService;

    public MusicApiImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WEB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        musicService = retrofit.create(MusicService.class);
    }

    @Override
    public Observable<List<Singer>> getSingers() {
        return musicService.getSingers();
    }
}
