package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum AuthDecision {
    ALLOWED("ALLOWED"),
    EXPLICIT_DENY("EXPLICIT_DENY"),
    IMPLICIT_DENY("IMPLICIT_DENY");

    private static final Map<String, AuthDecision> enumMap;
    private String value;

    static {
        AuthDecision authDecision = ALLOWED;
        AuthDecision authDecision2 = EXPLICIT_DENY;
        AuthDecision authDecision3 = IMPLICIT_DENY;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("ALLOWED", authDecision);
        map.put("EXPLICIT_DENY", authDecision2);
        map.put("IMPLICIT_DENY", authDecision3);
    }

    AuthDecision(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static AuthDecision fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, AuthDecision> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
