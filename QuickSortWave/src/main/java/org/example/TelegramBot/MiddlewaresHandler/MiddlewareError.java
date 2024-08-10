package org.example.TelegramBot.MiddlewaresHandler;

public abstract class MiddlewareError extends Exception {

    private final Enum<?> errorType;

    protected MiddlewareError(Enum<?> errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public Enum<?> getErrorType() {
        return errorType;
    }
}
