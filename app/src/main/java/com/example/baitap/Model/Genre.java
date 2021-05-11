package com.example.baitap.Model;

import java.io.Serializable;
import java.util.Objects;

public class Genre extends Media implements Serializable {
    public final int id;
    public final String name;
    public final int songCount;

    public Genre(int id, String name, int songCount) {
        this.id = id;
        this.name = name;
        this.songCount = songCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return id == genre.id &&
                songCount == genre.songCount &&
                Objects.equals(name, genre.name);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", songCount=" + songCount +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, songCount);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSongCount() {
        return songCount;
    }
}
