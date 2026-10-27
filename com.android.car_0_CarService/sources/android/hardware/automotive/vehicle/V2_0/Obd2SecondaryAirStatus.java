package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class Obd2SecondaryAirStatus {
    public static final int DOWNSTREAM_OF_CATALYCIC_CONVERTER = 2;
    public static final int FROM_OUTSIDE_OR_OFF = 4;
    public static final int PUMP_ON_FOR_DIAGNOSTICS = 8;
    public static final int UPSTREAM = 1;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(1, "UPSTREAM");
        hashmap.put(2, "DOWNSTREAM_OF_CATALYCIC_CONVERTER");
        hashmap.put(4, "FROM_OUTSIDE_OR_OFF");
        hashmap.put(8, "PUMP_ON_FOR_DIAGNOSTICS");
    }

    public static final String toString(int o) {
        if (hashmap.containsKey(Integer.valueOf(o))) {
            return hashmap.get(Integer.valueOf(o));
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if (hashmap.containsKey(Integer.valueOf(o))) {
            list.add(hashmap.get(Integer.valueOf(o)));
            flipped = 0 | o;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
