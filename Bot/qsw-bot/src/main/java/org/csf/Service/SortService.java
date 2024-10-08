package org.csf.Service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.csf.Constants.Constants.*;

/**
 * Сервис для обработки параметров команды типа "/sort"
 */
@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
public class SortService extends Service{
    public String param1; //параметр первого уровня
    public String param2; //параметр второго уровня
    protected final String message; //отправленное сообщение
    protected final SendMessage sendMessage; //ответ бота
    protected final Update update; //вся информация из сообщения

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
                case "-help" -> this.helpParamMethod(update);
                case "-date" -> this.dateParamMethod(update);
                case "-type" -> this.typeParamMethod(update);
                case "-extension" -> this.extensionParamMethod(update);
                case "-size" -> this.sizeParamMethod(update);
            }
        } else {
            sendMessage.setText("Err");
        }
    }

    //v--------Методы для обработки запроса--------v

    /**
     * Помощь вв использовании параметров
     * @param update - обновление с полной информацией
     */
    public void helpParamMethod(Update update){
        String text = """
                *QuickSortWave* - удобный сортировщик файлов
                Для использования команды /sort существуют следующие параметры:
                -help - выводит информацию о параметре уровня
                -date - сортировка файлов по дате
                -type - выборочная сортировка по типу файла(фото, видео и т.д.)
                -extension - выборочная сортировка по расширению файла
                -size - выборочная сортировка по размеру файла
                _для информации по параметрам второго уровня используйте 1 параметр(~help~) и напишите -help_
                """;

        sendMessage.setText(buildTheMessage(text));
    }

    /**
     * Метод сортиривки по различным системам измерения времени
     * @param update - обновление, чтобы достать что-нибудь важное
     */
    @SneakyThrows
    public void dateParamMethod(Update update){
        switch(param2){
            case "-year" -> {
                renameFile("file1.txt", "copy.txt"); //тестовая штука TODO
            }

            case "-time" -> {
            }
        }
    }

    public void typeParamMethod(Update update){
        sendMessage.setText("type");
    }

    public void extensionParamMethod(Update update){
        sendMessage.setText("extension");
    }

    public void sizeParamMethod(Update update){
        sendMessage.setText("size");
    }

    /**
     * Переименовка файла
     * @param file - имя старого файла
     * @param copy - имя новой копии
     * @throws IOException - для работы с файлами нужно это пробросить
     */
    public void renameFile(String file, String copy) throws IOException {
        Path source = Paths.get("src/main/java/org/csf/Service/sort", file);
        try{
            Files.move(source, source.resolveSibling(copy));
        } catch (IOException e) {
            System.err.println(e);
        }
        sendMessage.setText(buildTheMessage("ok!"));
    }

}
