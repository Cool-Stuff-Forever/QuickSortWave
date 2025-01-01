package org.csf.API;

import org.apache.commons.net.ftp.FTPClient;
import org.csf.Constants.Constants;
import org.csf.Entity.UserEntity;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FTP {
    protected static final String hostname = "ftp.quicksortwave.altervista.org";
    protected static final int port = 21;
    protected static final String username = "quicksortwave";
    protected static final String pass = "";
    static FTPClient ftp = new  FTPClient(); //important instance in file transfer

    /**
     * Method to send data to a dedicated server via FTP
     * @param fileName
     */
    public void postMethod(String fileName){
        try{
            //Connection to the FTP server
            ftp.connect(hostname, port);
            ftp.login(username, pass);

            FileInputStream inputStream = new FileInputStream(Constants.EXCHANGE_PATH + fileName + ".json"); //filepath to initial file
            ftp.makeDirectory(Constants.EXCHANGE_PATH); //distant directory on the server
            ftp.appendFile(Constants.EXCHANGE_PATH + fileName + ".json", inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieving information from a server by:
     * @param user - user's data
     * @param filePath - asked filePath
     */
    public static void getMethod(UserEntity user, String filePath){
        try{
            //Connection to the FTP server
            ftp.connect(hostname, port);
            ftp.login(username, pass);

            FileOutputStream outputStream = new FileOutputStream(Constants.EXCHANGE_PATH + user.getChatId() + filePath); //filepath to save a new file somewhere
            ftp.retrieveFile("index.html", outputStream); //taking only one special file
            ftp.retrieveFileStream(user.getChatId() + filePath); //getting a file InputStream

        }catch (Exception e){
            System.err.println(e);
        }
    }
}
