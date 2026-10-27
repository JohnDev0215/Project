package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class VmsSubscriptionsStateIntegerValuesIndex {
    public static final int MESSAGE_TYPE = 0;
    public static final int NUMBER_OF_ASSOCIATED_LAYERS = 3;
    public static final int NUMBER_OF_LAYERS = 2;
    public static final int SEQUENCE_NUMBER = 1;
    public static final int SUBSCRIPTIONS_START = 4;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "MESSAGE_TYPE");
        hashmap.put(1, "SEQUENCE_NUMBER");
        hashmap.put(2, "NUMBER_OF_LAYERS");
        hashmap.put(3, "NUMBER_OF_ASSOCIATED_LAYERS");
        hashmap.put(4, "SUBSCRIPTIONS_START");
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
