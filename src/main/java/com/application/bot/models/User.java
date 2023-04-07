package com.application.bot.models;

import java.math.BigInteger;

public class User {

    private BigInteger id;
    private String username;
    private boolean isBot;

    public User(BigInteger id, String username, boolean isBot) {
        this.id = id;
        this.username = username;
        this.isBot = isBot;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isBot() {
        return isBot;
    }

    public void setBot(boolean bot) {
        isBot = bot;
    }
}
