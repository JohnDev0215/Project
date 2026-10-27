package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class FuelType {
    public static final int FUEL_TYPE_BIODIESEL = 5;
    public static final int FUEL_TYPE_CNG = 8;
    public static final int FUEL_TYPE_DIESEL_1 = 3;
    public static final int FUEL_TYPE_DIESEL_2 = 4;
    public static final int FUEL_TYPE_E85 = 6;
    public static final int FUEL_TYPE_ELECTRIC = 10;
    public static final int FUEL_TYPE_HYDROGEN = 11;
    public static final int FUEL_TYPE_LEADED = 2;
    public static final int FUEL_TYPE_LNG = 9;
    public static final int FUEL_TYPE_LPG = 7;
    public static final int FUEL_TYPE_OTHER = 12;
    public static final int FUEL_TYPE_UNKNOWN = 0;
    public static final int FUEL_TYPE_UNLEADED = 1;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "FUEL_TYPE_UNKNOWN");
        hashmap.put(1, "FUEL_TYPE_UNLEADED");
        hashmap.put(2, "FUEL_TYPE_LEADED");
        hashmap.put(3, "FUEL_TYPE_DIESEL_1");
        hashmap.put(4, "FUEL_TYPE_DIESEL_2");
        hashmap.put(5, "FUEL_TYPE_BIODIESEL");
        hashmap.put(6, "FUEL_TYPE_E85");
        hashmap.put(7, "FUEL_TYPE_LPG");
        hashmap.put(8, "FUEL_TYPE_CNG");
        hashmap.put(9, "FUEL_TYPE_LNG");
        hashmap.put(10, "FUEL_TYPE_ELECTRIC");
        hashmap.put(11, "FUEL_TYPE_HYDROGEN");
        hashmap.put(12, "FUEL_TYPE_OTHER");
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
