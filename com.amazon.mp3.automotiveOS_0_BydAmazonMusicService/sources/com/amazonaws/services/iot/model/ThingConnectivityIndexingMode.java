package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum ThingConnectivityIndexingMode {
    OFF("OFF"),
    STATUS("STATUS");

    private static final Map<String, ThingConnectivityIndexingMode> enumMap;
    private String value;

    static {
        ThingConnectivityIndexingMode thingConnectivityIndexingMode = OFF;
        ThingConnectivityIndexingMode thingConnectivityIndexingMode2 = STATUS;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("OFF", thingConnectivityIndexingMode);
        map.put("STATUS", thingConnectivityIndexingMode2);
    }

    ThingConnectivityIndexingMode(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static ThingConnectivityIndexingMode fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, ThingConnectivityIndexingMode> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
