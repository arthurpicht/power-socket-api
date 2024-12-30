package de.arthurpicht.powerSocketApi;

import de.arthurpicht.powerSocketApi.common.PowerSocketConfig;
import de.arthurpicht.powerSocketApi.common.PowerSocketProcessor;
import de.arthurpicht.powerSocketApi.infratecPM8.InfratecPM8Config;
import de.arthurpicht.powerSocketApi.infratecPM8.InfratecPM8Processor;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class PowerSocket {

    private final Map<String, PowerSocketProcessor> powerSocketProcessorMap;

    public PowerSocket(List<PowerSocketConfig> powerSocketConfigList) {
        Map<String, PowerSocketProcessor> powerSocketProcessorHashMapTemp = new HashMap<>();
        for (PowerSocketConfig powerSocketConfig : powerSocketConfigList) {
            PowerSocketProcessor powerSocketProcessor = create(powerSocketConfig);
            powerSocketProcessorHashMapTemp.put(powerSocketConfig.getDeviceId(), powerSocketProcessor);
        }
        this.powerSocketProcessorMap = Collections.unmodifiableMap(powerSocketProcessorHashMapTemp);
    }

    private PowerSocketProcessor create(PowerSocketConfig powerSocketConfig) {
        if (powerSocketConfig instanceof InfratecPM8Config infratecPM8Config) {
            return new InfratecPM8Processor(infratecPM8Config);
        }
        throw new IllegalStateException("Unknown type of PowerSocketConfig.");
    }

    public Status getStatus(String deviceId) throws PowerSocketApiException {
        PowerSocketProcessor powerSocketProcessor = getPowerSocketProcessor(deviceId);
        return powerSocketProcessor.getStatus();
    }

    public void switchOn(String deviceId) throws PowerSocketApiException {
        PowerSocketProcessor powerSocketProcessor = getPowerSocketProcessor(deviceId);
        powerSocketProcessor.switchOn();
    }

    public void switchOff(String deviceId) throws PowerSocketApiException {
        PowerSocketProcessor powerSocketProcessor = getPowerSocketProcessor(deviceId);
        powerSocketProcessor.switchOff();
    }

    private PowerSocketProcessor getPowerSocketProcessor(String deviceId) throws PowerSocketApiException {
        if (!this.powerSocketProcessorMap.containsKey(deviceId))
            throw new PowerSocketApiException("Unknown deviceId: [" + deviceId + "].");
        return this.powerSocketProcessorMap.get(deviceId);
    }

}