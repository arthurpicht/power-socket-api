package de.arthurpicht.powerSocketApi.infratecPM8;

import de.arthurpicht.powerSocketApi.common.PowerSocketConfig;

@SuppressWarnings({"SpellCheckingInspection"})
public class InfratecPM8Config extends PowerSocketConfig {

    private final String deviceId;
    private final String host;
    private final String username;
    private final String password;

    public InfratecPM8Config(String deviceId, String host, String username, String password) {
        this.deviceId = deviceId;
        this.host = host;
        this.username = username;
        this.password = password;
    }

    @Override
    public String getDeviceId() {
        return this.deviceId;
    }

    public String getHost() {
        return host;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
