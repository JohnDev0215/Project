package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum ModelStatus {
    PENDING_BUILD("PENDING_BUILD"),
    ACTIVE("ACTIVE"),
    EXPIRED("EXPIRED");

    private static final Map<String, ModelStatus> enumMap;
    private String value;

    static {
        ModelStatus modelStatus = PENDING_BUILD;
        ModelStatus modelStatus2 = ACTIVE;
        ModelStatus modelStatus3 = EXPIRED;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("PENDING_BUILD", modelStatus);
        map.put("ACTIVE", modelStatus2);
        map.put("EXPIRED", modelStatus3);
    }

    ModelStatus(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static ModelStatus fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, ModelStatus> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
