package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum ThingGroupIndexingMode {
    OFF("OFF"),
    ON("ON");

    private static final Map<String, ThingGroupIndexingMode> enumMap;
    private String value;

    static {
        ThingGroupIndexingMode thingGroupIndexingMode = OFF;
        ThingGroupIndexingMode thingGroupIndexingMode2 = ON;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("OFF", thingGroupIndexingMode);
        map.put("ON", thingGroupIndexingMode2);
    }

    ThingGroupIndexingMode(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static ThingGroupIndexingMode fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, ThingGroupIndexingMode> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
