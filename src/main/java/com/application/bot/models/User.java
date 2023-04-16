package com.application.bot.models;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class User {

    private BigInteger id;
    private String username;
    private boolean isBot;
    private List<Integer> listOfTables;

    public User(BigInteger id, String username, boolean isBot) {
        this.id = id;
        this.username = username;
        this.isBot = isBot;
        this.listOfTables = new ArrayList<>();
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

    public void addTableToUser(int numberOfTable){
        this.listOfTables.add(numberOfTable);
    }

    public void deleteAllTablesFromUser(){
        this.listOfTables.clear();
    }

    public void deleteTableByNumberFromUser(int number){
        listOfTables.remove(number);
    }

    public List<Integer> getListOfTables() {
        return listOfTables;
    }
}
