package com.application.bot.models;

import java.math.BigInteger;

public class Message {

    //User может быть как ботом, так и пользователем.
    private BigInteger id;
    private User from;//Пользователь, от которого пришло сообщение
    private String text;
    private Chat chat; //Чат, от которого пришло сообщение

    public Message(BigInteger id, User from, String text, Chat chat) {
        this.id = id;
        this.from = from;
        this.text = text;
        this.chat = chat;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String test) {
        this.text = test;
    }
}
