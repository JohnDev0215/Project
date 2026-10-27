package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum DimensionType {
    TOPIC_FILTER("TOPIC_FILTER");

    private static final Map<String, DimensionType> enumMap;
    private String value;

    static {
        DimensionType dimensionType = TOPIC_FILTER;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("TOPIC_FILTER", dimensionType);
    }

    DimensionType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static DimensionType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, DimensionType> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
