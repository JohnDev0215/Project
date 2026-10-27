package vendor.rbxc.usbext.V1_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class Speed {
    public static final int FULL_SPEED = 1;
    public static final int HIGH_SPEED = 2;
    public static final int LOW_SPEED = 4;
    public static final int SUPER_SPEED = 3;
    public static final int UNKNOWN = 0;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(4, "LOW_SPEED");
        hashmap.put(1, "FULL_SPEED");
        hashmap.put(2, "HIGH_SPEED");
        hashmap.put(3, "SUPER_SPEED");
        hashmap.put(0, "UNKNOWN");
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
