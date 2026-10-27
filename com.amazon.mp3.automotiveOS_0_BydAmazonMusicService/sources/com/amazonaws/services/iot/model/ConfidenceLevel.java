package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum ConfidenceLevel {
    LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH");

    private static final Map<String, ConfidenceLevel> enumMap;
    private String value;

    static {
        ConfidenceLevel confidenceLevel = LOW;
        ConfidenceLevel confidenceLevel2 = MEDIUM;
        ConfidenceLevel confidenceLevel3 = HIGH;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("LOW", confidenceLevel);
        map.put("MEDIUM", confidenceLevel2);
        map.put("HIGH", confidenceLevel3);
    }

    ConfidenceLevel(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static ConfidenceLevel fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, ConfidenceLevel> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
