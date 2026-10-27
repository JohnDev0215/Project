package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum AuditTaskStatus {
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED"),
    FAILED("FAILED"),
    CANCELED("CANCELED");

    private static final Map<String, AuditTaskStatus> enumMap;
    private String value;

    static {
        AuditTaskStatus auditTaskStatus = IN_PROGRESS;
        AuditTaskStatus auditTaskStatus2 = COMPLETED;
        AuditTaskStatus auditTaskStatus3 = FAILED;
        AuditTaskStatus auditTaskStatus4 = CANCELED;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("IN_PROGRESS", auditTaskStatus);
        map.put("COMPLETED", auditTaskStatus2);
        map.put("FAILED", auditTaskStatus3);
        map.put("CANCELED", auditTaskStatus4);
    }

    AuditTaskStatus(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static AuditTaskStatus fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, AuditTaskStatus> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
