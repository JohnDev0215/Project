package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum AbortAction {
    CANCEL("CANCEL");

    private static final Map<String, AbortAction> enumMap;
    private String value;

    static {
        AbortAction abortAction = CANCEL;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("CANCEL", abortAction);
    }

    AbortAction(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static AbortAction fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, AbortAction> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
