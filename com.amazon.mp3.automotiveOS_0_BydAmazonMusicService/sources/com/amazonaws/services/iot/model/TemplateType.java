package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum TemplateType {
    FLEET_PROVISIONING("FLEET_PROVISIONING"),
    JITP("JITP");

    private static final Map<String, TemplateType> enumMap;
    private String value;

    static {
        TemplateType templateType = FLEET_PROVISIONING;
        TemplateType templateType2 = JITP;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("FLEET_PROVISIONING", templateType);
        map.put("JITP", templateType2);
    }

    TemplateType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static TemplateType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, TemplateType> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
