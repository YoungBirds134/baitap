package com.example.baitap.Model;

import java.io.Serializable;

public class PlayList_Song extends Media implements Serializable {
    public static final PlayList_Song EMPTY_PLAYLIST_SONG = new PlayList_Song(-1, "", -1, -1, -1, "", -1, -1, "", -1, "", -1, -1);



    public final int playlistId;
    public final int idInPlayList;

    @Override
    public String toString() {
        return "PlayList_Song{" +
                "playlistId=" + playlistId +
                ", idInPlayList=" + idInPlayList +
                '}';
    }

    public PlayList_Song(int playlistId, int idInPlayList) {
        super();
        this.playlistId = playlistId;
        this.idInPlayList = idInPlayList;
    }
    public PlayList_Song(int id, String title, int trackNumber, int year, long duration, String data, int dateModified, int albumId, String albumName, int artistId, String artistName, final int playlistId, final int idInPlayList) {
        super(id, title, trackNumber, year, duration, data, dateModified, albumId, albumName, artistId, artistName);
        this.playlistId = playlistId;
        this.idInPlayList = idInPlayList;
    }
    }
