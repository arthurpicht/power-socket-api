package de.arthurpicht.powerSocketApi;

import java.util.List;

public record Status(String deviceId, List<OutletStatus> outletStatusList) {

    public record OutletStatus(String outletId, String outletName, boolean power) {
    }

    public OutletStatus getOutletStatus(String outletId) {
        for (OutletStatus outletStatus : this.outletStatusList) {
            if (outletStatus.outletId.equals(outletId)) return outletStatus;
        }
        throw new IllegalStateException("No OutletStatus found for outletId: [" + outletId + "].");
    }

}
