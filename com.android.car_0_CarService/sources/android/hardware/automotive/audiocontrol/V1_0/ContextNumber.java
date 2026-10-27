package android.hardware.automotive.audiocontrol.V1_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ContextNumber {
    public static final int ALARM = 6;
    public static final int CALL = 5;
    public static final int CALL_RING = 4;
    public static final int INVALID = 0;
    public static final int MUSIC = 1;
    public static final int NAVIGATION = 2;
    public static final int NOTIFICATION = 7;
    public static final int SYSTEM_SOUND = 8;
    public static final int VOICE_COMMAND = 3;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "INVALID");
        hashmap.put(1, "MUSIC");
        hashmap.put(2, "NAVIGATION");
        hashmap.put(3, "VOICE_COMMAND");
        hashmap.put(4, "CALL_RING");
        hashmap.put(5, "CALL");
        hashmap.put(6, "ALARM");
        hashmap.put(7, "NOTIFICATION");
        hashmap.put(8, "SYSTEM_SOUND");
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
