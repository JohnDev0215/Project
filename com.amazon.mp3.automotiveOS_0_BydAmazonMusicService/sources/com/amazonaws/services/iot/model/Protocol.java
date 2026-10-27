package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum Protocol {
    MQTT("MQTT"),
    HTTP("HTTP");

    private static final Map<String, Protocol> enumMap;
    private String value;

    static {
        Protocol protocol = MQTT;
        Protocol protocol2 = HTTP;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("MQTT", protocol);
        map.put("HTTP", protocol2);
    }

    Protocol(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static Protocol fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, Protocol> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
