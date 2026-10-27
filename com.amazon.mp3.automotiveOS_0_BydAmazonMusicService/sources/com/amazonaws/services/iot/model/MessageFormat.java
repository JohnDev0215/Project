package com.amazonaws.services.iot.model;

import com.fasterxml.jackson.core.JsonFactory;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum MessageFormat {
    RAW("RAW"),
    JSON(JsonFactory.FORMAT_NAME_JSON);

    private static final Map<String, MessageFormat> enumMap;
    private String value;

    static {
        MessageFormat messageFormat = RAW;
        MessageFormat messageFormat2 = JSON;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("RAW", messageFormat);
        map.put(JsonFactory.FORMAT_NAME_JSON, messageFormat2);
    }

    MessageFormat(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static MessageFormat fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, MessageFormat> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
