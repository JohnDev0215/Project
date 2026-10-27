package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum AutoRegistrationStatus {
    ENABLE("ENABLE"),
    DISABLE("DISABLE");

    private static final Map<String, AutoRegistrationStatus> enumMap;
    private String value;

    static {
        AutoRegistrationStatus autoRegistrationStatus = ENABLE;
        AutoRegistrationStatus autoRegistrationStatus2 = DISABLE;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("ENABLE", autoRegistrationStatus);
        map.put("DISABLE", autoRegistrationStatus2);
    }

    AutoRegistrationStatus(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static AutoRegistrationStatus fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, AutoRegistrationStatus> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
