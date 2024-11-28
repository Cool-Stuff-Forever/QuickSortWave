package org.csf.Service;

import lombok.Data;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Class for testing server connection
 */
@Data
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private DataInputStream dataInputStream;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        dataInputStream = new DataInputStream(clientSocket.getInputStream());
        String response = dataInputStream.readUTF();
        System.out.println("message = " + response);
    }

    public static void main(String[] args) {
        Server ser = new Server();
        try {
            ser.start(1233); //server start on 1233
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
