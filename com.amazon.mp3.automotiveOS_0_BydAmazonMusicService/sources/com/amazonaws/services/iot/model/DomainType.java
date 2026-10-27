package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum DomainType {
    ENDPOINT("ENDPOINT"),
    AWS_MANAGED("AWS_MANAGED"),
    CUSTOMER_MANAGED("CUSTOMER_MANAGED");

    private static final Map<String, DomainType> enumMap;
    private String value;

    static {
        DomainType domainType = ENDPOINT;
        DomainType domainType2 = AWS_MANAGED;
        DomainType domainType3 = CUSTOMER_MANAGED;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("ENDPOINT", domainType);
        map.put("AWS_MANAGED", domainType2);
        map.put("CUSTOMER_MANAGED", domainType3);
    }

    DomainType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static DomainType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, DomainType> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
