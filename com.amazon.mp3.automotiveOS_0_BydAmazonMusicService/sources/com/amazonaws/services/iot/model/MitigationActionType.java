package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum MitigationActionType {
    UPDATE_DEVICE_CERTIFICATE("UPDATE_DEVICE_CERTIFICATE"),
    UPDATE_CA_CERTIFICATE("UPDATE_CA_CERTIFICATE"),
    ADD_THINGS_TO_THING_GROUP("ADD_THINGS_TO_THING_GROUP"),
    REPLACE_DEFAULT_POLICY_VERSION("REPLACE_DEFAULT_POLICY_VERSION"),
    ENABLE_IOT_LOGGING("ENABLE_IOT_LOGGING"),
    PUBLISH_FINDING_TO_SNS("PUBLISH_FINDING_TO_SNS");

    private static final Map<String, MitigationActionType> enumMap;
    private String value;

    static {
        MitigationActionType mitigationActionType = UPDATE_DEVICE_CERTIFICATE;
        MitigationActionType mitigationActionType2 = UPDATE_CA_CERTIFICATE;
        MitigationActionType mitigationActionType3 = ADD_THINGS_TO_THING_GROUP;
        MitigationActionType mitigationActionType4 = REPLACE_DEFAULT_POLICY_VERSION;
        MitigationActionType mitigationActionType5 = ENABLE_IOT_LOGGING;
        MitigationActionType mitigationActionType6 = PUBLISH_FINDING_TO_SNS;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("UPDATE_DEVICE_CERTIFICATE", mitigationActionType);
        map.put("UPDATE_CA_CERTIFICATE", mitigationActionType2);
        map.put("ADD_THINGS_TO_THING_GROUP", mitigationActionType3);
        map.put("REPLACE_DEFAULT_POLICY_VERSION", mitigationActionType4);
        map.put("ENABLE_IOT_LOGGING", mitigationActionType5);
        map.put("PUBLISH_FINDING_TO_SNS", mitigationActionType6);
    }

    MitigationActionType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static MitigationActionType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, MitigationActionType> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
