package com.kamakarov.musicshelf.core;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.kamakarov.musicshelf.store.DaggerDbComponent;
import com.kamakarov.musicshelf.store.DbComponent;
import com.kamakarov.musicshelf.store.DbModule;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import io.fabric.sdk.android.Fabric;

public final class MainApplication extends MultiDexApplication {

    private static MainApplication application;
    private RefWatcher refWatcher;
    private CoreComponent component;
    private DbComponent dbComponent;

    public static DbComponent getDbComponent() {
        return application.dbComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        application = this;
        refWatcher = LeakCanary.install(this);
        Fabric.with(this, new Crashlytics(), new Answers());
        Fresco.initialize(this);
        component = DaggerCoreComponent.builder()
                .musicshelfModule(new MusicshelfModule())
                .build();

        dbComponent = DaggerDbComponent.builder()
                .dbModule(new DbModule(getApplicationContext()))
                .build();


    }

    public static RefWatcher getRefWatcher(Context context) {
        MainApplication application = (MainApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    public static CoreComponent getComponent() {
        return application.component;
    }

    public static Context getAppContext() {
        return application.getApplicationContext();
    }
}
