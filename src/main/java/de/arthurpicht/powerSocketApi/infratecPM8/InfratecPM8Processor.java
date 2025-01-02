package de.arthurpicht.powerSocketApi.infratecPM8;

import de.arthurpicht.powerSocketApi.PowerSocketApiException;
import de.arthurpicht.powerSocketApi.IllegalOperationException;
import de.arthurpicht.powerSocketApi.common.HttpHelper;
import de.arthurpicht.powerSocketApi.common.PowerSocketProcessor;
import de.arthurpicht.powerSocketApi.Status;

import java.io.IOException;
import java.net.URISyntaxException;

@SuppressWarnings({"SpellCheckingInspection"})
public class InfratecPM8Processor implements PowerSocketProcessor {

    private enum Function { ON, OFF }
    private final InfratecPM8Config infratecPM8Config;

    public InfratecPM8Processor(InfratecPM8Config infratecPM8Config) {
        this.infratecPM8Config = infratecPM8Config;
    }

    @Override
    public Status getStatus() throws PowerSocketApiException {
        String url = "http://" + this.infratecPM8Config.getHost() + "/sw?s=0";
        String response = makeHttpRequest(url);
        return StatusParser.parseStatusHtml(this.infratecPM8Config.getDeviceId(), response);
    }

    @Override
    public void switchOn(String outletId) throws PowerSocketApiException, IllegalOperationException {
        assertOutletStatusNotYet(outletId, Function.ON);
        String url = getUrlForSwitch(outletId, Function.ON);
        String response = makeHttpRequest(url);
        assertOutletStatusReached(outletId, Function.ON, response);
    }

    @Override
    public void switchOff(String outletId) throws PowerSocketApiException, IllegalOperationException {
        assertOutletStatusNotYet(outletId, Function.OFF);
        String url = getUrlForSwitch(outletId, Function.OFF);
        String response = makeHttpRequest(url);
        assertOutletStatusReached(outletId, Function.OFF, response);
    }

    private void assertOutletStatusNotYet(String outletId, Function functionExpNot) throws PowerSocketApiException, IllegalOperationException {
        Status status = getStatus();
        Status.OutletStatus outletStatus = status.getOutletStatus(outletId);

        String errorMessage = "Outlet [" + this.infratecPM8Config.getDeviceId() + "] " +
                "[" + outletId + "] [" + outletStatus.outletName() + "] already in state " + functionExpNot.name() + ".";

        if (functionExpNot == Function.ON && outletStatus.isPoweredOn()) {
            throw new IllegalOperationException(errorMessage);
        } else if (functionExpNot == Function.OFF && !outletStatus.isPoweredOn()) {
            throw new IllegalOperationException(errorMessage);
        }
    }

    private void assertOutletStatusReached(String outletId, Function functionReached, String htmlResponse) throws PowerSocketApiException {
        Status status = StatusParser.parseActionHtml(this.infratecPM8Config.getDeviceId(), htmlResponse);
        Status.OutletStatus outletStatus = status.getOutletStatus(outletId);
        String errorMessage = "Error on switching " + functionReached.name() + " [" + this.infratecPM8Config.getDeviceId() + "] " +
                "[" + outletId + "] [" + outletStatus.outletName() + "].";
        if (functionReached == Function.ON && !outletStatus.isPoweredOn()) {
            throw new PowerSocketApiException(errorMessage);
        } else if (functionReached == Function.OFF && outletStatus.isPoweredOn()) {
            throw new PowerSocketApiException(errorMessage);
        }
    }

    private String makeHttpRequest(String url) throws PowerSocketApiException {
        try {
            return HttpHelper.sendGetRequest(url);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new PowerSocketApiException(
                    "Error on contacting device [" + this.infratecPM8Config.getDeviceId() + "]: " + e.getMessage(), e);
        }
    }

    private String getUrlForSwitch(String outletId, Function function) {
        String functionString = function.name().toLowerCase();
        int outletNumber = getOutletNumber(outletId);
        return "http://" + this.infratecPM8Config.getHost() + "/sw?u=" + this.infratecPM8Config.getUsername()
                + "&p=" + this.infratecPM8Config.getPassword()
                + "&o=" + outletNumber
                + "&f=" + functionString;
    }

    private int getOutletNumber(String outletId) {
        try {
            return InfratecConsts.OutletId.valueOf(outletId).ordinal() + 1;
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Unknown outletId for InfratecPM8: [" + outletId + "].");
        }
    }

}
