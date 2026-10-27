package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum DayOfWeek {
    SUN("SUN"),
    MON("MON"),
    TUE("TUE"),
    WED("WED"),
    THU("THU"),
    FRI("FRI"),
    SAT("SAT");

    private static final Map<String, DayOfWeek> enumMap;
    private String value;

    static {
        DayOfWeek dayOfWeek = SUN;
        DayOfWeek dayOfWeek2 = MON;
        DayOfWeek dayOfWeek3 = TUE;
        DayOfWeek dayOfWeek4 = WED;
        DayOfWeek dayOfWeek5 = THU;
        DayOfWeek dayOfWeek6 = FRI;
        DayOfWeek dayOfWeek7 = SAT;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("SUN", dayOfWeek);
        map.put("MON", dayOfWeek2);
        map.put("TUE", dayOfWeek3);
        map.put("WED", dayOfWeek4);
        map.put("THU", dayOfWeek5);
        map.put("FRI", dayOfWeek6);
        map.put("SAT", dayOfWeek7);
    }

    DayOfWeek(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static DayOfWeek fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, DayOfWeek> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
