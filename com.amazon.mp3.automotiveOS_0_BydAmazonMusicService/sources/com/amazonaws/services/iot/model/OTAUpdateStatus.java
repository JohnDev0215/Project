package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum OTAUpdateStatus {
    CREATE_PENDING("CREATE_PENDING"),
    CREATE_IN_PROGRESS("CREATE_IN_PROGRESS"),
    CREATE_COMPLETE("CREATE_COMPLETE"),
    CREATE_FAILED("CREATE_FAILED");

    private static final Map<String, OTAUpdateStatus> enumMap;
    private String value;

    static {
        OTAUpdateStatus oTAUpdateStatus = CREATE_PENDING;
        OTAUpdateStatus oTAUpdateStatus2 = CREATE_IN_PROGRESS;
        OTAUpdateStatus oTAUpdateStatus3 = CREATE_COMPLETE;
        OTAUpdateStatus oTAUpdateStatus4 = CREATE_FAILED;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("CREATE_PENDING", oTAUpdateStatus);
        map.put("CREATE_IN_PROGRESS", oTAUpdateStatus2);
        map.put("CREATE_COMPLETE", oTAUpdateStatus3);
        map.put("CREATE_FAILED", oTAUpdateStatus4);
    }

    OTAUpdateStatus(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static OTAUpdateStatus fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, OTAUpdateStatus> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
