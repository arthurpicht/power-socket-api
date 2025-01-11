package de.arthurpicht.powerSocketApi.dummy;

import de.arthurpicht.powerSocketApi.IllegalOperationException;
import de.arthurpicht.powerSocketApi.PowerSocketApiException;
import de.arthurpicht.powerSocketApi.Status;
import de.arthurpicht.powerSocketApi.common.OutletIds;
import de.arthurpicht.powerSocketApi.common.PowerSocketProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DummyProcessor implements PowerSocketProcessor {

    private final DummyConfig dummyConfig;
    private final OutletIds outletIds;

    public DummyProcessor(DummyConfig dummyConfig) {
        this.dummyConfig = dummyConfig;
        this.outletIds = new DummyOutletIds(dummyConfig);
    }

    @Override
    public OutletIds getOutletIds() {
        return this.outletIds;
    }

    @Override
    public Status getStatus() throws PowerSocketApiException {

        List<Status.OutletStatus> outletStatusList = new ArrayList<>();
        DummyFilePersistence dummyFilePersistence = new DummyFilePersistence(this.dummyConfig.getOutletIds());
        for (String outletName : this.dummyConfig.getOutletIds()) {
            boolean powerStatus;
            try {
                powerStatus = dummyFilePersistence.get(outletName);
            } catch (IOException e) {
                throw new PowerSocketApiException(e.getMessage(), e);
            }
            Status.OutletStatus outletStatus = new Status.OutletStatus(outletName, outletName, powerStatus);
            outletStatusList.add(outletStatus);
        }
        return new Status(dummyConfig.getDeviceId(), outletStatusList);
    }

    @Override
    public void switchOn(String outletId) throws PowerSocketApiException, IllegalOperationException {
        DummyFilePersistence dummyFilePersistence = new DummyFilePersistence(this.outletIds.getIds());
        try {
            boolean preset = dummyFilePersistence.get(outletId);
            if (preset)
                throw new IllegalOperationException("Outlet is already set on.");
            dummyFilePersistence.set(outletId, true);
        } catch (IOException e) {
            throw new PowerSocketApiException(e.getMessage(), e);
        }
    }

    @Override
    public void switchOff(String outletId) throws PowerSocketApiException, IllegalOperationException {
        DummyFilePersistence dummyFilePersistence = new DummyFilePersistence(this.outletIds.getIds());
        try {
            boolean preset = dummyFilePersistence.get(outletId);
            if (!preset)
                throw new IllegalOperationException("Outlet is already set off.");
            dummyFilePersistence.set(outletId, false);
        } catch (IOException e) {
            throw new PowerSocketApiException(e.getMessage(), e);
        }
    }

}
