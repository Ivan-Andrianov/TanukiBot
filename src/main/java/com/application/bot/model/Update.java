package com.application.bot.model;



public class Update {
    private int id;
    private Message message;


    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
