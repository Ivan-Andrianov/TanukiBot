package com.application.bot.tools;

import com.application.bot.models.*;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static void parseMessage(Message message,ApplicationContext context){
        if (message.getFrom().isBot()) messageFromBotParser(message,context);
        else messageFromUserParse(message,context);
    }

    public static void messageFromUserParse(Message message, ApplicationContext context){
        Tables tables = context.getBean(Tables.class);
        WorkingWaiters waiters = context.getBean(WorkingWaiters.class);
        User user = message.getFrom();

        if (!waiters.isWorkingWaiter(user)){
            waiters.addWaiter(user);
            String text = "Привет, я Бот Ивуампий. Чтобы начать работать со мной, введите команду /chooseTables <listOfTables>";
            try {
                URL url = new URL("https://api.telegram.org/bot5832019176:AAHJiWrYXI4L8PmseIBuN-Yi4v2cwvqp0Ms/sendMessage?text="+text+"&chat_id="+user.getId());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.getInputStream();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if (message.getText().startsWith("/addTables")){
            Matcher matcher = Pattern.compile("((\\d+)\\s*-\\s*(\\d+))|(\\d+)").matcher(message.getText());
            while (matcher.find()){
                if (matcher.group().matches("\\d+")){
                    tables.addUserInTable(Integer.parseInt(matcher.group()),user.getId());
                }else if(matcher.group().matches("(\\d+\\s*-\\s*\\d+)")){
                    for (int i=Integer.parseInt(matcher.group(2));i<=Integer.parseInt(matcher.group(3));i++){
                        tables.addUserInTable(i,user.getId());
                    }
                }
            }
        }else if (message.getText().startsWith("/dropAllTables")){
            for (int i:user.getListOfTables()){
                tables.removeUserFromTable(i,user.getId());
            }
            user.deleteAllTablesFromUser();

        }else if (message.getText().startsWith("/dropTableByNumber")){
            Matcher matcher = Pattern.compile("((\\d+)\\s*-\\s*(\\d+))|(\\d+)").matcher(message.getText());

            while (matcher.find()){
                if (matcher.group().matches("\\d+")){
                    int numberOfTable = Integer.parseInt(matcher.group());
                    tables.removeUserFromTable(numberOfTable,user.getId());
                    user.deleteTableByNumberFromUser(numberOfTable);
                }else if(matcher.group().matches("(\\d+\\s*-\\s*\\d+)")){
                    for (int i=Integer.parseInt(matcher.group(2));i<=Integer.parseInt(matcher.group(3));i++){
                        int numberOfTable = Integer.parseInt(matcher.group());
                        tables.removeUserFromTable(numberOfTable,user.getId());
                        user.deleteTableByNumberFromUser(numberOfTable);
                    }
                }
            }
        }else if (message.getText().startsWith("/giveMyTables")){
            StringBuffer sb = new StringBuffer();
            for (int i:user.getListOfTables()){
                sb.append("Столик: "+i+"%0A");
            }
            try {
                new URL("https://api.telegram.org/bot5832019176:AAHJiWrYXI4L8PmseIBuN-Yi4v2cwvqp0Ms/sendMessage?text="+sb+"&chat_id="+user.getId()).openConnection().getInputStream();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void messageFromBotParser(Message message,ApplicationContext context){
        Tables tables = context.getBean(Tables.class);
        WorkingWaiters waiters = context.getBean(WorkingWaiters.class);

        Matcher matcher = Pattern.compile("(столика (\\d+))").matcher(message.getText());
        matcher.find();
        int numberOfTable = Integer.parseInt(matcher.group(2));

        for (BigInteger id:tables.getListOfUsersIdByNumberOfTable(numberOfTable)){
            try {
                new URL("https://api.telegram.org/bot5832019176:AAHJiWrYXI4L8PmseIBuN-Yi4v2cwvqp0Ms/sendMessage?text="+message.getText()+"&chat_id="+id).openConnection().getInputStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
