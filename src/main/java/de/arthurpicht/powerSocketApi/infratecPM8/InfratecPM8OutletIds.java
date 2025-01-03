package de.arthurpicht.powerSocketApi.infratecPM8;

import de.arthurpicht.powerSocketApi.common.OutletIds;

import java.util.List;

public class InfratecPM8OutletIds implements OutletIds {

    private static final List<String> OUTLET_IDS = List.of("1.1", "1.2", "1.3", "1.4", "2.1", "2.2", "2.3", "2.4");


    @Override
    public List<String> getIds() {
        return OUTLET_IDS;
    }

    @Override
    public boolean hasId(String outletId) {
        return this.getIds().contains(outletId);
    }

    @Override
    public int getOutletNr(String outletId) {
        if (!OUTLET_IDS.contains(outletId))
            throw new IllegalArgumentException(InfratecPM8OutletIds.class.getSimpleName()
                    + " does not contain outletId [" + outletId + "]. Check before calling.");
        return OUTLET_IDS.indexOf(outletId) + 1;
    }

}
