package org.csf;

import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.csf.API.TestController;
import org.csf.Service.ConnectService;
import org.csf.Service.SortService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Date;
@Slf4j
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
    public SendMessage sendMessage = new SendMessage(); // объект класса сообщений (может быть фото)
    public TestController testController = new TestController();
    public ConnectService connectService;

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        String chatId = update.getMessage().getChatId().toString(); // id чата, куда отправляется ответ
        String text = update.getMessage().getText();
        String date = new Date().toString(); // получение даты

        Message message = update.getMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        sendMessage.setParseMode(ParseMode.MARKDOWNV2);

        SortService sortService = new SortService(message.getText(), sendMessage, update);
        connectService = new ConnectService(message.getChatId().toString());

        if (message.isCommand()){
            switch (message.getText()){
                case "/start" -> sendMessage.setText("Бот готов к работе\\!");
                case "/echo" -> {
                    sendMessage.setText("Напиши мне что\\-нибудь: ");
                    echo = true;
                    testController.sendBotMessage(update.getMessage().getChatId().toString());
                }
                case "/date" -> sendMessage.setText(date); //вывод даты
                case "/connect" -> {
                    sendMessage.setText("CONN"); //показывает что бот работает
                    firstConnection(chatId);
                }
            }
        } else if (echo) {
            sendMessage.setText(text);
            echo = false;
        }
        //Обработка команды /sort
        if (message.getText().startsWith("/sort")) {
            sortService.paramCheck();
            sendMessage = sortService.getSendMessage();
        }

        try {
            this.execute(sendMessage); // исполненение команды - отправка сообщения

        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Первичная синхронизация ПК с ботом (без БД)
     * @param chatId
     */
    public void firstConnection(String chatId){
        try{
            connectService.startConnection("localhost", 1233); //подключаемся по IP
            connectService.sendMessage(chatId); //отправляем свой chatId
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
