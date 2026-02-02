package com.game.ui.test;

import com.game.ui.User;
import com.game.ui.UserControl;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.json.*;

// 使用Java内置的JSON-P API
public class JsonTest {

    // 写入JSON文件
    public static void writeJsonFile(String filePath) {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("name", "张三")
                .add("age", 25)
                .add("email", "zhangsan@example.com")
                .add("isStudent", false)
                .add("hobbies", Json.createArrayBuilder()
                        .add("读书")
                        .add("游泳")
                        .add("编程"))
                .build();

        try (JsonWriter writer = Json.createWriter(Files.newBufferedWriter(Paths.get(filePath)))) {
            writer.write(jsonObject);
            System.out.println("JSON文件写入成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 读取JSON文件
    public static void readJsonFile(String filePath) {
        try (JsonReader reader = Json.createReader(Files.newBufferedReader(Paths.get(filePath)))) {
            JsonObject jsonObject = reader.readObject();

            System.out.println("姓名: " + jsonObject.getString("name"));
            System.out.println("年龄: " + jsonObject.getInt("age"));
            System.out.println("邮箱: " + jsonObject.getString("email"));
            System.out.println("是否学生: " + jsonObject.getBoolean("isStudent"));

            JsonArray hobbies = jsonObject.getJsonArray("hobbies");
            System.out.println("爱好: ");
            for (JsonValue hobby : hobbies) {
                System.out.println("  - " + hobby.toString().replace("\"", ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
//        String filePath = "data/users.json";

//        JsonReader reader = Json.createReader(Files.newBufferedReader(Paths.get("data/users.json")));
//        JsonObject jsonObject = reader.readObject();
//        JsonArray jsonArray = jsonObject.getJsonArray("data");
//        for (JsonValue jsonValue : jsonArray) {
//            JsonObject jsonObject1 = (JsonObject) jsonValue;
//            String name = jsonObject1.getString("name");
//            System.out.println(name);
//            System.out.println(jsonObject1.getString("password"));
//        }
        UserControl userControl = new UserControl();
        try {
            if (userControl.addUser("hi","111111")){
                System.out.println("注册成功");
            }else {
                System.out.println("注册失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}