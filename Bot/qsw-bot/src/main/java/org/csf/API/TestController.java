package org.csf.API;

public class TestController extends API{

    public void sendBotMessage(String chatId){
        responseMethod(chatId, "sendMessage", "Wow, bro! You have a secret msg!!!");
    }


}
