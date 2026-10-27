package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class VehicleAreaSeat {
    public static final int ROW_1_CENTER = 2;
    public static final int ROW_1_LEFT = 1;
    public static final int ROW_1_RIGHT = 4;
    public static final int ROW_2_CENTER = 32;
    public static final int ROW_2_LEFT = 16;
    public static final int ROW_2_RIGHT = 64;
    public static final int ROW_3_CENTER = 512;
    public static final int ROW_3_LEFT = 256;
    public static final int ROW_3_RIGHT = 1024;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(1, "ROW_1_LEFT");
        hashmap.put(2, "ROW_1_CENTER");
        hashmap.put(4, "ROW_1_RIGHT");
        hashmap.put(16, "ROW_2_LEFT");
        hashmap.put(32, "ROW_2_CENTER");
        hashmap.put(64, "ROW_2_RIGHT");
        hashmap.put(256, "ROW_3_LEFT");
        hashmap.put(512, "ROW_3_CENTER");
        hashmap.put(1024, "ROW_3_RIGHT");
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
