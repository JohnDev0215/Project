package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum ThingIndexingMode {
    OFF("OFF"),
    REGISTRY("REGISTRY"),
    REGISTRY_AND_SHADOW("REGISTRY_AND_SHADOW");

    private static final Map<String, ThingIndexingMode> enumMap;
    private String value;

    static {
        ThingIndexingMode thingIndexingMode = OFF;
        ThingIndexingMode thingIndexingMode2 = REGISTRY;
        ThingIndexingMode thingIndexingMode3 = REGISTRY_AND_SHADOW;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("OFF", thingIndexingMode);
        map.put("REGISTRY", thingIndexingMode2);
        map.put("REGISTRY_AND_SHADOW", thingIndexingMode3);
    }

    ThingIndexingMode(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static ThingIndexingMode fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, ThingIndexingMode> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
