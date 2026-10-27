package vendor.bosch.tee.V1_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class BoschAsymKeyFormat {
    public static final int DER = 1;
    public static final int PEM = 0;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "PEM");
        hashmap.put(1, "DER");
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
