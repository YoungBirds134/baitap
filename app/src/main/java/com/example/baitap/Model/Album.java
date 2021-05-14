package com.example.baitap.Model;

import java.io.Serializable;

public class Album implements Serializable {
    public  final int id_Album;
    public  final String name_Album;

    public Album(int id_Album, String name_Album) {
        this.id_Album = id_Album;
        this.name_Album = name_Album;
    }

    public int getId_Album() {
        return id_Album;
    }

    public String getName_Album() {
        return name_Album;
    }
}
