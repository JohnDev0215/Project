package com.byd.car.adas;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public enum VehicleAdasVendor {
    OTHER(0),
    HUAWEI(1);

    private static Map<String, VehicleAdasVendor> strToVendorMap = new HashMap();
    private final int value;

    static {
        VehicleAdasVendor[] vehicleAdasVendorArrValues = values();
        for (int i = 0; i < 2; i++) {
            VehicleAdasVendor vehicleAdasVendor = vehicleAdasVendorArrValues[i];
            strToVendorMap.put(vehicleAdasVendor.name().toLowerCase(), vehicleAdasVendor);
        }
    }

    VehicleAdasVendor(int i) {
        this.value = i;
    }

    public static VehicleAdasVendor fromName(String str) {
        return strToVendorMap.getOrDefault(str, OTHER);
    }

    public int getValue() {
        return this.value;
    }
}
