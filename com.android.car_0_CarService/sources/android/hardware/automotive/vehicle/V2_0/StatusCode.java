package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class StatusCode {
    public static final int ACCESS_DENIED = 4;
    public static final int INTERNAL_ERROR = 5;
    public static final int INVALID_ARG = 2;
    public static final int NOT_AVAILABLE = 3;
    public static final int OK = 0;
    public static final int TRY_AGAIN = 1;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "OK");
        hashmap.put(1, "TRY_AGAIN");
        hashmap.put(2, "INVALID_ARG");
        hashmap.put(3, "NOT_AVAILABLE");
        hashmap.put(4, "ACCESS_DENIED");
        hashmap.put(5, "INTERNAL_ERROR");
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
