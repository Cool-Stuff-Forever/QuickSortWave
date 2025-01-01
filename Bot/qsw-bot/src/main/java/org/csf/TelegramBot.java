package org.csf;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.csf.Controllers.TestController;
import org.csf.Service.ConnectService;
import org.csf.Service.SortService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.net.InetAddress;
import java.util.Date;
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    /**
     * Token defining method
     * @return - we have to put out Telegram Bot API token
     */
    @Override
    public String getBotToken() {
        return "5207415087:AAHw0CAQtRE714qA1Kcq33azOyd7SeyfpNU";
    }

    /**
     * Bot name defining method
     * @return - our Bot's name(without '@')
     */
    @Override
    public String getBotUsername() {
        return "CoolSFer_bot";
    }

    public boolean echo = false; //controls echo-mode
    public SendMessage sendMessage = new SendMessage(); //special class to send a message(may be SendPhoto)
    public TestController testController = new TestController();
    public ConnectService connectService;

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        String chatId = update.getMessage().getChatId().toString(); //user's id to send a response
        String text = update.getMessage().getText();
        String date = new Date().toString(); //date initialization

        Message message = update.getMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        sendMessage.setParseMode(ParseMode.MARKDOWNV2);

        SortService sortService = new SortService(message.getText(), update, sendMessage);
        connectService = new ConnectService(message.getChatId().toString());

        if (message.isCommand()){
            switch (message.getText()){
                case "/start" -> sendMessage.setText("Bot is ready to work\\!");
                case "/echo" -> {
                    sendMessage.setText("Send me some text: ");
                    echo = true;
                    testController.sendBotMessage(update.getMessage().getChatId().toString());
                }
                case "/date" -> sendMessage.setText(date); //current date
                case "/connect" -> {
                    sendMessage.setText("CONN"); //only to check bot's state
                    firstConnection(chatId);
                }
            }
        } else if (echo) {
            sendMessage.setText(text);
            echo = false;
        }
        //Handling for /sort command
        if (message.getText().startsWith("/sort")) {
            sortService.paramCheck();
            sendMessage = sortService.getSendMessage();
        }

        try {
            this.execute(sendMessage); //execution of message sending

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
            connectService.startConnection(InetAddress.getByName("quicksortwave.duckdns.org"), 1233); //подключаемся по IP
            connectService.sendMessage(chatId); //отправляем свой chatId
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
