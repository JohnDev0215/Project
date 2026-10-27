package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum ServiceType {
    DATA("DATA"),
    CREDENTIAL_PROVIDER("CREDENTIAL_PROVIDER"),
    JOBS("JOBS");

    private static final Map<String, ServiceType> enumMap;
    private String value;

    static {
        ServiceType serviceType = DATA;
        ServiceType serviceType2 = CREDENTIAL_PROVIDER;
        ServiceType serviceType3 = JOBS;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("DATA", serviceType);
        map.put("CREDENTIAL_PROVIDER", serviceType2);
        map.put("JOBS", serviceType3);
    }

    ServiceType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static ServiceType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, ServiceType> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
