package org.csf.Service;

public class Service {
    /**
     * Метод простой обработки сообщений
     * @param text неотформатированный текст
     * @return готовое сообщение
     */
    public String buildTheMessage(String text){
        return text.replaceAll("\\.", "\\\\.")
                .replaceAll("\\(","\\\\(")
                .replaceAll("\\)", "\\\\)")
                .replaceAll("-", "\\\\-")
                .replaceAll("!", "\\\\!");
    }
}
