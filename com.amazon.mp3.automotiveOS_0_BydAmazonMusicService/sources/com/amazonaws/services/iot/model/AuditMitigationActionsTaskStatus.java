package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum AuditMitigationActionsTaskStatus {
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED"),
    FAILED("FAILED"),
    CANCELED("CANCELED");

    private static final Map<String, AuditMitigationActionsTaskStatus> enumMap;
    private String value;

    static {
        AuditMitigationActionsTaskStatus auditMitigationActionsTaskStatus = IN_PROGRESS;
        AuditMitigationActionsTaskStatus auditMitigationActionsTaskStatus2 = COMPLETED;
        AuditMitigationActionsTaskStatus auditMitigationActionsTaskStatus3 = FAILED;
        AuditMitigationActionsTaskStatus auditMitigationActionsTaskStatus4 = CANCELED;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("IN_PROGRESS", auditMitigationActionsTaskStatus);
        map.put("COMPLETED", auditMitigationActionsTaskStatus2);
        map.put("FAILED", auditMitigationActionsTaskStatus3);
        map.put("CANCELED", auditMitigationActionsTaskStatus4);
    }

    AuditMitigationActionsTaskStatus(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static AuditMitigationActionsTaskStatus fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, AuditMitigationActionsTaskStatus> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
