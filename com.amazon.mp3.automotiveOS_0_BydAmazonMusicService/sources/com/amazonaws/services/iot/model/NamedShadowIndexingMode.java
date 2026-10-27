package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum NamedShadowIndexingMode {
    OFF("OFF"),
    ON("ON");

    private static final Map<String, NamedShadowIndexingMode> enumMap;
    private String value;

    static {
        NamedShadowIndexingMode namedShadowIndexingMode = OFF;
        NamedShadowIndexingMode namedShadowIndexingMode2 = ON;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("OFF", namedShadowIndexingMode);
        map.put("ON", namedShadowIndexingMode2);
    }

    NamedShadowIndexingMode(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static NamedShadowIndexingMode fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, NamedShadowIndexingMode> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
