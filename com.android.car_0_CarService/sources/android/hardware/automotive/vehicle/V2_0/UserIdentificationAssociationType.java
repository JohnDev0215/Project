package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class UserIdentificationAssociationType {
    public static final int CUSTOM_1 = 101;
    public static final int CUSTOM_2 = 102;
    public static final int CUSTOM_3 = 103;
    public static final int CUSTOM_4 = 104;
    public static final int KEY_FOB = 1;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(1, "KEY_FOB");
        hashmap.put(101, "CUSTOM_1");
        hashmap.put(102, "CUSTOM_2");
        hashmap.put(103, "CUSTOM_3");
        hashmap.put(104, "CUSTOM_4");
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
