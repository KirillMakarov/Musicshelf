package com.kamakarov.musicshelf.store.structure;

public class SingerStructure {

    public final static String NAME = "singers";

    public static class Column {
        public static final String ID = "singer_id";
        public static final String NAME = "name";
        public static final String TRACKS = "tracks";
        public static final String ALBUMS = "albums";
        public static final String LINK = "link";
        public static final String DESCRIPTION = "description";
        public static final String GENRES = "genres";
    }
}
