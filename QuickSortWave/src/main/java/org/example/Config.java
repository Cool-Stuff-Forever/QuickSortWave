package org.example;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.ArrayList;
import java.util.List;

public class Config {
    static Dotenv dotenv = Dotenv.load();

    public static String getBotToken() {
      return dotenv.get("TG_BOT_TOKEN");
    };

    public static String getBotUserName() {
      return dotenv.get("TG_BOT_USERNAME");
    };

    public static List<Long> getAdminList() {
        String raw = dotenv.get("TG_BOT_ADMIN_LIST");
        String[] parts = raw.split(",");
        List<Long> list = new ArrayList<>();

        for (String admin_id: parts ){
            try {
                list.add(Long.parseLong(admin_id));
            } catch (NumberFormatException e) {
                System.out.println("Error get admin list: " + e.getMessage());
            }
        }

        return list;
    };
}
