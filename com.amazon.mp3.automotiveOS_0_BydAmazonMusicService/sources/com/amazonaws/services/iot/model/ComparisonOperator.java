package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum ComparisonOperator {
    LessThan("less-than"),
    LessThanEquals("less-than-equals"),
    GreaterThan("greater-than"),
    GreaterThanEquals("greater-than-equals"),
    InCidrSet("in-cidr-set"),
    NotInCidrSet("not-in-cidr-set"),
    InPortSet("in-port-set"),
    NotInPortSet("not-in-port-set"),
    InSet("in-set"),
    NotInSet("not-in-set");

    private static final Map<String, ComparisonOperator> enumMap;
    private String value;

    static {
        ComparisonOperator comparisonOperator = LessThan;
        ComparisonOperator comparisonOperator2 = LessThanEquals;
        ComparisonOperator comparisonOperator3 = GreaterThan;
        ComparisonOperator comparisonOperator4 = GreaterThanEquals;
        ComparisonOperator comparisonOperator5 = InCidrSet;
        ComparisonOperator comparisonOperator6 = NotInCidrSet;
        ComparisonOperator comparisonOperator7 = InPortSet;
        ComparisonOperator comparisonOperator8 = NotInPortSet;
        ComparisonOperator comparisonOperator9 = InSet;
        ComparisonOperator comparisonOperator10 = NotInSet;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("less-than", comparisonOperator);
        map.put("less-than-equals", comparisonOperator2);
        map.put("greater-than", comparisonOperator3);
        map.put("greater-than-equals", comparisonOperator4);
        map.put("in-cidr-set", comparisonOperator5);
        map.put("not-in-cidr-set", comparisonOperator6);
        map.put("in-port-set", comparisonOperator7);
        map.put("not-in-port-set", comparisonOperator8);
        map.put("in-set", comparisonOperator9);
        map.put("not-in-set", comparisonOperator10);
    }

    ComparisonOperator(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static ComparisonOperator fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, ComparisonOperator> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
