package org.csf.API;

import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
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

    public static void requestMethod(){
        String hostname = "ftp.quicksortwave.altervista.org";
        String user = "quicksortwave";
        String pass = "";
        int port = 21;
        FTPClient ftp = new  FTPClient();
        //GET
        try{
            ftp.connect(hostname, port);
            ftp.login(user, pass);
            FileOutputStream outputStream = new FileOutputStream("index.html"); //filepath to save a new file somewhere
            ftp.retrieveFile("index.html", outputStream);

        }catch (Exception e){
            System.err.println(e);
        }

        //POST
        try{
            ftp.connect(hostname, port);
            ftp.login(user, pass);
            FileInputStream inputStream = new FileInputStream("transfer/message.txt"); //filepath to initial file
            ftp.makeDirectory("transfer");
            ftp.appendFile("transfer/message.txt", inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
