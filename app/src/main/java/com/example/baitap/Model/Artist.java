package com.example.baitap.Model;

import java.io.Serializable;

public class Artist implements Serializable {
    public  final int id_Artist;
    public  final String name_Artist;

    public Artist(int id_Artist, String name_Artist) {
        this.id_Artist = id_Artist;
        this.name_Artist = name_Artist;
    }

    public int getId_Artist() {
        return id_Artist;
    }

    public String getName_Artist() {
        return name_Artist;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id_Artist=" + id_Artist +
                ", name_Artist='" + name_Artist + '\'' +
                '}';
    }
}
