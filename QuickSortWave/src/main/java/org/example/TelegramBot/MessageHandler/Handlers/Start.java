package org.example.TelegramBot.MessageHandler.Handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class Start {
    public static void help(SendMessage sendMessage){
        sendMessage.setText("Доступные комманды:\n/getWeather");
    }
}
