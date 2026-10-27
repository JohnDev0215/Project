package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum AuditTaskType {
    ON_DEMAND_AUDIT_TASK("ON_DEMAND_AUDIT_TASK"),
    SCHEDULED_AUDIT_TASK("SCHEDULED_AUDIT_TASK");

    private static final Map<String, AuditTaskType> enumMap;
    private String value;

    static {
        AuditTaskType auditTaskType = ON_DEMAND_AUDIT_TASK;
        AuditTaskType auditTaskType2 = SCHEDULED_AUDIT_TASK;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("ON_DEMAND_AUDIT_TASK", auditTaskType);
        map.put("SCHEDULED_AUDIT_TASK", auditTaskType2);
    }

    AuditTaskType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static AuditTaskType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, AuditTaskType> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
