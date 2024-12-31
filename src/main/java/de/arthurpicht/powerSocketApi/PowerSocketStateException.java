package de.arthurpicht.powerSocketApi;

public class PowerSocketStateException extends PowerSocketApiException {

    public PowerSocketStateException() {
    }

    public PowerSocketStateException(String message) {
        super(message);
    }

    public PowerSocketStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public PowerSocketStateException(Throwable cause) {
        super(cause);
    }

    public PowerSocketStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
