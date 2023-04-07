package com.application.bot.models;

import java.math.BigInteger;

public class Chat {
    private BigInteger id;
    private String type;

    public Chat(BigInteger id, String type) {
        this.id = id;
        this.type = type;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
