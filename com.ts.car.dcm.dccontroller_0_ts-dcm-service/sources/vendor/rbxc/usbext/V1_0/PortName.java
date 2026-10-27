package vendor.rbxc.usbext.V1_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class PortName {
    public static final int USB2_0 = 0;
    public static final int USB3_0 = 1;
    public static final int USB3_0_1 = 2;
    public static final int USB3_0_2 = 3;
    public static final int USB3_0_3 = 4;
    public static final int USB3_0_4 = 5;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "USB2_0");
        hashmap.put(1, "USB3_0");
        hashmap.put(2, "USB3_0_1");
        hashmap.put(3, "USB3_0_2");
        hashmap.put(4, "USB3_0_3");
        hashmap.put(5, "USB3_0_4");
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
