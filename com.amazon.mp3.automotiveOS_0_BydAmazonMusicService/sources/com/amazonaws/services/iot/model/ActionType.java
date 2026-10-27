package com.amazonaws.services.iot.model;

import com.google.firebase.perf.FirebasePerformance;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum ActionType {
    PUBLISH("PUBLISH"),
    SUBSCRIBE("SUBSCRIBE"),
    RECEIVE("RECEIVE"),
    CONNECT(FirebasePerformance.HttpMethod.CONNECT);

    private static final Map<String, ActionType> enumMap;
    private String value;

    static {
        ActionType actionType = PUBLISH;
        ActionType actionType2 = SUBSCRIBE;
        ActionType actionType3 = RECEIVE;
        ActionType actionType4 = CONNECT;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("PUBLISH", actionType);
        map.put("SUBSCRIBE", actionType2);
        map.put("RECEIVE", actionType3);
        map.put(FirebasePerformance.HttpMethod.CONNECT, actionType4);
    }

    ActionType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static ActionType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, ActionType> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
