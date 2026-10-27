package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum Status {
    InProgress("InProgress"),
    Completed("Completed"),
    Failed("Failed"),
    Cancelled("Cancelled"),
    Cancelling("Cancelling");

    private static final Map<String, Status> enumMap;
    private String value;

    static {
        Status status = InProgress;
        Status status2 = Completed;
        Status status3 = Failed;
        Status status4 = Cancelled;
        Status status5 = Cancelling;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("InProgress", status);
        map.put("Completed", status2);
        map.put("Failed", status3);
        map.put("Cancelled", status4);
        map.put("Cancelling", status5);
    }

    Status(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static Status fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, Status> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
