package org.example.TelegramBot.MiddlewaresHandler.Middlewares;

import org.example.TelegramBot.MiddlewaresHandler.Middleware;
import org.example.TelegramBot.MiddlewaresHandler.MiddlewareError;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static org.example.Config.getAdminList;
import static org.example.TelegramBot.MiddlewaresHandler.Middlewares.AuthByChatId.AuthError.ErrorType.ACCESS_DENIED;

public class AuthByChatId implements Middleware {

    @Override
    public void process(Update update, SendMessage sendMessage) throws MiddlewareError {
        Long chatId = update.getMessage().getChatId();
        List<Long> admins = getAdminList();
        if (!admins.contains(chatId)) {
            throw new AuthError(ACCESS_DENIED, "Access denied for chat ID: " + chatId);
        }
    }

    // своя ошибка
    public static class AuthError extends MiddlewareError {

        public enum ErrorType {
            ACCESS_DENIED
        }

        public AuthError(ErrorType errorType, String message) {
            super(errorType, message);
        }

    }
}
