package org.csf.API;

import java.net.HttpURLConnection;
import java.net.URL;

public class TestController {

    public void sendBotMessage(String chatId){
        try {
            URL url = new URL("https://api.telegram.org/bot5207415087:AAHw0CAQtRE714qA1Kcq33azOyd7SeyfpNU/sendMessage?chat_id="+chatId+"&text=Hello my friend!");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            System.out.println(connection.getResponseCode());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
