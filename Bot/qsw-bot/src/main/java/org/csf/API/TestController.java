package org.csf.API;

import org.telegram.telegrambots.meta.api.objects.File;

import java.io.FileDescriptor;

public class TestController extends API{

    public void sendBotMessage(String chatId){
        responseMethod(chatId, "sendMessage", "Wow, bro! You have a secret msg!!!");
    }


}
