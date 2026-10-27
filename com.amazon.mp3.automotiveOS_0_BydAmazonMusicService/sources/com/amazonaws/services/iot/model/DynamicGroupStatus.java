package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum DynamicGroupStatus {
    ACTIVE("ACTIVE"),
    BUILDING("BUILDING"),
    REBUILDING("REBUILDING");

    private static final Map<String, DynamicGroupStatus> enumMap;
    private String value;

    static {
        DynamicGroupStatus dynamicGroupStatus = ACTIVE;
        DynamicGroupStatus dynamicGroupStatus2 = BUILDING;
        DynamicGroupStatus dynamicGroupStatus3 = REBUILDING;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("ACTIVE", dynamicGroupStatus);
        map.put("BUILDING", dynamicGroupStatus2);
        map.put("REBUILDING", dynamicGroupStatus3);
    }

    DynamicGroupStatus(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static DynamicGroupStatus fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, DynamicGroupStatus> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
