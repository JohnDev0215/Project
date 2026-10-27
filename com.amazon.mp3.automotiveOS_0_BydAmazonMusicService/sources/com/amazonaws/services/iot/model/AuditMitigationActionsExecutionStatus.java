package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum AuditMitigationActionsExecutionStatus {
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED"),
    FAILED("FAILED"),
    CANCELED("CANCELED"),
    SKIPPED("SKIPPED"),
    PENDING("PENDING");

    private static final Map<String, AuditMitigationActionsExecutionStatus> enumMap;
    private String value;

    static {
        AuditMitigationActionsExecutionStatus auditMitigationActionsExecutionStatus = IN_PROGRESS;
        AuditMitigationActionsExecutionStatus auditMitigationActionsExecutionStatus2 = COMPLETED;
        AuditMitigationActionsExecutionStatus auditMitigationActionsExecutionStatus3 = FAILED;
        AuditMitigationActionsExecutionStatus auditMitigationActionsExecutionStatus4 = CANCELED;
        AuditMitigationActionsExecutionStatus auditMitigationActionsExecutionStatus5 = SKIPPED;
        AuditMitigationActionsExecutionStatus auditMitigationActionsExecutionStatus6 = PENDING;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("IN_PROGRESS", auditMitigationActionsExecutionStatus);
        map.put("COMPLETED", auditMitigationActionsExecutionStatus2);
        map.put("FAILED", auditMitigationActionsExecutionStatus3);
        map.put("CANCELED", auditMitigationActionsExecutionStatus4);
        map.put("SKIPPED", auditMitigationActionsExecutionStatus5);
        map.put("PENDING", auditMitigationActionsExecutionStatus6);
    }

    AuditMitigationActionsExecutionStatus(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static AuditMitigationActionsExecutionStatus fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, AuditMitigationActionsExecutionStatus> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
