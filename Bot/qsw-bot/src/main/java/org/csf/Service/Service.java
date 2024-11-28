package org.csf.Service;

public class Service {
    /**
     * Simple function to format messages
     * @param text - non-formatted text
     * @return compiled message
     */
    public String buildTheMessage(String text){
        return text.replaceAll("\\.", "\\\\.")
                .replaceAll("\\(","\\\\(")
                .replaceAll("\\)", "\\\\)")
                .replaceAll("-", "\\\\-")
                .replaceAll("!", "\\\\!");
    }
}
