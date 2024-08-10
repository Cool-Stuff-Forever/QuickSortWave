package org.example.TelegramBot.MiddlewaresHandler;

import org.example.TelegramBot.MiddlewaresHandler.Middlewares.AuthByChatId;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

public class MiddlewareHandlerChain {
    private final List<Middleware> middlewares = new ArrayList<>();

    public void addMiddleware(Middleware middleware){
        middlewares.add(middleware);
    }

    public void executeMiddlewares(Update update, SendMessage sendMessage) throws Exception {
        for (Middleware middleware: middlewares){
            middleware.process(update, sendMessage);
        }
    }
}
