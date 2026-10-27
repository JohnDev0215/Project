package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum AuthorizerStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private static final Map<String, AuthorizerStatus> enumMap;
    private String value;

    static {
        AuthorizerStatus authorizerStatus = ACTIVE;
        AuthorizerStatus authorizerStatus2 = INACTIVE;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("ACTIVE", authorizerStatus);
        map.put("INACTIVE", authorizerStatus2);
    }

    AuthorizerStatus(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static AuthorizerStatus fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, AuthorizerStatus> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
