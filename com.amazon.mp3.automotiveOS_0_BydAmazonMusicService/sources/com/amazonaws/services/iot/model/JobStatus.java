package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum JobStatus {
    IN_PROGRESS("IN_PROGRESS"),
    CANCELED("CANCELED"),
    COMPLETED("COMPLETED"),
    DELETION_IN_PROGRESS("DELETION_IN_PROGRESS");

    private static final Map<String, JobStatus> enumMap;
    private String value;

    static {
        JobStatus jobStatus = IN_PROGRESS;
        JobStatus jobStatus2 = CANCELED;
        JobStatus jobStatus3 = COMPLETED;
        JobStatus jobStatus4 = DELETION_IN_PROGRESS;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("IN_PROGRESS", jobStatus);
        map.put("CANCELED", jobStatus2);
        map.put("COMPLETED", jobStatus3);
        map.put("DELETION_IN_PROGRESS", jobStatus4);
    }

    JobStatus(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static JobStatus fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, JobStatus> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
