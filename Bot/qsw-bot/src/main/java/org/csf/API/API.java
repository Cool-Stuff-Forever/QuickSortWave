package org.csf.API;

import org.csf.Constants.Constants;
import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

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

//    public static void requestMethod(){
//        try {
//            FTP.getMethod();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    /**
     * Method which creates a JSON-file
     * Inside there can be any information (this is an abstract method)
     * @param data
     * @param fileName
     * @throws IOException
     */
    public void createJSON(HashMap<String, String> data, String fileName) throws IOException{
        File fileJSON = new File(Constants.EXCHANGE_PATH + fileName + ".json");
        JSONObject json = new JSONObject(data);
        PrintWriter writer = new PrintWriter(fileJSON);
        writer.print(json);
        writer.close();
    }
}
