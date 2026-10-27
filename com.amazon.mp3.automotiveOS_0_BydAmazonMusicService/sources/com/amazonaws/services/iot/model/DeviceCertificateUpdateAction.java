package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum DeviceCertificateUpdateAction {
    DEACTIVATE("DEACTIVATE");

    private static final Map<String, DeviceCertificateUpdateAction> enumMap;
    private String value;

    static {
        DeviceCertificateUpdateAction deviceCertificateUpdateAction = DEACTIVATE;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("DEACTIVATE", deviceCertificateUpdateAction);
    }

    DeviceCertificateUpdateAction(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static DeviceCertificateUpdateAction fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, DeviceCertificateUpdateAction> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
