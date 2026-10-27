package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum DetectMitigationActionExecutionStatus {
    IN_PROGRESS("IN_PROGRESS"),
    SUCCESSFUL("SUCCESSFUL"),
    FAILED("FAILED"),
    SKIPPED("SKIPPED");

    private static final Map<String, DetectMitigationActionExecutionStatus> enumMap;
    private String value;

    static {
        DetectMitigationActionExecutionStatus detectMitigationActionExecutionStatus = IN_PROGRESS;
        DetectMitigationActionExecutionStatus detectMitigationActionExecutionStatus2 = SUCCESSFUL;
        DetectMitigationActionExecutionStatus detectMitigationActionExecutionStatus3 = FAILED;
        DetectMitigationActionExecutionStatus detectMitigationActionExecutionStatus4 = SKIPPED;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("IN_PROGRESS", detectMitigationActionExecutionStatus);
        map.put("SUCCESSFUL", detectMitigationActionExecutionStatus2);
        map.put("FAILED", detectMitigationActionExecutionStatus3);
        map.put("SKIPPED", detectMitigationActionExecutionStatus4);
    }

    DetectMitigationActionExecutionStatus(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static DetectMitigationActionExecutionStatus fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, DetectMitigationActionExecutionStatus> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
