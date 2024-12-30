package de.arthurpicht.powerSocketApi;

public class PowerSocketApiException extends Exception {

    public PowerSocketApiException() {
    }

    public PowerSocketApiException(String message) {
        super(message);
    }

    public PowerSocketApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public PowerSocketApiException(Throwable cause) {
        super(cause);
    }

    public PowerSocketApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
