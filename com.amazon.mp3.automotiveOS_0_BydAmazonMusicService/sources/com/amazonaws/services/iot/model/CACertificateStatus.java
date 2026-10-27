package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum CACertificateStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private static final Map<String, CACertificateStatus> enumMap;
    private String value;

    static {
        CACertificateStatus cACertificateStatus = ACTIVE;
        CACertificateStatus cACertificateStatus2 = INACTIVE;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("ACTIVE", cACertificateStatus);
        map.put("INACTIVE", cACertificateStatus2);
    }

    CACertificateStatus(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static CACertificateStatus fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, CACertificateStatus> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
