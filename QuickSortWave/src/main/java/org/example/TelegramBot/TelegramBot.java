package org.example.TelegramBot;

import org.example.Config;
import org.example.Main;
import org.example.TelegramBot.MessageHandler.MessageHandlerChain;
import org.example.TelegramBot.MiddlewaresHandler.MiddlewareError;
import org.example.TelegramBot.MiddlewaresHandler.MiddlewareHandlerChain;
import org.example.TelegramBot.MiddlewaresHandler.Middlewares.AuthByChatId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;


public class TelegramBot extends TelegramLongPollingBot {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);
    // обработчик соо
    private final MessageHandlerChain messageHandler;
    // обработчик мидлварей
    private final MiddlewareHandlerChain middlewareChain;

    public TelegramBot() {
        this.middlewareChain = new MiddlewareHandlerChain();
        this.middlewareChain.addMiddleware(new AuthByChatId());
        this.messageHandler = new MessageHandlerChain();
    }

    @Override
    public String getBotUsername() {
        return Config.getBotUserName();
    }

    @Override
    public String getBotToken() {
        return Config.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        // проходимся по мидлварям
        try {
            // проводим запрос по всем мидлварям
            middlewareChain.executeMiddlewares(update, sendMessage);
        } catch (MiddlewareError e) {
            // Обработка исключений мидлварей
            Enum<?> errorType = e.getErrorType();

            if (errorType instanceof AuthByChatId.AuthError.ErrorType) {
                switch ((AuthByChatId.AuthError.ErrorType) errorType) {
                    case ACCESS_DENIED:
                        logger.info("Access denied ID: {}", e.getMessage());
                        return;
                    default:
                        throw new RuntimeException("Unhandled error type: " + errorType);
                }
            } else {
                throw new RuntimeException("Unknown error type: " + errorType);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // обработка соо в цепи
        try {
            messageHandler.handleMessage(update,sendMessage);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        // отправляем
        try {
            sendMessage.setChatId(update.getMessage().getChatId().toString());
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
