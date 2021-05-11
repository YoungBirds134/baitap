package com.example.baitap.Model;

import java.io.Serializable;
import java.util.Objects;

public class PlayList extends Media implements Serializable {
    public final int id;
    public final String name;

    public PlayList(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PlayList(int id) {
        this.id = id;
        name = null;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayList playList = (PlayList) o;
        return id == playList.id &&
                Objects.equals(name, playList.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
