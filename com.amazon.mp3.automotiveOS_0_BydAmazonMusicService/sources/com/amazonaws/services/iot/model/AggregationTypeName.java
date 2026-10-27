package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum AggregationTypeName {
    Statistics("Statistics"),
    Percentiles("Percentiles"),
    Cardinality("Cardinality");

    private static final Map<String, AggregationTypeName> enumMap;
    private String value;

    static {
        AggregationTypeName aggregationTypeName = Statistics;
        AggregationTypeName aggregationTypeName2 = Percentiles;
        AggregationTypeName aggregationTypeName3 = Cardinality;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("Statistics", aggregationTypeName);
        map.put("Percentiles", aggregationTypeName2);
        map.put("Cardinality", aggregationTypeName3);
    }

    AggregationTypeName(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static AggregationTypeName fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, AggregationTypeName> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
