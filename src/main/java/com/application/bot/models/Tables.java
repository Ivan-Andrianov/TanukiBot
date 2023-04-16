package com.application.bot.models;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class Tables {
    private Map<Integer,List<BigInteger>> mapOfTables; //number of table : user;

    public Tables(){
        mapOfTables = new HashMap<>();
    }

    public void addUserInTable(int numberOfTable,BigInteger idOfUser){
        mapOfTables.get(numberOfTable).add(idOfUser);
    }

    public void removeUserFromTable(int numberOfTable,BigInteger idOfUser){
        mapOfTables.get(numberOfTable).remove(idOfUser);
    }

    public List<BigInteger> getListOfUsersIdByNumberOfTable(int numberOfTable){
        return mapOfTables.get(numberOfTable);
    }

}
