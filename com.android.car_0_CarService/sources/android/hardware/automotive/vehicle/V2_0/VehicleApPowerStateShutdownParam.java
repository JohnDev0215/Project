package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class VehicleApPowerStateShutdownParam {
    public static final int CAN_SLEEP = 2;
    public static final int SHUTDOWN_IMMEDIATELY = 1;
    public static final int SHUTDOWN_ONLY = 3;
    public static final int SLEEP_IMMEDIATELY = 4;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(1, "SHUTDOWN_IMMEDIATELY");
        hashmap.put(2, "CAN_SLEEP");
        hashmap.put(3, "SHUTDOWN_ONLY");
        hashmap.put(4, "SLEEP_IMMEDIATELY");
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
