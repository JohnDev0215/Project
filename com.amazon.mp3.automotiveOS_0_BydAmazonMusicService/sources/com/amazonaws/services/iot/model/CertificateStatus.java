package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum CertificateStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    REVOKED("REVOKED"),
    PENDING_TRANSFER("PENDING_TRANSFER"),
    REGISTER_INACTIVE("REGISTER_INACTIVE"),
    PENDING_ACTIVATION("PENDING_ACTIVATION");

    private static final Map<String, CertificateStatus> enumMap;
    private String value;

    static {
        CertificateStatus certificateStatus = ACTIVE;
        CertificateStatus certificateStatus2 = INACTIVE;
        CertificateStatus certificateStatus3 = REVOKED;
        CertificateStatus certificateStatus4 = PENDING_TRANSFER;
        CertificateStatus certificateStatus5 = REGISTER_INACTIVE;
        CertificateStatus certificateStatus6 = PENDING_ACTIVATION;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("ACTIVE", certificateStatus);
        map.put("INACTIVE", certificateStatus2);
        map.put("REVOKED", certificateStatus3);
        map.put("PENDING_TRANSFER", certificateStatus4);
        map.put("REGISTER_INACTIVE", certificateStatus5);
        map.put("PENDING_ACTIVATION", certificateStatus6);
    }

    CertificateStatus(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static CertificateStatus fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, CertificateStatus> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
