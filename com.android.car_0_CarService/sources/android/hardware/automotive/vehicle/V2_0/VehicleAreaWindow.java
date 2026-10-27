package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class VehicleAreaWindow {
    public static final int FRONT_WINDSHIELD = 1;
    public static final int REAR_WINDSHIELD = 2;
    public static final int ROOF_TOP_1 = 65536;
    public static final int ROOF_TOP_2 = 131072;
    public static final int ROW_1_LEFT = 16;
    public static final int ROW_1_RIGHT = 64;
    public static final int ROW_2_LEFT = 256;
    public static final int ROW_2_RIGHT = 1024;
    public static final int ROW_3_LEFT = 4096;
    public static final int ROW_3_RIGHT = 16384;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(1, "FRONT_WINDSHIELD");
        hashmap.put(2, "REAR_WINDSHIELD");
        hashmap.put(16, "ROW_1_LEFT");
        hashmap.put(64, "ROW_1_RIGHT");
        hashmap.put(256, "ROW_2_LEFT");
        hashmap.put(1024, "ROW_2_RIGHT");
        hashmap.put(4096, "ROW_3_LEFT");
        hashmap.put(16384, "ROW_3_RIGHT");
        hashmap.put(65536, "ROOF_TOP_1");
        hashmap.put(131072, "ROOF_TOP_2");
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
