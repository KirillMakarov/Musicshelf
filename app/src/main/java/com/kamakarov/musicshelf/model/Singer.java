package com.kamakarov.musicshelf.model;

import java.util.List;

public final class Singer {
    private long id;
    private String name;
    private List<String> genres;
    private int tracks;
    private int albums;
    private String link;
    private String description;
    private Cover cover;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getGenres() {
        return genres;
    }

    public int getTracks() {
        return tracks;
    }

    public int getAlbums() {
        return albums;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public Cover getCover() {
        return cover;
    }
}
