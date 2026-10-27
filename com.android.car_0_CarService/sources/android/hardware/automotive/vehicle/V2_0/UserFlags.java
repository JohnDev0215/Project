package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class UserFlags {
    public static final int ADMIN = 8;
    public static final int DISABLED = 16;
    public static final int EPHEMERAL = 4;
    public static final int GUEST = 2;
    public static final int NONE = 0;
    public static final int PROFILE = 32;
    public static final int SYSTEM = 1;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "NONE");
        hashmap.put(1, "SYSTEM");
        hashmap.put(2, "GUEST");
        hashmap.put(4, "EPHEMERAL");
        hashmap.put(8, "ADMIN");
        hashmap.put(16, "DISABLED");
        hashmap.put(32, "PROFILE");
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
