package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum LogTargetType {
    DEFAULT("DEFAULT"),
    THING_GROUP("THING_GROUP"),
    CLIENT_ID("CLIENT_ID"),
    SOURCE_IP("SOURCE_IP"),
    PRINCIPAL_ID("PRINCIPAL_ID");

    private static final Map<String, LogTargetType> enumMap;
    private String value;

    static {
        LogTargetType logTargetType = DEFAULT;
        LogTargetType logTargetType2 = THING_GROUP;
        LogTargetType logTargetType3 = CLIENT_ID;
        LogTargetType logTargetType4 = SOURCE_IP;
        LogTargetType logTargetType5 = PRINCIPAL_ID;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("DEFAULT", logTargetType);
        map.put("THING_GROUP", logTargetType2);
        map.put("CLIENT_ID", logTargetType3);
        map.put("SOURCE_IP", logTargetType4);
        map.put("PRINCIPAL_ID", logTargetType5);
    }

    LogTargetType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static LogTargetType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, LogTargetType> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
