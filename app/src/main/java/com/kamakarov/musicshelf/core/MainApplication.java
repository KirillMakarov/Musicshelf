package com.kamakarov.musicshelf.core;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import io.fabric.sdk.android.Fabric;

public final class MainApplication extends Application {


    private RefWatcher refWatcher;
    private CoreComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        refWatcher = LeakCanary.install(this);
        Fabric.with(this, new Crashlytics(), new Answers());

        component = DaggerCoreComponent.builder().
                musicshelfModule(new MusicshelfModule()).build();
    }

    public static RefWatcher getRefWatcher(Context context) {
        MainApplication application = (MainApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    public CoreComponent getComponent() {
        return component;
    }
}
