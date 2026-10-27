package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class SwitchUserMessageType {
    public static final int ANDROID_POST_SWITCH = 5;
    public static final int ANDROID_SWITCH = 2;
    public static final int LEGACY_ANDROID_SWITCH = 1;
    public static final int VEHICLE_REQUEST = 4;
    public static final int VEHICLE_RESPONSE = 3;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(1, "LEGACY_ANDROID_SWITCH");
        hashmap.put(2, "ANDROID_SWITCH");
        hashmap.put(3, "VEHICLE_RESPONSE");
        hashmap.put(4, "VEHICLE_REQUEST");
        hashmap.put(5, "ANDROID_POST_SWITCH");
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
