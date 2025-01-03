package de.arthurpicht.powerSocketApi;

import de.arthurpicht.powerSocketApi.common.OutletIds;
import de.arthurpicht.powerSocketApi.common.PowerSocketConfig;
import de.arthurpicht.powerSocketApi.common.PowerSocketProcessor;
import de.arthurpicht.powerSocketApi.infratecPM8.InfratecPM8Config;
import de.arthurpicht.powerSocketApi.infratecPM8.InfratecPM8Processor;

import java.util.*;

@SuppressWarnings("unused")
public class PowerSocket {

    private final Map<String, PowerSocketProcessor> powerSocketProcessorMap;

    public PowerSocket(List<PowerSocketConfig> powerSocketConfigList) {
        Map<String, PowerSocketProcessor> powerSocketProcessorHashMapTemp = new HashMap<>();
        for (PowerSocketConfig powerSocketConfig : powerSocketConfigList) {
            String deviceId = powerSocketConfig.getDeviceId();
            if (powerSocketProcessorHashMapTemp.containsKey(deviceId))
                throw new IllegalArgumentException("Configuration for deviceId already specified: [" + deviceId + "].");
            PowerSocketProcessor powerSocketProcessor = create(powerSocketConfig);
            powerSocketProcessorHashMapTemp.put(deviceId, powerSocketProcessor);
        }
        this.powerSocketProcessorMap = Collections.unmodifiableMap(powerSocketProcessorHashMapTemp);
    }

    private PowerSocketProcessor create(PowerSocketConfig powerSocketConfig) {
        if (powerSocketConfig instanceof InfratecPM8Config infratecPM8Config) {
            return new InfratecPM8Processor(infratecPM8Config);
        }
        throw new IllegalStateException("Unknown type of PowerSocketConfig.");
    }

    public Set<String> getDeviceIds() {
        return this.powerSocketProcessorMap.keySet();
    }

    public Status getStatus(String deviceId) throws PowerSocketApiException {
        PowerSocketProcessor powerSocketProcessor = getPowerSocketProcessor(deviceId);
        return powerSocketProcessor.getStatus();
    }

    public void switchOn(String deviceId, String outletId) throws PowerSocketApiException, IllegalOperationException {
        PowerSocketProcessor powerSocketProcessor = getPowerSocketProcessor(deviceId);
        powerSocketProcessor.switchOn(outletId);
    }

    public void switchOff(String deviceId, String outletId) throws PowerSocketApiException, IllegalOperationException {
        PowerSocketProcessor powerSocketProcessor = getPowerSocketProcessor(deviceId);
        powerSocketProcessor.switchOff(outletId);
    }

    public OutletIds getOutletIds(String deviceId) throws PowerSocketApiException {
        PowerSocketProcessor powerSocketProcessor = getPowerSocketProcessor(deviceId);
        return powerSocketProcessor.getOutletIds();
    }

    private PowerSocketProcessor getPowerSocketProcessor(String deviceId) throws PowerSocketApiException {

        if (!this.powerSocketProcessorMap.containsKey(deviceId))
            throw new PowerSocketApiException("Unknown deviceId: [" + deviceId + "].");
        return this.powerSocketProcessorMap.get(deviceId);
    }

}
