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

    VehicleAdasVendor(int value) {
        this.value = value;
    }

    public static VehicleAdasVendor fromName(String name) {
        return strToVendorMap.getOrDefault(name, OTHER);
    }

    public int getValue() {
        return this.value;
    }
}
