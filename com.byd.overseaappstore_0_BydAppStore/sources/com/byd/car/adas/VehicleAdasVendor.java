package com.byd.car.adas;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public enum VehicleAdasVendor {
    OTHER(0),
    HUAWEI(1);

    private static Map<String, VehicleAdasVendor> strToVendorMap = new HashMap();
    private final int value;

    static {
        for (VehicleAdasVendor vehicleAdasVendor : values()) {
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
