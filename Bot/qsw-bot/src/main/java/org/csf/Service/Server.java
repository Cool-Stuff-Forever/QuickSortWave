package org.csf.Service;

import lombok.Data;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Тестовый класс для проверки создания сервера
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
            ser.start(1233); //стартуем сервак на 1233 порту
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
