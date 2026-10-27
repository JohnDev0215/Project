package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class VehicleArea {
    public static final int DOOR = 100663296;
    public static final int GLOBAL = 16777216;
    public static final int MASK = 251658240;
    public static final int MIRROR = 67108864;
    public static final int SEAT = 83886080;
    public static final int WHEEL = 117440512;
    public static final int WINDOW = 50331648;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(16777216, "GLOBAL");
        hashmap.put(50331648, "WINDOW");
        hashmap.put(67108864, "MIRROR");
        hashmap.put(83886080, "SEAT");
        hashmap.put(100663296, "DOOR");
        hashmap.put(117440512, "WHEEL");
        hashmap.put(Integer.valueOf(MASK), "MASK");
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
