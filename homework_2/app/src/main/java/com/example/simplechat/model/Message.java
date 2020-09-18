package com.example.simplechat.model;

public class Message {
    public static final int TYPE_SEND = 0;
    public static final int TYPE_RECIEVE = 1;
    private String text;
    private int type;

    public Message(String text, int type) {
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public int getType() {
        return type;
    }
}
