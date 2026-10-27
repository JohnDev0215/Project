package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum JobExecutionStatus {
    QUEUED("QUEUED"),
    IN_PROGRESS("IN_PROGRESS"),
    SUCCEEDED("SUCCEEDED"),
    FAILED("FAILED"),
    TIMED_OUT("TIMED_OUT"),
    REJECTED("REJECTED"),
    REMOVED("REMOVED"),
    CANCELED("CANCELED");

    private static final Map<String, JobExecutionStatus> enumMap;
    private String value;

    static {
        JobExecutionStatus jobExecutionStatus = QUEUED;
        JobExecutionStatus jobExecutionStatus2 = IN_PROGRESS;
        JobExecutionStatus jobExecutionStatus3 = SUCCEEDED;
        JobExecutionStatus jobExecutionStatus4 = FAILED;
        JobExecutionStatus jobExecutionStatus5 = TIMED_OUT;
        JobExecutionStatus jobExecutionStatus6 = REJECTED;
        JobExecutionStatus jobExecutionStatus7 = REMOVED;
        JobExecutionStatus jobExecutionStatus8 = CANCELED;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("QUEUED", jobExecutionStatus);
        map.put("IN_PROGRESS", jobExecutionStatus2);
        map.put("SUCCEEDED", jobExecutionStatus3);
        map.put("FAILED", jobExecutionStatus4);
        map.put("TIMED_OUT", jobExecutionStatus5);
        map.put("REJECTED", jobExecutionStatus6);
        map.put("REMOVED", jobExecutionStatus7);
        map.put("CANCELED", jobExecutionStatus8);
    }

    JobExecutionStatus(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static JobExecutionStatus fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, JobExecutionStatus> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
