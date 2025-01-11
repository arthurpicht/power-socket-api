package de.arthurpicht.powerSocketApi.dummy;

import de.arthurpicht.powerSocketApi.common.PowerSocketConfig;

import java.util.List;

public class DummyConfig extends PowerSocketConfig {

    private final String deviceId;
    private final List<String> outletIds;

    public DummyConfig(String deviceId, List<String> outletIds) {
        this.deviceId = deviceId;
        this.outletIds = outletIds;
    }

    @Override
    public String getDeviceId() {
        return this.deviceId;
    }

    public List<String> getOutletIds() {
        return this.outletIds;
    }

}
