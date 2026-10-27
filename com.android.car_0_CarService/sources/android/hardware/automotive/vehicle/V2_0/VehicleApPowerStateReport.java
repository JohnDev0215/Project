package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class VehicleApPowerStateReport {
    public static final int DEEP_SLEEP_ENTRY = 2;
    public static final int DEEP_SLEEP_EXIT = 3;
    public static final int ON = 6;
    public static final int SHUTDOWN_CANCELLED = 8;
    public static final int SHUTDOWN_POSTPONE = 4;
    public static final int SHUTDOWN_PREPARE = 7;
    public static final int SHUTDOWN_START = 5;
    public static final int WAIT_FOR_VHAL = 1;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(1, "WAIT_FOR_VHAL");
        hashmap.put(2, "DEEP_SLEEP_ENTRY");
        hashmap.put(3, "DEEP_SLEEP_EXIT");
        hashmap.put(4, "SHUTDOWN_POSTPONE");
        hashmap.put(5, "SHUTDOWN_START");
        hashmap.put(6, "ON");
        hashmap.put(7, "SHUTDOWN_PREPARE");
        hashmap.put(8, "SHUTDOWN_CANCELLED");
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
