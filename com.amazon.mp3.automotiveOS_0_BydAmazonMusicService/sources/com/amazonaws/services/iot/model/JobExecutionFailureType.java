package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum JobExecutionFailureType {
    FAILED("FAILED"),
    REJECTED("REJECTED"),
    TIMED_OUT("TIMED_OUT"),
    ALL("ALL");

    private static final Map<String, JobExecutionFailureType> enumMap;
    private String value;

    static {
        JobExecutionFailureType jobExecutionFailureType = FAILED;
        JobExecutionFailureType jobExecutionFailureType2 = REJECTED;
        JobExecutionFailureType jobExecutionFailureType3 = TIMED_OUT;
        JobExecutionFailureType jobExecutionFailureType4 = ALL;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("FAILED", jobExecutionFailureType);
        map.put("REJECTED", jobExecutionFailureType2);
        map.put("TIMED_OUT", jobExecutionFailureType3);
        map.put("ALL", jobExecutionFailureType4);
    }

    JobExecutionFailureType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static JobExecutionFailureType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, JobExecutionFailureType> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
