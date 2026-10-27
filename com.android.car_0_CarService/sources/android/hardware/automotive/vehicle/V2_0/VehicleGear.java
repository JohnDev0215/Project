package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class VehicleGear {
    public static final int GEAR_1 = 16;
    public static final int GEAR_2 = 32;
    public static final int GEAR_3 = 64;
    public static final int GEAR_4 = 128;
    public static final int GEAR_5 = 256;
    public static final int GEAR_6 = 512;
    public static final int GEAR_7 = 1024;
    public static final int GEAR_8 = 2048;
    public static final int GEAR_9 = 4096;
    public static final int GEAR_DRIVE = 8;
    public static final int GEAR_NEUTRAL = 1;
    public static final int GEAR_PARK = 4;
    public static final int GEAR_REVERSE = 2;
    public static final int GEAR_UNKNOWN = 0;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "GEAR_UNKNOWN");
        hashmap.put(1, "GEAR_NEUTRAL");
        hashmap.put(2, "GEAR_REVERSE");
        hashmap.put(4, "GEAR_PARK");
        hashmap.put(8, "GEAR_DRIVE");
        hashmap.put(16, "GEAR_1");
        hashmap.put(32, "GEAR_2");
        hashmap.put(64, "GEAR_3");
        hashmap.put(128, "GEAR_4");
        hashmap.put(256, "GEAR_5");
        hashmap.put(512, "GEAR_6");
        hashmap.put(1024, "GEAR_7");
        hashmap.put(2048, "GEAR_8");
        hashmap.put(4096, "GEAR_9");
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
