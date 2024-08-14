package org.csf.API;

import java.net.HttpURLConnection;
import java.net.URL;

public class API {

    public void responseMethod(String chatId, String method, String response){
        try {
            URL url = new URL("https://api.telegram.org/bot5207415087:AAHw0CAQtRE714qA1Kcq33azOyd7SeyfpNU/"+method+"?chat_id="+chatId+"&text="+response);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            System.out.println(connection.getResponseCode());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void requestMethod(){

    }
}
