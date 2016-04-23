package com.kamakarov.musicshelf.store;

import android.content.ContentValues;
import android.database.Cursor;

import com.kamakarov.musicshelf.core.MainApplication;
import com.kamakarov.musicshelf.model.Cover;
import com.kamakarov.musicshelf.model.Singer;
import com.kamakarov.musicshelf.store.structure.CoverStructure;
import com.kamakarov.musicshelf.store.structure.SingerStructure;
import com.kamakarov.musicshelf.utils.DbParseHelper;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class DbManagerImpl implements DbManager {

    @Inject
    BriteDatabase briteDatabase;

    @Inject
    public DbManagerImpl() {
        MainApplication.getDbComponent().inject(this);
    }


    public void addSingers(List<Singer> singerList, boolean canBeUpdated) {
        //this operation should happen on Worker Thread
        BriteDatabase.Transaction transaction = briteDatabase.newTransaction();
        try {
            for (Singer singerItem : singerList) {
                addSinger(singerItem, canBeUpdated);
            }
            transaction.markSuccessful();
        } finally {
            transaction.end();
        }
    }

    private void addSinger(Singer singer, boolean canBeUpdated) {
        ContentValues cv = new ContentValues();
        cv.put(SingerStructure.Column.ID, singer.getId());
        cv.put(SingerStructure.Column.NAME, singer.getName());
        cv.put(SingerStructure.Column.TRACKS, singer.getTracks());
        cv.put(SingerStructure.Column.ALBUMS, singer.getAlbums());
        cv.put(SingerStructure.Column.DESCRIPTION, singer.getDescription());
        cv.put(SingerStructure.Column.GENRES, DbParseHelper.parseStringArrayToString(singer.getGenres()));
        cv.put(SingerStructure.Column.LINK, singer.getLink());

        boolean isInDb = isInDb(singer.getId());
        if (!isInDb) {
            briteDatabase.insert(SingerStructure.NAME, cv);
        } else if (canBeUpdated) {
            briteDatabase.update(SingerStructure.NAME, cv, SingerStructure.Column.ID + "= ?", singer.getId() + "");
        }

        if (singer.getCover() != null) {
            ContentValues coverCv = new ContentValues();
            coverCv.put(CoverStructure.Column.SINGER_ID, singer.getId());
            coverCv.put(CoverStructure.Column.BIG, singer.getCover().getBig());
            coverCv.put(CoverStructure.Column.SMALL, singer.getCover().getSmall());

            if (!isInDb) {
                briteDatabase.insert(CoverStructure.NAME, coverCv);
            } else {
                briteDatabase.update(CoverStructure.NAME, coverCv, CoverStructure.Column.SINGER_ID + "= ?", singer.getId() + "");
            }
        }
    }

    public Observable<List<Singer>> getSingers() {
        return briteDatabase.createQuery(SingerStructure.NAME, "SELECT * FROM " + SingerStructure.NAME).map(this::getSingersByCursor);
    }

    private List<Singer> getSingersByCursor(SqlBrite.Query query) {
        //it is Worker thread.
        Cursor cursor = query.run();
        List<Singer> listOfPersistentObjects = new ArrayList<>();
        if (cursor == null) {
            return listOfPersistentObjects;
        }

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Singer singer = parseSinger(cursor);
                String[] singerID = {singer.getId() + ""};
                Cursor coverCursor = briteDatabase.query("SELECT * FROM " + CoverStructure.NAME + " WHERE " + CoverStructure.Column.SINGER_ID + " =?", singerID);
                if (coverCursor != null) {
                    try {
                        coverCursor.moveToFirst();
                        Cover cover = parseCover(coverCursor);
                        singer.setCover(cover);
                    } finally {
                        coverCursor.close();
                    }
                }
                listOfPersistentObjects.add(singer);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return listOfPersistentObjects;
    }

    private Cover parseCover(Cursor cursor) {
        int indexBig = cursor.getColumnIndex(CoverStructure.Column.BIG);
        int indexSmall = cursor.getColumnIndex(CoverStructure.Column.SMALL);

        Cover cover = new Cover();
        cover.setBig(cursor.getString(indexBig));
        cover.setSmall(cursor.getString(indexSmall));

        return cover;
    }

    private Singer parseSinger(Cursor cursor) {
        int indexId = cursor.getColumnIndex(SingerStructure.Column.ID);
        int indexName = cursor.getColumnIndex(SingerStructure.Column.NAME);
        int indexAlbums = cursor.getColumnIndex(SingerStructure.Column.ALBUMS);
        int indexDescription = cursor.getColumnIndex(SingerStructure.Column.DESCRIPTION);
        int indexGenres = cursor.getColumnIndex(SingerStructure.Column.GENRES);
        int indexLink = cursor.getColumnIndex(SingerStructure.Column.LINK);
        int indexTracks = cursor.getColumnIndex(SingerStructure.Column.TRACKS);

        Singer singer = new Singer();
        singer.setId(cursor.getLong(indexId));
        singer.setName(cursor.getString(indexName));
        singer.setAlbums(cursor.getInt(indexAlbums));
        singer.setDescription(cursor.getString(indexDescription));
        singer.setLink(cursor.getString(indexLink));
        singer.setTracks(cursor.getInt(indexTracks));

        String[] genres = DbParseHelper.parseStringToStringArray(cursor.getString(indexGenres));
        if (genres == null) {
            singer.setGenres(new ArrayList<>());
        } else {
            singer.setGenres(Arrays.asList(genres));
        }
        return singer;
    }

    private boolean isInDb(long id) {
        String Query = "Select " + SingerStructure.Column.ID + " from " + SingerStructure.NAME + " where " + SingerStructure.Column.ID + " = ?";
        Cursor cursor = briteDatabase.query(Query, id + "");
        try {
            if (cursor.getCount() <= 0) {
                cursor.close();
                return false;
            } else {
                return true;
            }
        } finally {
            cursor.close();
        }

    }
}
