package com.example.baitap.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Song implements Serializable {
    public static final Song EMPTY_SONG = new Song(-1,null,null,0,null,0);

public final int id_Song;
    public  String name_Song;
    public  String image_Song;
    public  long duration;
    public String path;
    public int like;

    public void setName_Song(String name_Song) {
        this.name_Song = name_Song;
    }

    public void setImage_Song(String image_Song) {
        this.image_Song = image_Song;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setLike(int like) {
        this.like = like;
    }


    public Song(int id_Song, String name_Song, String image_Song, long duration, String path, int like) {
        this.id_Song = id_Song;
        this.name_Song = name_Song;
        this.image_Song = image_Song;
        this.duration = duration;
        this.path = path;
        this.like = like;
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

    public long getDuration() {
        return duration;
    }

    public String getPath() {
        return path;
    }

    public int getLike() {
        return like;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return id_Song == song.id_Song &&
                duration == song.duration &&
                like == song.like &&
                Objects.equals(name_Song, song.name_Song) &&
                Objects.equals(image_Song, song.image_Song) &&
                Objects.equals(path, song.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_Song, name_Song, image_Song, duration, path, like);
    }

    @Override
    public String toString() {
        return "Songs{" +
                "id_Song=" + id_Song +
                ", name_Song='" + name_Song + '\'' +
                ", image_Song='" + image_Song + '\'' +
                ", duration=" + duration +
                ", path='" + path + '\'' +
                ", like=" + like +
                '}';
    }
    public ArrayList<Song> initSong(){
        ArrayList<Song> arrayList = new ArrayList<>();
        for (int i = 1; i<10;i++)
        {
            arrayList.add(new Song(0+i,"Song"+i,"Image"+i,12+i,"Path"+i,0));

        }
return  arrayList;
    }
}
