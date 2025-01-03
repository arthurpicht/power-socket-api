package de.arthurpicht.powerSocketApi.common;

import java.util.List;

public interface OutletIds {

    List<String> getIds();

    boolean hasId(String outletId);

    int getOutletNr(String outletId);





}
