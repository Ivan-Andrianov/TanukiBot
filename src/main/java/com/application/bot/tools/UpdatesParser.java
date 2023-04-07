package com.application.bot.tools;

import com.application.bot.models.Chat;
import com.application.bot.models.Message;
import com.application.bot.models.User;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class UpdatesParser {

    public static List<Message> parseToListOfMessenger(String json){

        JSONParser parser = new JSONParser(json);
        List<Message> messages = new ArrayList<>();


        try {
           for (LinkedHashMap<String,Object> list:(ArrayList<LinkedHashMap<String,Object>>) parser.parseObject().get("result")){
               LinkedHashMap<String,Object> messageJson = (LinkedHashMap<String, Object>) list.get("message");
               LinkedHashMap<String,Object> userJson = (LinkedHashMap<String, Object>) messageJson.get("from");
               LinkedHashMap<String,Object> chatJson = (LinkedHashMap<String, Object>) messageJson.get("chat");
               System.out.println(messageJson);

               BigInteger user_id = (BigInteger) userJson.get("id");
               boolean is_bot = (boolean) userJson.get("is_bot");
               String username = (String) userJson.get("username");
               User user = new User(user_id,username,is_bot);

               BigInteger chat_id = (BigInteger) chatJson.get("id");
               String chat_type = (String) chatJson.get("type");
               Chat chat = new Chat(chat_id,chat_type);

               BigInteger message_id = (BigInteger) messageJson.get("message_id");
               String text = (String) messageJson.get("text");
               messages.add(new Message(message_id,user,text,chat));
           }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return messages;
    }
}
