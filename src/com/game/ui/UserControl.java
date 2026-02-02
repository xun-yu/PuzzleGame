package com.game.ui;

import javax.json.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class UserControl {
    private ArrayList<User> users = new ArrayList<>();
    private String path = "data/users.json";
    public UserControl() throws IOException {
        JsonReader reader = Json.createReader(Files.newBufferedReader(Paths.get(path)));
        JsonArray jsonArray = reader.readArray();
        for (JsonValue jsonValue : jsonArray) {
            JsonObject obj = jsonValue.asJsonObject();
            String username = obj.getString("username");
            String password = obj.getString("password");
            users.add(new User(username, password));
        }
    }

    // 检查用户是否存在
    public boolean checkUser(String username) {
        for (User user : users) {
            if (username.equals(user.getName())) {
                return true;
            }
        }
        return false;
    }
    public boolean checkUser(String username, String password) {
        for (User user : users) {
            if (username.equals(user.getName())&& password.equals(user.getPassword())) {
//                System.out.println(user.getName()+" "+user.getPassword());
                return true;
            }
        }
        return false;
    }

    // 添加用户
    public boolean addUser(String username, String password) throws IOException {
        users.add(new User(username, password));
        return writeJsonToFile();
    }

    // 写入JSON文件
    private boolean writeJsonToFile() throws IOException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (User user : users) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                    .add("username", user.getName())
                    .add("password", user.getPassword());
            arrayBuilder.add(objectBuilder);
        }
        JsonArray jsonArray = arrayBuilder.build();
        printJson(jsonArray);
        try(JsonWriter writer = Json.createWriter(Files.newBufferedWriter(Paths.get(path)))) {
            System.out.println("JSON数据: " + jsonArray.toString());
            writer.writeArray(jsonArray);
//            writer.close();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    private void printJson(JsonArray jsonArray){
        for (JsonValue jsonValue : jsonArray) {
            JsonObject obj = jsonValue.asJsonObject();
            String username = obj.getString("username");
            String password = obj.getString("password");
            System.out.println(username + " " + password);
        }
    }
}
