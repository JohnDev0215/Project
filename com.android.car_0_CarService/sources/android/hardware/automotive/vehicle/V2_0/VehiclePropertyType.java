package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class VehiclePropertyType {
    public static final int BOOLEAN = 2097152;
    public static final int BYTES = 7340032;
    public static final int FLOAT = 6291456;
    public static final int FLOAT_VEC = 6356992;
    public static final int INT32 = 4194304;
    public static final int INT32_VEC = 4259840;
    public static final int INT64 = 5242880;
    public static final int INT64_VEC = 5308416;
    public static final int MASK = 16711680;
    public static final int MIXED = 14680064;
    public static final int STRING = 1048576;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(1048576, "STRING");
        hashmap.put(2097152, "BOOLEAN");
        hashmap.put(4194304, "INT32");
        hashmap.put(Integer.valueOf(INT32_VEC), "INT32_VEC");
        hashmap.put(Integer.valueOf(INT64), "INT64");
        hashmap.put(Integer.valueOf(INT64_VEC), "INT64_VEC");
        hashmap.put(Integer.valueOf(FLOAT), "FLOAT");
        hashmap.put(Integer.valueOf(FLOAT_VEC), "FLOAT_VEC");
        hashmap.put(Integer.valueOf(BYTES), "BYTES");
        hashmap.put(Integer.valueOf(MIXED), "MIXED");
        hashmap.put(Integer.valueOf(MASK), "MASK");
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
