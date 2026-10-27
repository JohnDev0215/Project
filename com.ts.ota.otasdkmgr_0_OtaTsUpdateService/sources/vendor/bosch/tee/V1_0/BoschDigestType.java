package vendor.bosch.tee.V1_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class BoschDigestType {
    public static final int MD5 = 0;
    public static final int SHA1 = 1;
    public static final int SHA256 = 2;
    public static final int SHA384 = 3;
    public static final int SHA512 = 4;
    public static final int SM3 = 5;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "MD5");
        hashmap.put(1, "SHA1");
        hashmap.put(2, "SHA256");
        hashmap.put(3, "SHA384");
        hashmap.put(4, "SHA512");
        hashmap.put(5, "SM3");
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
