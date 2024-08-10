package org.example.TelegramBot.MessageHandler.Handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Weather {
    public static void getToday(SendMessage sendMessage) throws IOException, InterruptedException {
        String url = "https://wttr.in/Moscow?format=%25t" ;
        HttpResponse<String> response;
//      отправляем запрос к апи
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        String responseBody = response.body();
        sendMessage.setText(responseBody);
    }
}
