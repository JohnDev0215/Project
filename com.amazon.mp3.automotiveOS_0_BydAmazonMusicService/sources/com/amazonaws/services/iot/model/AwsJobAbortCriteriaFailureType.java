package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum AwsJobAbortCriteriaFailureType {
    FAILED("FAILED"),
    REJECTED("REJECTED"),
    TIMED_OUT("TIMED_OUT"),
    ALL("ALL");

    private static final Map<String, AwsJobAbortCriteriaFailureType> enumMap;
    private String value;

    static {
        AwsJobAbortCriteriaFailureType awsJobAbortCriteriaFailureType = FAILED;
        AwsJobAbortCriteriaFailureType awsJobAbortCriteriaFailureType2 = REJECTED;
        AwsJobAbortCriteriaFailureType awsJobAbortCriteriaFailureType3 = TIMED_OUT;
        AwsJobAbortCriteriaFailureType awsJobAbortCriteriaFailureType4 = ALL;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("FAILED", awsJobAbortCriteriaFailureType);
        map.put("REJECTED", awsJobAbortCriteriaFailureType2);
        map.put("TIMED_OUT", awsJobAbortCriteriaFailureType3);
        map.put("ALL", awsJobAbortCriteriaFailureType4);
    }

    AwsJobAbortCriteriaFailureType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static AwsJobAbortCriteriaFailureType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, AwsJobAbortCriteriaFailureType> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
