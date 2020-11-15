package com.example.musicplayer.model;

import java.io.Serializable;

public class MyMusic implements Serializable {
    private static final long SerialVersionUID = 1L;

    private String name;
    private String author;
    private String path;
    private long duration;

    public MyMusic(String name, String author, String path, long time) {
        this.name = name;
        this.author = author;
        this.path = path;
        this.duration = time;
    }

    public static long getSerialVersionUID() {
        return SerialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long time) {
        this.duration = time;
    }
}
