package org.csf;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Date;

public class TelegramBot extends TelegramLongPollingBot {


    /**
     * Определение токена
     * @return - кидаем токен
     */
    @Override
    public String getBotToken() {
        return "5207415087:AAHw0CAQtRE714qA1Kcq33azOyd7SeyfpNU";
    }

    /**
     * Определяем имя
     * @return - кидаем имя бота
     */
    @Override
    public String getBotUsername() {
        return "CoolSFer_bot";
    }

    public boolean echo = false; // переменная для включения эхо-режима

    @Override
    public void onUpdateReceived(Update update) {
        String chatId = update.getMessage().getChatId().toString(); // id чата, куда отправляется ответ
        String text = update.getMessage().getText();
        String date = new Date().toString();  // получение даты

        SendMessage sendMessage = new SendMessage(); // объект класса сообщений (может быть фото)
        sendMessage.setChatId(chatId);

        Message message = update.getMessage();

        if(message.isCommand()){    // обработка команд
            switch (message.getText()){
                case "/start" -> sendMessage.setText("Бот готов к работе!");
                case "/echo" -> {
                    sendMessage.setText("Напиши мне что-нибудь: ");
                    echo = true;
                }
                case "/date" -> sendMessage.setText(date);
            }
        } else if (echo) {
            sendMessage.setText(text);
            echo = false;
        }

        try {
            this.execute(sendMessage); // исполненение команды (отправка сообщения)
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
