package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class UserIdentificationAssociationValue {
    public static final int ASSOCIATED_ANOTHER_USER = 3;
    public static final int ASSOCIATED_CURRENT_USER = 2;
    public static final int NOT_ASSOCIATED_ANY_USER = 4;
    public static final int UNKNOWN = 1;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(1, "UNKNOWN");
        hashmap.put(2, "ASSOCIATED_CURRENT_USER");
        hashmap.put(3, "ASSOCIATED_ANOTHER_USER");
        hashmap.put(4, "NOT_ASSOCIATED_ANY_USER");
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
