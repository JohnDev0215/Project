package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum DetectMitigationActionsTaskStatus {
    IN_PROGRESS("IN_PROGRESS"),
    SUCCESSFUL("SUCCESSFUL"),
    FAILED("FAILED"),
    CANCELED("CANCELED");

    private static final Map<String, DetectMitigationActionsTaskStatus> enumMap;
    private String value;

    static {
        DetectMitigationActionsTaskStatus detectMitigationActionsTaskStatus = IN_PROGRESS;
        DetectMitigationActionsTaskStatus detectMitigationActionsTaskStatus2 = SUCCESSFUL;
        DetectMitigationActionsTaskStatus detectMitigationActionsTaskStatus3 = FAILED;
        DetectMitigationActionsTaskStatus detectMitigationActionsTaskStatus4 = CANCELED;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("IN_PROGRESS", detectMitigationActionsTaskStatus);
        map.put("SUCCESSFUL", detectMitigationActionsTaskStatus2);
        map.put("FAILED", detectMitigationActionsTaskStatus3);
        map.put("CANCELED", detectMitigationActionsTaskStatus4);
    }

    DetectMitigationActionsTaskStatus(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static DetectMitigationActionsTaskStatus fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, DetectMitigationActionsTaskStatus> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
