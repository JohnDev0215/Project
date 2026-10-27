package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class VehicleHvacFanDirection {
    public static final int DEFROST = 4;
    public static final int DEFROST_AND_FLOOR = 6;
    public static final int FACE = 1;
    public static final int FACE_AND_FLOOR = 3;
    public static final int FLOOR = 2;
    public static final int UNKNOWN = 0;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "UNKNOWN");
        hashmap.put(1, "FACE");
        hashmap.put(2, "FLOOR");
        hashmap.put(3, "FACE_AND_FLOOR");
        hashmap.put(4, "DEFROST");
        hashmap.put(6, "DEFROST_AND_FLOOR");
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
