package de.arthurpicht.powerSocketApi.infratecPM8;

public class InfratecConsts {

    public enum OutletId {
        OUTLET_1_1,
        OUTLET_1_2,
        OUTLET_1_3,
        OUTLET_1_4,
        OUTLET_2_1,
        OUTLET_2_2,
        OUTLET_2_3,
        OUTLET_2_4;

        public static boolean hasOutletId(String outletId) {
            for (OutletId outletIdCur : OutletId.values()) {
                if (outletIdCur.name().equals(outletId)) {
                    return true;
                }
            }
            return false;
        }

    }

}
