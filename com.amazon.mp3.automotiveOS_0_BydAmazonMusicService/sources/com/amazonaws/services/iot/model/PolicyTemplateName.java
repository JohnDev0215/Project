package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum PolicyTemplateName {
    BLANK_POLICY("BLANK_POLICY");

    private static final Map<String, PolicyTemplateName> enumMap;
    private String value;

    static {
        PolicyTemplateName policyTemplateName = BLANK_POLICY;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("BLANK_POLICY", policyTemplateName);
    }

    PolicyTemplateName(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static PolicyTemplateName fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, PolicyTemplateName> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
