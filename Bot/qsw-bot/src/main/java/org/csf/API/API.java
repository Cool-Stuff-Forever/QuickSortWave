package org.csf.API;

import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class API {
    protected static final String hostname = "ftp.quicksortwave.altervista.org";
    protected static final int port = 21;
    protected static final String user = "quicksortwave";
    protected static final String pass = "";
    static FTPClient ftp = new  FTPClient();

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
        try {
            FTP.getMethod();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
