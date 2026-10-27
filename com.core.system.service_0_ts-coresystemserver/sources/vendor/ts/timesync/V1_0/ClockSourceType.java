package vendor.ts.timesync.V1_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ClockSourceType {
    public static final int CLOCK_SOURCE_TYPE_DEFAULT = 0;
    public static final int CLOCK_SOURCE_TYPE_EXT_NETWORK = 3;
    public static final int CLOCK_SOURCE_TYPE_FORCE = 1;
    public static final int CLOCK_SOURCE_TYPE_LOCATION = 4;
    public static final int CLOCK_SOURCE_TYPE_MCU = 6;
    public static final int CLOCK_SOURCE_TYPE_MCU_RESYNC = 8;
    public static final int CLOCK_SOURCE_TYPE_ORIG_NETWORK = 2;
    public static final int CLOCK_SOURCE_TYPE_QNX = 7;
    public static final int CLOCK_SOURCE_TYPE_RDS = 5;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "CLOCK_SOURCE_TYPE_DEFAULT");
        hashmap.put(1, "CLOCK_SOURCE_TYPE_FORCE");
        hashmap.put(2, "CLOCK_SOURCE_TYPE_ORIG_NETWORK");
        hashmap.put(3, "CLOCK_SOURCE_TYPE_EXT_NETWORK");
        hashmap.put(4, "CLOCK_SOURCE_TYPE_LOCATION");
        hashmap.put(5, "CLOCK_SOURCE_TYPE_RDS");
        hashmap.put(6, "CLOCK_SOURCE_TYPE_MCU");
        hashmap.put(7, "CLOCK_SOURCE_TYPE_QNX");
        hashmap.put(8, "CLOCK_SOURCE_TYPE_MCU_RESYNC");
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
