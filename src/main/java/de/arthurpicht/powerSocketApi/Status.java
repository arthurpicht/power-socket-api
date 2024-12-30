package de.arthurpicht.powerSocketApi;

import java.util.List;

public record Status(String deviceId, List<OutletStatus> outletStatusList) {

    public record OutletStatus(String outletName, boolean power) {
    }

}
