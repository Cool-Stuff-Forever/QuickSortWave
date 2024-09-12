package org.csf.Service;

import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

@Data
public class ConnectService{
    protected final String chatId;
    private Socket clientSocket;
    private DataOutputStream dataoutputStream;

    /**
     * Метод для установки соединения
     * @param ip - адресс
     * @param port - порт сервера
     * @throws IOException
     */
    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port); //нужен для соединения
        dataoutputStream = new DataOutputStream(clientSocket.getOutputStream()); //подготовка буфера к отправке
    }

    /**
     * Отправка сообщения на сервер
     * @param message - содержание сообщения
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        dataoutputStream.writeUTF(message); //отправка сообщения
        dataoutputStream.flush(); //что-то типо быстрой очистки буфера после отправки сообщения
        dataoutputStream.close(); //завершение соединения
    }

    //TODO: сделать подключение по ключу
    public String generateKey(String chatId){
        return DigestUtils.sha256Hex(chatId);
    }


}
