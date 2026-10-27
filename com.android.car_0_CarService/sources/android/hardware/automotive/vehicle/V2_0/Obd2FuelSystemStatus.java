package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class Obd2FuelSystemStatus {
    public static final int CLOSED_LOOP = 2;
    public static final int CLOSED_LOOP_BUT_FEEDBACK_FAULT = 16;
    public static final int OPEN_ENGINE_LOAD_OR_DECELERATION = 4;
    public static final int OPEN_INSUFFICIENT_ENGINE_TEMPERATURE = 1;
    public static final int OPEN_SYSTEM_FAILURE = 8;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(1, "OPEN_INSUFFICIENT_ENGINE_TEMPERATURE");
        hashmap.put(2, "CLOSED_LOOP");
        hashmap.put(4, "OPEN_ENGINE_LOAD_OR_DECELERATION");
        hashmap.put(8, "OPEN_SYSTEM_FAILURE");
        hashmap.put(16, "CLOSED_LOOP_BUT_FEEDBACK_FAULT");
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
