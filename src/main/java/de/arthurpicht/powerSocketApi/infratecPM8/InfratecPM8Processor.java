package de.arthurpicht.powerSocketApi.infratecPM8;

import de.arthurpicht.powerSocketApi.PowerSocketApiException;
import de.arthurpicht.powerSocketApi.common.HttpHelper;
import de.arthurpicht.powerSocketApi.common.PowerSocketProcessor;
import de.arthurpicht.powerSocketApi.Status;

import java.io.IOException;
import java.net.URISyntaxException;

@SuppressWarnings({"SpellCheckingInspection"})
public class InfratecPM8Processor implements PowerSocketProcessor {

    private final InfratecPM8Config infratecPM8Config;

    public InfratecPM8Processor(InfratecPM8Config infratecPM8Config) {
        this.infratecPM8Config = infratecPM8Config;
    }

    @Override
    public Status getStatus() throws PowerSocketApiException {
        String url = "http://" + this.infratecPM8Config.getHost() + "/sw?s=0";
        String response;
        try {
            response = HttpHelper.sendGetRequest(url);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new PowerSocketApiException(
                    "Error on contacting device [" + this.infratecPM8Config.getDeviceId() + "]: " + e.getMessage(), e);
        }
        return StatusParser.parseStatusHtml(this.infratecPM8Config.getDeviceId(), response);
    }

    @Override
    public void switchOn() throws PowerSocketApiException {
        throw new RuntimeException("NIY");
    }

    @Override
    public void switchOff() throws PowerSocketApiException {
        throw new RuntimeException("NIY");
    }

}
