package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum ReportType {
    ERRORS("ERRORS"),
    RESULTS("RESULTS");

    private static final Map<String, ReportType> enumMap;
    private String value;

    static {
        ReportType reportType = ERRORS;
        ReportType reportType2 = RESULTS;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("ERRORS", reportType);
        map.put("RESULTS", reportType2);
    }

    ReportType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static ReportType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, ReportType> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
