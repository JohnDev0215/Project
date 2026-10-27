package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum AuditFindingSeverity {
    CRITICAL("CRITICAL"),
    HIGH("HIGH"),
    MEDIUM("MEDIUM"),
    LOW("LOW");

    private static final Map<String, AuditFindingSeverity> enumMap;
    private String value;

    static {
        AuditFindingSeverity auditFindingSeverity = CRITICAL;
        AuditFindingSeverity auditFindingSeverity2 = HIGH;
        AuditFindingSeverity auditFindingSeverity3 = MEDIUM;
        AuditFindingSeverity auditFindingSeverity4 = LOW;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("CRITICAL", auditFindingSeverity);
        map.put("HIGH", auditFindingSeverity2);
        map.put("MEDIUM", auditFindingSeverity3);
        map.put("LOW", auditFindingSeverity4);
    }

    AuditFindingSeverity(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static AuditFindingSeverity fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, AuditFindingSeverity> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
