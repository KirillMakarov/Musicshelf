package com.kamakarov.musicshelf.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public final class Singer implements Parcelable {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeStringList(this.genres);
        dest.writeInt(this.tracks);
        dest.writeInt(this.albums);
        dest.writeString(this.link);
        dest.writeString(this.description);
        dest.writeParcelable(this.cover, flags);
    }

    public Singer() {
    }

    protected Singer(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.genres = in.createStringArrayList();
        this.tracks = in.readInt();
        this.albums = in.readInt();
        this.link = in.readString();
        this.description = in.readString();
        this.cover = in.readParcelable(Cover.class.getClassLoader());
    }

    public static final Parcelable.Creator<Singer> CREATOR = new Parcelable.Creator<Singer>() {
        @Override
        public Singer createFromParcel(Parcel source) {
            return new Singer(source);
        }

        @Override
        public Singer[] newArray(int size) {
            return new Singer[size];
        }
    };
}
