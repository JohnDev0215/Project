package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum ServerCertificateStatus {
    INVALID("INVALID"),
    VALID("VALID");

    private static final Map<String, ServerCertificateStatus> enumMap;
    private String value;

    static {
        ServerCertificateStatus serverCertificateStatus = INVALID;
        ServerCertificateStatus serverCertificateStatus2 = VALID;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("INVALID", serverCertificateStatus);
        map.put("VALID", serverCertificateStatus2);
    }

    ServerCertificateStatus(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static ServerCertificateStatus fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, ServerCertificateStatus> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
