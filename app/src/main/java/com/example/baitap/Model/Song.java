package com.example.baitap.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Song extends Media implements Serializable {
    public static final Song EMPTY_SONG = new Song(-1, "", -1, -1, -1, "", -1, -1, "", -1, "", "");

    public final int id;
    public final String title;
    public final int trackNumber;
    public final int year;
    public final long duration;
    public final String data;
    public final long dateModified;
    public final int albumId;
    public final String path;

    public static Song getEmptySong() {
        return EMPTY_SONG;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public int getYear() {
        return year;
    }

    public long getDuration() {
        return duration;
    }

    public String getData() {
        return data;
    }

    public long getDateModified() {
        return dateModified;
    }

    public int getAlbumId() {
        return albumId;
    }

    public String getPath() {
        return path;
    }

    public String getAlbumName() {
        return albumName;
    }

    public int getArtistId() {
        return artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public final String albumName;
    public final int artistId;
    public final String artistName;

    public Song(int id, String title, int trackNumber, int year, long duration, String data, long dateModified, int albumId, String albumName, int artistId, String artistName, String path) {
        this.id = id;
        this.title = title;
        this.trackNumber = trackNumber;
        this.year = year;
        this.duration = duration;
        this.data = data;
        this.dateModified = dateModified;
        this.albumId = albumId;
        this.albumName = albumName;
        this.artistId = artistId;
        this.artistName = artistName;
        this.path = path;
    }

    public static ArrayList<Song> initSong() {
        ArrayList<Song> tmp = new ArrayList<>();
        tmp.add(new Song(1, "ok", 1, 1, 1, "2kb", 2, 2, "AA", 2, "BB", "ss"));
        tmp.add(new Song(1, "ok", 1, 1, 1, "2kb", 2, 2, "AA", 2, "BB", "ss"));
        tmp.add(new Song(1, "ok", 1, 1, 1, "2kb", 2, 2, "AA", 2, "BB", "ss"));
        tmp.add(new Song(1, "ok", 1, 1, 1, "2kb", 2, 2, "AA", 2, "BB", "ss"));
        tmp.add(new Song(1, "ok", 1, 1, 1, "2kb", 2, 2, "AA", 2, "BB", "ss"));

        return tmp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (id != song.id) return false;
        if (trackNumber != song.trackNumber) return false;
        if (year != song.year) return false;
        if (duration != song.duration) return false;
        if (dateModified != song.dateModified) return false;
        if (albumId != song.albumId) return false;
        if (artistId != song.artistId) return false;
        if (title != null ? !title.equals(song.title) : song.title != null) return false;
        if (data != null ? !data.equals(song.data) : song.data != null) return false;
        if (albumName != null ? !albumName.equals(song.albumName) : song.albumName != null)
            return false;
        return artistName != null ? artistName.equals(song.artistName) : song.artistName == null;

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, trackNumber, year, duration, data, dateModified, albumId, albumName, artistId, artistName);
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", trackNumber=" + trackNumber +
                ", year=" + year +
                ", duration=" + duration +
                ", data='" + data + '\'' +
                ", dateModified=" + dateModified +
                ", albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                '}';
    }
}
