package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum DynamoKeyType {
    STRING("STRING"),
    NUMBER("NUMBER");

    private static final Map<String, DynamoKeyType> enumMap;
    private String value;

    static {
        DynamoKeyType dynamoKeyType = STRING;
        DynamoKeyType dynamoKeyType2 = NUMBER;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("STRING", dynamoKeyType);
        map.put("NUMBER", dynamoKeyType2);
    }

    DynamoKeyType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static DynamoKeyType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, DynamoKeyType> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
