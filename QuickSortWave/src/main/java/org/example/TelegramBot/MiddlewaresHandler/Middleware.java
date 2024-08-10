package org.example.TelegramBot.MiddlewaresHandler;

import org.example.TelegramBot.MiddlewaresHandler.Middlewares.AuthByChatId;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

// интрефейс для всех мидлварей
public interface Middleware<E extends Exception> {
    void process(Update update, SendMessage sendMessage) throws E;
}

