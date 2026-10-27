package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum DimensionValueOperator {
    IN("IN"),
    NOT_IN("NOT_IN");

    private static final Map<String, DimensionValueOperator> enumMap;
    private String value;

    static {
        DimensionValueOperator dimensionValueOperator = IN;
        DimensionValueOperator dimensionValueOperator2 = NOT_IN;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("IN", dimensionValueOperator);
        map.put("NOT_IN", dimensionValueOperator2);
    }

    DimensionValueOperator(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static DimensionValueOperator fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, DimensionValueOperator> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
