package android.hardware.automotive.audiocontrol.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class AudioFocusChange {
    public static final int GAIN = 1;
    public static final int GAIN_TRANSIENT = 2;
    public static final int GAIN_TRANSIENT_EXCLUSIVE = 4;
    public static final int GAIN_TRANSIENT_MAY_DUCK = 3;
    public static final int LOSS = -1;
    public static final int LOSS_TRANSIENT = -2;
    public static final int LOSS_TRANSIENT_CAN_DUCK = -3;
    public static final int NONE = 0;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "NONE");
        hashmap.put(1, "GAIN");
        hashmap.put(2, "GAIN_TRANSIENT");
        hashmap.put(3, "GAIN_TRANSIENT_MAY_DUCK");
        hashmap.put(4, "GAIN_TRANSIENT_EXCLUSIVE");
        hashmap.put(-1, "LOSS");
        hashmap.put(-2, "LOSS_TRANSIENT");
        hashmap.put(-3, "LOSS_TRANSIENT_CAN_DUCK");
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
