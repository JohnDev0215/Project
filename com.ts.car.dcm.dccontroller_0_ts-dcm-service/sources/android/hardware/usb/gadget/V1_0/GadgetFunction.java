package android.hardware.usb.gadget.V1_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class GadgetFunction {
    public static final long ACCESSORY = 2;
    public static final long ADB = 1;
    public static final long AUDIO_SOURCE = 64;
    public static final long MIDI = 8;
    public static final long MTP = 4;
    public static final long NONE = 0;
    public static final long PTP = 16;
    public static final long RNDIS = 32;
    public static final HashMap<Long, String> hashmap;

    static {
        HashMap<Long, String> map = new HashMap<>();
        hashmap = map;
        map.put(0L, "NONE");
        hashmap.put(1L, "ADB");
        hashmap.put(2L, "ACCESSORY");
        hashmap.put(4L, "MTP");
        hashmap.put(8L, "MIDI");
        hashmap.put(16L, "PTP");
        hashmap.put(32L, "RNDIS");
        hashmap.put(64L, "AUDIO_SOURCE");
    }

    public static final String toString(long o) {
        if (hashmap.containsKey(Long.valueOf(o))) {
            return hashmap.get(Long.valueOf(o));
        }
        return "0x" + Long.toHexString(o);
    }

    public static final String dumpBitfield(long o) {
        ArrayList<String> list = new ArrayList<>();
        long flipped = 0;
        if (hashmap.containsKey(Long.valueOf(o))) {
            list.add(hashmap.get(Long.valueOf(o)));
            flipped = 0 | o;
        }
        if (o != flipped) {
            list.add("0x" + Long.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
