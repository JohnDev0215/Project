package android.hardware.usb.V1_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class PortRoleType {
    public static final int DATA_ROLE = 0;
    public static final int MODE = 2;
    public static final int POWER_ROLE = 1;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "DATA_ROLE");
        hashmap.put(1, "POWER_ROLE");
        hashmap.put(2, "MODE");
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
