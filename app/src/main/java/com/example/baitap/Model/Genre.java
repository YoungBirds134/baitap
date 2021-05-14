package com.example.baitap.Model;

import java.io.Serializable;

public class Genre implements Serializable {
    public  final int id_Genre;
    public  final String name_Genre;

    public Genre(int id_Genre, String name_Genre) {
        this.id_Genre = id_Genre;
        this.name_Genre = name_Genre;
    }

    public int getId_Genre() {
        return id_Genre;
    }

    public String getName_Genre() {
        return name_Genre;
    }
}
