package com.example.baitap.Model;

import java.io.Serializable;

public class Song implements Serializable {
    public int id_Song;
    public  String name_Song;
    public  String image_Song;
    public  String duration;
    public String path;
    public int like;
    public String date;
    public String name_Genre;
    public  String name_Album;
    public  String name_Artist;

    public Song(int id_Song, String name_Song, String image_Song, String duration, String path, int like, String date, String name_Genre, String name_Album, String name_Artist) {
        this.id_Song = id_Song;
        this.name_Song = name_Song;
        this.image_Song = image_Song;
        this.duration = duration;
        this.path = path;
        this.like = like;
        this.date = date;
        this.name_Genre = name_Genre;
        this.name_Album = name_Album;
        this.name_Artist = name_Artist;
    }

    public int getId_Song() {
        return id_Song;
    }

    public void setId_Song(int id_Song) {
        this.id_Song = id_Song;
    }

    public String getName_Song() {
        return name_Song;
    }

    public void setName_Song(String name_Song) {
        this.name_Song = name_Song;
    }

    public String getImage_Song() {
        return image_Song;
    }

    public void setImage_Song(String image_Song) {
        this.image_Song = image_Song;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName_Genre() {
        return name_Genre;
    }

    public void setName_Genre(String name_Genre) {
        this.name_Genre = name_Genre;
    }

    public String getName_Album() {
        return name_Album;
    }

    public void setName_Album(String name_Album) {
        this.name_Album = name_Album;
    }

    public String getName_Artist() {
        return name_Artist;
    }

    public void setName_Artist(String name_Artist) {
        this.name_Artist = name_Artist;
    }
public Song(){}
}
