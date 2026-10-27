package com.amazonaws.services.iot.model;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum FleetMetricUnit {
    Seconds("Seconds"),
    Microseconds("Microseconds"),
    Milliseconds("Milliseconds"),
    Bytes("Bytes"),
    Kilobytes("Kilobytes"),
    Megabytes("Megabytes"),
    Gigabytes("Gigabytes"),
    Terabytes("Terabytes"),
    Bits("Bits"),
    Kilobits("Kilobits"),
    Megabits("Megabits"),
    Gigabits("Gigabits"),
    Terabits("Terabits"),
    Percent("Percent"),
    Count("Count"),
    BytesSecond("Bytes/Second"),
    KilobytesSecond("Kilobytes/Second"),
    MegabytesSecond("Megabytes/Second"),
    GigabytesSecond("Gigabytes/Second"),
    TerabytesSecond("Terabytes/Second"),
    BitsSecond("Bits/Second"),
    KilobitsSecond("Kilobits/Second"),
    MegabitsSecond("Megabits/Second"),
    GigabitsSecond("Gigabits/Second"),
    TerabitsSecond("Terabits/Second"),
    CountSecond("Count/Second"),
    None("None");

    private static final Map<String, FleetMetricUnit> enumMap;
    private String value;

    static {
        FleetMetricUnit fleetMetricUnit = Seconds;
        FleetMetricUnit fleetMetricUnit2 = Microseconds;
        FleetMetricUnit fleetMetricUnit3 = Milliseconds;
        FleetMetricUnit fleetMetricUnit4 = Bytes;
        FleetMetricUnit fleetMetricUnit5 = Kilobytes;
        FleetMetricUnit fleetMetricUnit6 = Megabytes;
        FleetMetricUnit fleetMetricUnit7 = Gigabytes;
        FleetMetricUnit fleetMetricUnit8 = Terabytes;
        FleetMetricUnit fleetMetricUnit9 = Bits;
        FleetMetricUnit fleetMetricUnit10 = Kilobits;
        FleetMetricUnit fleetMetricUnit11 = Megabits;
        FleetMetricUnit fleetMetricUnit12 = Gigabits;
        FleetMetricUnit fleetMetricUnit13 = Terabits;
        FleetMetricUnit fleetMetricUnit14 = Percent;
        FleetMetricUnit fleetMetricUnit15 = Count;
        FleetMetricUnit fleetMetricUnit16 = BytesSecond;
        FleetMetricUnit fleetMetricUnit17 = KilobytesSecond;
        FleetMetricUnit fleetMetricUnit18 = MegabytesSecond;
        FleetMetricUnit fleetMetricUnit19 = GigabytesSecond;
        FleetMetricUnit fleetMetricUnit20 = TerabytesSecond;
        FleetMetricUnit fleetMetricUnit21 = BitsSecond;
        FleetMetricUnit fleetMetricUnit22 = KilobitsSecond;
        FleetMetricUnit fleetMetricUnit23 = MegabitsSecond;
        FleetMetricUnit fleetMetricUnit24 = GigabitsSecond;
        FleetMetricUnit fleetMetricUnit25 = TerabitsSecond;
        FleetMetricUnit fleetMetricUnit26 = CountSecond;
        FleetMetricUnit fleetMetricUnit27 = None;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("Seconds", fleetMetricUnit);
        map.put("Microseconds", fleetMetricUnit2);
        map.put("Milliseconds", fleetMetricUnit3);
        map.put("Bytes", fleetMetricUnit4);
        map.put("Kilobytes", fleetMetricUnit5);
        map.put("Megabytes", fleetMetricUnit6);
        map.put("Gigabytes", fleetMetricUnit7);
        map.put("Terabytes", fleetMetricUnit8);
        map.put("Bits", fleetMetricUnit9);
        map.put("Kilobits", fleetMetricUnit10);
        map.put("Megabits", fleetMetricUnit11);
        map.put("Gigabits", fleetMetricUnit12);
        map.put("Terabits", fleetMetricUnit13);
        map.put("Percent", fleetMetricUnit14);
        map.put("Count", fleetMetricUnit15);
        map.put("Bytes/Second", fleetMetricUnit16);
        map.put("Kilobytes/Second", fleetMetricUnit17);
        map.put("Megabytes/Second", fleetMetricUnit18);
        map.put("Gigabytes/Second", fleetMetricUnit19);
        map.put("Terabytes/Second", fleetMetricUnit20);
        map.put("Bits/Second", fleetMetricUnit21);
        map.put("Kilobits/Second", fleetMetricUnit22);
        map.put("Megabits/Second", fleetMetricUnit23);
        map.put("Gigabits/Second", fleetMetricUnit24);
        map.put("Terabits/Second", fleetMetricUnit25);
        map.put("Count/Second", fleetMetricUnit26);
        map.put("None", fleetMetricUnit27);
    }

    FleetMetricUnit(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static FleetMetricUnit fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, FleetMetricUnit> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
