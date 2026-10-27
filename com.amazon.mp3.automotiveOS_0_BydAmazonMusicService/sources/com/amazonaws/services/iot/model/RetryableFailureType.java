package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum RetryableFailureType {
    FAILED("FAILED"),
    TIMED_OUT("TIMED_OUT"),
    ALL("ALL");

    private static final Map<String, RetryableFailureType> enumMap;
    private String value;

    static {
        RetryableFailureType retryableFailureType = FAILED;
        RetryableFailureType retryableFailureType2 = TIMED_OUT;
        RetryableFailureType retryableFailureType3 = ALL;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("FAILED", retryableFailureType);
        map.put("TIMED_OUT", retryableFailureType2);
        map.put("ALL", retryableFailureType3);
    }

    RetryableFailureType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static RetryableFailureType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, RetryableFailureType> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
