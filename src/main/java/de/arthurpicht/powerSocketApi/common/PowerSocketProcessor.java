package de.arthurpicht.powerSocketApi.common;

import de.arthurpicht.powerSocketApi.PowerSocketApiException;
import de.arthurpicht.powerSocketApi.Status;

public interface PowerSocketProcessor {

    Status getStatus() throws PowerSocketApiException;

    void switchOn() throws PowerSocketApiException;

    void switchOff() throws PowerSocketApiException;

}
