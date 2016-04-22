package com.kamakarov.musicshelf.store;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kamakarov.musicshelf.store.structure.CoverStructure;
import com.kamakarov.musicshelf.store.structure.SingerStructure;

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "singers.db";

    public DbOpenHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createSingersTable(db);
        createCoverTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createSingersTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + SingerStructure.NAME
                + " ("
                + SingerStructure.Column.ID + " LONG, "
                + SingerStructure.Column.NAME + " TEXT, "
                + SingerStructure.Column.TRACKS + " INTEGER, "
                + SingerStructure.Column.ALBUMS + " INTEGER, "
                + SingerStructure.Column.LINK + " TEXT, "
                + SingerStructure.Column.DESCRIPTION + " TEXT, "
                + SingerStructure.Column.GENRES + " TEXT "
                + ")";
        db.execSQL(sql);
    }

    private void createCoverTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + CoverStructure.NAME
                + " ("
                + CoverStructure.Column.SINGER_ID + " LONG, "
                + CoverStructure.Column.BIG + " TEXT, "
                + CoverStructure.Column.SMALL + " TEXT "
                + ")";
        db.execSQL(sql);
    }

}
