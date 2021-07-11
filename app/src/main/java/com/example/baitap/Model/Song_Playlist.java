package com.example.baitap.Model;

import java.io.Serializable;

public class Song_Playlist implements Serializable {
    public int id_Song;
    public  String name_Song;
    public  String image_Song;
    public  String duration;
    public String path;

    public Song_Playlist(int id_Song, String name_Song, String image_Song, String duration, String path) {
        this.id_Song = id_Song;
        this.name_Song = name_Song;
        this.image_Song = image_Song;
        this.duration = duration;
        this.path = path;
    }

    public Song_Playlist() {

    }

    public int getId_Song() {
        return id_Song;
    }

    public String getName_Song() {
        return name_Song;
    }

    public String getImage_Song() {
        return image_Song;
    }

    public String getDuration() {
        return duration;
    }

    public String getPath() {
        return path;
    }

    public void setId_Song(int id_Song) {
        this.id_Song = id_Song;
    }

    public void setName_Song(String name_Song) {
        this.name_Song = name_Song;
    }

    public void setImage_Song(String image_Song) {
        this.image_Song = image_Song;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Song_Playlist{" +
                "id_Song=" + id_Song +
                ", name_Song='" + name_Song + '\'' +
                ", image_Song='" + image_Song + '\'' +
                ", duration='" + duration + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
