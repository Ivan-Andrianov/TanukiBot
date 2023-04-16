package com.application.bot.models;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Component
public class WorkingWaiters {
    private Map<BigInteger,User> mapOfWaiters;

    public WorkingWaiters(){
        mapOfWaiters = new HashMap<>();
    }

    public void addWaiter(User user){
        mapOfWaiters.put(user.getId(),user);
    }

    public void deleteWaiter(BigInteger id){
        mapOfWaiters.remove(id);
    }

    public User getUser(BigInteger id){
        return mapOfWaiters.get(id);
    }
    public boolean isWorkingWaiter(User user){
        return mapOfWaiters.get(user.getId())!=null;
    }
}
