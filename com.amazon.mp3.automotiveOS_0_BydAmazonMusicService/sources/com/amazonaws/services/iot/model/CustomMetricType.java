package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum CustomMetricType {
    StringList("string-list"),
    IpAddressList("ip-address-list"),
    NumberList("number-list"),
    Number("number");

    private static final Map<String, CustomMetricType> enumMap;
    private String value;

    static {
        CustomMetricType customMetricType = StringList;
        CustomMetricType customMetricType2 = IpAddressList;
        CustomMetricType customMetricType3 = NumberList;
        CustomMetricType customMetricType4 = Number;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("string-list", customMetricType);
        map.put("ip-address-list", customMetricType2);
        map.put("number-list", customMetricType3);
        map.put("number", customMetricType4);
    }

    CustomMetricType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static CustomMetricType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, CustomMetricType> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
