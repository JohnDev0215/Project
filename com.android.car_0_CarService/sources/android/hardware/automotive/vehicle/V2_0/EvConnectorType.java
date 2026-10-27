package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class EvConnectorType {
    public static final int GBT_AC = 10;
    public static final int GBT_DC = 11;
    public static final int IEC_TYPE_1_AC = 1;
    public static final int IEC_TYPE_1_CCS_DC = 5;
    public static final int IEC_TYPE_2_AC = 2;
    public static final int IEC_TYPE_2_CCS_DC = 6;
    public static final int IEC_TYPE_3_AC = 3;
    public static final int IEC_TYPE_4_DC = 4;
    public static final int OTHER = 101;
    public static final int TESLA_HPWC = 8;
    public static final int TESLA_ROADSTER = 7;
    public static final int TESLA_SUPERCHARGER = 9;
    public static final int UNKNOWN = 0;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "UNKNOWN");
        hashmap.put(1, "IEC_TYPE_1_AC");
        hashmap.put(2, "IEC_TYPE_2_AC");
        hashmap.put(3, "IEC_TYPE_3_AC");
        hashmap.put(4, "IEC_TYPE_4_DC");
        hashmap.put(5, "IEC_TYPE_1_CCS_DC");
        hashmap.put(6, "IEC_TYPE_2_CCS_DC");
        hashmap.put(7, "TESLA_ROADSTER");
        hashmap.put(8, "TESLA_HPWC");
        hashmap.put(9, "TESLA_SUPERCHARGER");
        hashmap.put(10, "GBT_AC");
        hashmap.put(11, "GBT_DC");
        hashmap.put(101, "OTHER");
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
