package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum IndexStatus {
    ACTIVE("ACTIVE"),
    BUILDING("BUILDING"),
    REBUILDING("REBUILDING");

    private static final Map<String, IndexStatus> enumMap;
    private String value;

    static {
        IndexStatus indexStatus = ACTIVE;
        IndexStatus indexStatus2 = BUILDING;
        IndexStatus indexStatus3 = REBUILDING;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("ACTIVE", indexStatus);
        map.put("BUILDING", indexStatus2);
        map.put("REBUILDING", indexStatus3);
    }

    IndexStatus(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static IndexStatus fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, IndexStatus> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
