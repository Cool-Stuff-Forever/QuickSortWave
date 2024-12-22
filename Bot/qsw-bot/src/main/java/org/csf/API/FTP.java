package org.csf.API;

import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FTP {
    protected static final String hostname = "ftp.quicksortwave.altervista.org";
    protected static final int port = 21;
    protected static final String user = "quicksortwave";
    protected static final String pass = "";
    static FTPClient ftp = new  FTPClient();

    public void postMethod(){
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

    public static void getMethod(){
        try{
            ftp.connect(hostname, port);
            ftp.login(user, pass);
            FileOutputStream outputStream = new FileOutputStream("index.html"); //filepath to save a new file somewhere
            ftp.retrieveFile("index.html", outputStream);

        }catch (Exception e){
            System.err.println(e);
        }
    }
}
