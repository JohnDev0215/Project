package android.hardware.audio.common.V6_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class AudioInputFlag {
    public static final int DIRECT = 128;
    public static final int FAST = 1;
    public static final int HW_AV_SYNC = 64;
    public static final int HW_HOTWORD = 2;
    public static final int MMAP_NOIRQ = 16;
    public static final int NONE = 0;
    public static final int RAW = 4;
    public static final int SYNC = 8;
    public static final int VOIP_TX = 32;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "NONE");
        hashmap.put(1, "FAST");
        hashmap.put(2, "HW_HOTWORD");
        hashmap.put(4, "RAW");
        hashmap.put(8, "SYNC");
        hashmap.put(16, "MMAP_NOIRQ");
        hashmap.put(32, "VOIP_TX");
        hashmap.put(64, "HW_AV_SYNC");
        hashmap.put(128, "DIRECT");
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
