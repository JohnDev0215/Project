package com.amazonaws.services.iot.model;

import com.google.android.gms.cast.MediaError;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum LogLevel {
    DEBUG("DEBUG"),
    INFO("INFO"),
    ERROR(MediaError.ERROR_TYPE_ERROR),
    WARN("WARN"),
    DISABLED("DISABLED");

    private static final Map<String, LogLevel> enumMap;
    private String value;

    static {
        LogLevel logLevel = DEBUG;
        LogLevel logLevel2 = INFO;
        LogLevel logLevel3 = ERROR;
        LogLevel logLevel4 = WARN;
        LogLevel logLevel5 = DISABLED;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("DEBUG", logLevel);
        map.put("INFO", logLevel2);
        map.put(MediaError.ERROR_TYPE_ERROR, logLevel3);
        map.put("WARN", logLevel4);
        map.put("DISABLED", logLevel5);
    }

    LogLevel(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static LogLevel fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, LogLevel> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
