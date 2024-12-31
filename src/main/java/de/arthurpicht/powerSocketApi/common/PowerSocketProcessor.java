package de.arthurpicht.powerSocketApi.common;

import de.arthurpicht.powerSocketApi.PowerSocketApiException;
import de.arthurpicht.powerSocketApi.Status;

public interface PowerSocketProcessor {

    Status getStatus() throws PowerSocketApiException;

    void switchOn(String outletId) throws PowerSocketApiException;

    void switchOff(String outletId) throws PowerSocketApiException;

}
