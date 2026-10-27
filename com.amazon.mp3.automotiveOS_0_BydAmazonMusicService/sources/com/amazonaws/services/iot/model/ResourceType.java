package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum ResourceType {
    DEVICE_CERTIFICATE("DEVICE_CERTIFICATE"),
    CA_CERTIFICATE("CA_CERTIFICATE"),
    IOT_POLICY("IOT_POLICY"),
    COGNITO_IDENTITY_POOL("COGNITO_IDENTITY_POOL"),
    CLIENT_ID("CLIENT_ID"),
    ACCOUNT_SETTINGS("ACCOUNT_SETTINGS"),
    ROLE_ALIAS("ROLE_ALIAS"),
    IAM_ROLE("IAM_ROLE"),
    ISSUER_CERTIFICATE("ISSUER_CERTIFICATE");

    private static final Map<String, ResourceType> enumMap;
    private String value;

    static {
        ResourceType resourceType = DEVICE_CERTIFICATE;
        ResourceType resourceType2 = CA_CERTIFICATE;
        ResourceType resourceType3 = IOT_POLICY;
        ResourceType resourceType4 = COGNITO_IDENTITY_POOL;
        ResourceType resourceType5 = CLIENT_ID;
        ResourceType resourceType6 = ACCOUNT_SETTINGS;
        ResourceType resourceType7 = ROLE_ALIAS;
        ResourceType resourceType8 = IAM_ROLE;
        ResourceType resourceType9 = ISSUER_CERTIFICATE;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("DEVICE_CERTIFICATE", resourceType);
        map.put("CA_CERTIFICATE", resourceType2);
        map.put("IOT_POLICY", resourceType3);
        map.put("COGNITO_IDENTITY_POOL", resourceType4);
        map.put("CLIENT_ID", resourceType5);
        map.put("ACCOUNT_SETTINGS", resourceType6);
        map.put("ROLE_ALIAS", resourceType7);
        map.put("IAM_ROLE", resourceType8);
        map.put("ISSUER_CERTIFICATE", resourceType9);
    }

    ResourceType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static ResourceType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, ResourceType> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
