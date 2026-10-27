package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum AuditCheckRunStatus {
    IN_PROGRESS("IN_PROGRESS"),
    WAITING_FOR_DATA_COLLECTION("WAITING_FOR_DATA_COLLECTION"),
    CANCELED("CANCELED"),
    COMPLETED_COMPLIANT("COMPLETED_COMPLIANT"),
    COMPLETED_NON_COMPLIANT("COMPLETED_NON_COMPLIANT"),
    FAILED("FAILED");

    private static final Map<String, AuditCheckRunStatus> enumMap;
    private String value;

    static {
        AuditCheckRunStatus auditCheckRunStatus = IN_PROGRESS;
        AuditCheckRunStatus auditCheckRunStatus2 = WAITING_FOR_DATA_COLLECTION;
        AuditCheckRunStatus auditCheckRunStatus3 = CANCELED;
        AuditCheckRunStatus auditCheckRunStatus4 = COMPLETED_COMPLIANT;
        AuditCheckRunStatus auditCheckRunStatus5 = COMPLETED_NON_COMPLIANT;
        AuditCheckRunStatus auditCheckRunStatus6 = FAILED;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("IN_PROGRESS", auditCheckRunStatus);
        map.put("WAITING_FOR_DATA_COLLECTION", auditCheckRunStatus2);
        map.put("CANCELED", auditCheckRunStatus3);
        map.put("COMPLETED_COMPLIANT", auditCheckRunStatus4);
        map.put("COMPLETED_NON_COMPLIANT", auditCheckRunStatus5);
        map.put("FAILED", auditCheckRunStatus6);
    }

    AuditCheckRunStatus(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static AuditCheckRunStatus fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, AuditCheckRunStatus> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
