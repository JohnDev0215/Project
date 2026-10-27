package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum FieldType {
    Number("Number"),
    String("String"),
    Boolean("Boolean");

    private static final Map<String, FieldType> enumMap;
    private String value;

    static {
        FieldType fieldType = Number;
        FieldType fieldType2 = String;
        FieldType fieldType3 = Boolean;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("Number", fieldType);
        map.put("String", fieldType2);
        map.put("Boolean", fieldType3);
    }

    FieldType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static FieldType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, FieldType> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
