package org.csf.Service;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * Сервис для обработки параметров команды типа "/sort"
 */
@Data
@RequiredArgsConstructor
public class SortService {
    public String param1;
    public String param2;
    protected final String message; // отправленное сообщение
    protected final SendMessage sendMessage; // ответ бота

    /**
     * Определение параметров
     */
    public void defineParams(){
        String[] messageData = this.getMessage().split(" ");

        if (messageData.length < 3){
            if (messageData[1].startsWith("-")){
                param1 = messageData[1];
            }
        } else {
            if (messageData[1].startsWith("-") && messageData[2].startsWith("-")){
                param1 = messageData[1];
                param2 = messageData[2];
            }
        }

        System.out.println(param1 + " " + param2);
    }

    /**
     * Сверка параметров запроса
     */
    public void paramCheck(){
        this.defineParams();
        if (param1 != null){
            switch (param1){
                case "-help" -> this.helpParamMethod(message);
                case "-date" -> this.dateParamMethod(message);
                case "-type" -> this.typeParamMethod(message);
                case "-extension" -> this.extensionParamMethod(message);
                case "-size" -> this.sizeParamMethod(message);
            }
        } else {
            sendMessage.setText("Err");
        }
    }

    //v--------Методы для обработки запроса--------v

    public void helpParamMethod(String message){
        sendMessage.setText("""
                QuickSortWave - удобный сортировщик файлов.
                Для использования команды /sort существуют следующие параметры:
                -help - выводит информацию о параметре уровня
                -date - сортировка файлов по дате
                -type - выборочная сортировка по типу файла(фото, видео и т.д.)
                -extension - выборочная сортировка по расширению файла
                -size - выборочная сортировка по размеру файла
                *для информации по параметрам второго уровня, используйте 1 параметр(~~help~~) и напишите -help*""");
    //    sendMessage.setParseMode(ParseMode.MARKDOWNV2);
    }

    public void dateParamMethod(String message){
        sendMessage.setText("date");
    }

    public void typeParamMethod(String message){
        sendMessage.setText("type");
    }

    public void extensionParamMethod(String message){
        sendMessage.setText("extension");
    }

    public void sizeParamMethod(String message){
        sendMessage.setText("size");
    }
}
