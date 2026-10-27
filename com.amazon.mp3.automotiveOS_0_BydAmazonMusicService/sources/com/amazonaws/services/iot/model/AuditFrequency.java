package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum AuditFrequency {
    DAILY("DAILY"),
    WEEKLY("WEEKLY"),
    BIWEEKLY("BIWEEKLY"),
    MONTHLY("MONTHLY");

    private static final Map<String, AuditFrequency> enumMap;
    private String value;

    static {
        AuditFrequency auditFrequency = DAILY;
        AuditFrequency auditFrequency2 = WEEKLY;
        AuditFrequency auditFrequency3 = BIWEEKLY;
        AuditFrequency auditFrequency4 = MONTHLY;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("DAILY", auditFrequency);
        map.put("WEEKLY", auditFrequency2);
        map.put("BIWEEKLY", auditFrequency3);
        map.put("MONTHLY", auditFrequency4);
    }

    AuditFrequency(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static AuditFrequency fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, AuditFrequency> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
