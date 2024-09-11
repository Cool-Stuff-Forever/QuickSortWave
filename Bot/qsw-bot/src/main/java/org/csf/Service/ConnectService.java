package org.csf.Service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

@Data
public class ConnectService{
    protected final String chatId;
    private Socket clientSocket;
    private DataOutputStream dataoutputStream;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        dataoutputStream = new DataOutputStream(clientSocket.getOutputStream());
    }

    public void sendMessage(String message) throws IOException {
        dataoutputStream.writeUTF(message);
        dataoutputStream.flush();
        dataoutputStream.close();
    }

    //TODO: сделать подключение по ключу
    public String generateKey(String chatId){
        return DigestUtils.sha256Hex(chatId);
    }


}
