package de.arthurpicht.powerSocketApi.common;

import de.arthurpicht.powerSocketApi.IllegalOperationException;
import de.arthurpicht.powerSocketApi.PowerSocketApiException;
import de.arthurpicht.powerSocketApi.Status;

public interface PowerSocketProcessor {

    Status getStatus() throws PowerSocketApiException;

    void switchOn(String outletId) throws PowerSocketApiException, IllegalOperationException;

    void switchOff(String outletId) throws PowerSocketApiException, IllegalOperationException;

}
