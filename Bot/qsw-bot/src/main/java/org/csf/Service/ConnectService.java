package org.csf.Service;

import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

@Data
public class ConnectService{
    protected final String chatId;
    private Socket clientSocket;
    private DataOutputStream dataoutputStream;

    /**
     * Connection method
     * @param ip - address
     * @param port - server port
     * @throws IOException
     */
    public void startConnection(InetAddress ip, int port) throws IOException {
        clientSocket = new Socket(ip, port); //necessary for connection
        dataoutputStream = new DataOutputStream(clientSocket.getOutputStream()); //buffer preparation for sending
    }

    /**
     * Sending message on a server
     * @param message - message content
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        dataoutputStream.writeUTF(message); //sending
        dataoutputStream.flush(); //something like a quick clean after buffer sending
        dataoutputStream.close(); //connection interruption
    }

    //TODO: make it possible to connect with key
    public String generateKey(String chatId){
        return DigestUtils.sha256Hex(chatId);
    }


}
