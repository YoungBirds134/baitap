package com.example.baitap.Model;

import java.io.Serializable;

public class Playlist implements Serializable {
    public  final int id_Playlist;
    public  final String name_Playlist;

    public Playlist(int id_Playlist, String name_Playlist) {
        this.id_Playlist = id_Playlist;
        this.name_Playlist = name_Playlist;
    }

    public int getId_Playlist() {
        return id_Playlist;
    }

    public String getName_Playlist() {
        return name_Playlist;
    }
}
