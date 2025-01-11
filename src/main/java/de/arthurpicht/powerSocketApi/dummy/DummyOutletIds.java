package de.arthurpicht.powerSocketApi.dummy;

import de.arthurpicht.powerSocketApi.common.OutletIds;

import java.util.List;

public class DummyOutletIds implements OutletIds {

    private final List<String> outletIds;

    public DummyOutletIds(DummyConfig dummyConfig) {
        this.outletIds = dummyConfig.getOutletIds();
    }

    @Override
    public List<String> getIds() {
        return this.outletIds;
    }

    @Override
    public boolean hasId(String outletId) {
        return this.outletIds.contains(outletId);
    }

    @Override
    public int getOutletNr(String outletId) {
        if (!this.outletIds.contains(outletId))
            throw new IllegalArgumentException(DummyOutletIds.class.getSimpleName()
                                               + " does not contain outletId [" + outletId + "]. Check before calling.");
        return this.outletIds.indexOf(outletId) + 1;
    }

}
