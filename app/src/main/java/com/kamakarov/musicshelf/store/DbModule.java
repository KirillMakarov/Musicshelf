package com.kamakarov.musicshelf.store;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.schedulers.Schedulers;

@Module
public class DbModule {

    Context context;

    public DbModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public SqlBrite provideSqlBrite() {
        return SqlBrite.create();
    }

    @Singleton
    @Provides
    public BriteDatabase provideBriteDb(SqlBrite sqlBrite, SQLiteOpenHelper openHelper) {
        return sqlBrite.wrapDatabaseHelper(openHelper, Schedulers.io());

    }

    @Singleton
    @Provides
    public SQLiteOpenHelper provideOpenHelper() {
        return new DbOpenHelper(context);
    }

    @Singleton
    @Provides
    public DbManager provideDbManager(BriteDatabase briteDatabase) {
        return new DbManagerImpl(briteDatabase);
    }
}
