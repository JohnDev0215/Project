package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum CertificateMode {
    DEFAULT("DEFAULT"),
    SNI_ONLY("SNI_ONLY");

    private static final Map<String, CertificateMode> enumMap;
    private String value;

    static {
        CertificateMode certificateMode = DEFAULT;
        CertificateMode certificateMode2 = SNI_ONLY;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("DEFAULT", certificateMode);
        map.put("SNI_ONLY", certificateMode2);
    }

    CertificateMode(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static CertificateMode fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, CertificateMode> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
