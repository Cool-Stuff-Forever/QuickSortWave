package org.example.TelegramBot.MessageHandler;

import org.example.TelegramBot.MessageHandler.Handlers.Start;
import org.example.TelegramBot.MessageHandler.Handlers.Weather;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

public class MessageHandlerChain {

    public void handleMessage(Update update, SendMessage sendMessage) throws IOException, InterruptedException {
        Message msg = update.getMessage();

        // обработка команд
        if (msg.isCommand()){
            switch (msg.getText()){
                case "/getWeather" -> {
                    Weather.getToday(sendMessage);
                }
                case "/start" -> {
                    Start.help(sendMessage);
                }
                default -> {
                    sendMessage.setText("Command not recognized");
                }
            }
        // обработка простых соо с состояними(todo?)
        } else if (msg.hasText()) {
            switch (msg.getText()){
                default -> {
                    sendMessage.setText("Message not recognized");
                }
            }
        } else {
            sendMessage.setText("Unsupported message");
        }
    }

}
