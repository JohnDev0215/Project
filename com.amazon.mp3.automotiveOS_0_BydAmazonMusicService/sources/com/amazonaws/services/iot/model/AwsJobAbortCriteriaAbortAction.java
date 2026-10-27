package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum AwsJobAbortCriteriaAbortAction {
    CANCEL("CANCEL");

    private static final Map<String, AwsJobAbortCriteriaAbortAction> enumMap;
    private String value;

    static {
        AwsJobAbortCriteriaAbortAction awsJobAbortCriteriaAbortAction = CANCEL;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("CANCEL", awsJobAbortCriteriaAbortAction);
    }

    AwsJobAbortCriteriaAbortAction(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static AwsJobAbortCriteriaAbortAction fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, AwsJobAbortCriteriaAbortAction> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
