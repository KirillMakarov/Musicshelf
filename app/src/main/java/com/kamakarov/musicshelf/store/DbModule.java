package com.kamakarov.musicshelf.store;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import dagger.Module;
import dagger.Provides;
import rx.schedulers.Schedulers;

/**
 * This module should be available in Db implementation (db layer).
 */
@Module
public class DbModule {
    Context context;

    public DbModule(Context context) {
        this.context = context;
    }

    @Provides
    public SqlBrite provideSqlBrite() {
        return SqlBrite.create();
    }

    @Provides
    public BriteDatabase provideBriteDb(SqlBrite sqlBrite, SQLiteOpenHelper openHelper) {
        return sqlBrite.wrapDatabaseHelper(openHelper, Schedulers.io());
    }

    @Provides
    public SQLiteOpenHelper provideOpenHelper() {
        return new DbOpenHelper(context);
    }

}
