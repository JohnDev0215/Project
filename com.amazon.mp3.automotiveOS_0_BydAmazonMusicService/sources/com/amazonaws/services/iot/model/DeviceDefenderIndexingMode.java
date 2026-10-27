package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum DeviceDefenderIndexingMode {
    OFF("OFF"),
    VIOLATIONS("VIOLATIONS");

    private static final Map<String, DeviceDefenderIndexingMode> enumMap;
    private String value;

    static {
        DeviceDefenderIndexingMode deviceDefenderIndexingMode = OFF;
        DeviceDefenderIndexingMode deviceDefenderIndexingMode2 = VIOLATIONS;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("OFF", deviceDefenderIndexingMode);
        map.put("VIOLATIONS", deviceDefenderIndexingMode2);
    }

    DeviceDefenderIndexingMode(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static DeviceDefenderIndexingMode fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, DeviceDefenderIndexingMode> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
