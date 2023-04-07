package com.application.bot.model;

public class Message {

    //User может быть как ботом, так и пользователем.
    private int id;
    private User from;//Пользователь, от которого пришло сообщение
    private String text;
    private Chat chat; //Чат, от которого пришло сообщение

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
