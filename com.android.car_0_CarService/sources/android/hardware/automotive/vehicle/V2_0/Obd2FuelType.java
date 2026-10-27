package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class Obd2FuelType {
    public static final int BIFUEL_RUNNING_CNG = 13;
    public static final int BIFUEL_RUNNING_DIESEL = 23;
    public static final int BIFUEL_RUNNING_ELECTRIC = 15;
    public static final int BIFUEL_RUNNING_ELECTRIC_AND_COMBUSTION = 16;
    public static final int BIFUEL_RUNNING_ETHANOL = 11;
    public static final int BIFUEL_RUNNING_GASOLINE = 9;
    public static final int BIFUEL_RUNNING_LPG = 12;
    public static final int BIFUEL_RUNNING_METHANOL = 10;
    public static final int BIFUEL_RUNNING_PROPANE = 14;
    public static final int CNG = 6;
    public static final int DIESEL = 4;
    public static final int ELECTRIC = 8;
    public static final int ETHANOL = 3;
    public static final int GASOLINE = 1;
    public static final int HYBRID_DIESEL = 19;
    public static final int HYBRID_ELECTRIC = 20;
    public static final int HYBRID_ETHANOL = 18;
    public static final int HYBRID_GASOLINE = 17;
    public static final int HYBRID_REGENERATIVE = 22;
    public static final int HYBRID_RUNNING_ELECTRIC_AND_COMBUSTION = 21;
    public static final int LPG = 5;
    public static final int METHANOL = 2;
    public static final int NOT_AVAILABLE = 0;
    public static final int PROPANE = 7;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "NOT_AVAILABLE");
        hashmap.put(1, "GASOLINE");
        hashmap.put(2, "METHANOL");
        hashmap.put(3, "ETHANOL");
        hashmap.put(4, "DIESEL");
        hashmap.put(5, "LPG");
        hashmap.put(6, "CNG");
        hashmap.put(7, "PROPANE");
        hashmap.put(8, "ELECTRIC");
        hashmap.put(9, "BIFUEL_RUNNING_GASOLINE");
        hashmap.put(10, "BIFUEL_RUNNING_METHANOL");
        hashmap.put(11, "BIFUEL_RUNNING_ETHANOL");
        hashmap.put(12, "BIFUEL_RUNNING_LPG");
        hashmap.put(13, "BIFUEL_RUNNING_CNG");
        hashmap.put(14, "BIFUEL_RUNNING_PROPANE");
        hashmap.put(15, "BIFUEL_RUNNING_ELECTRIC");
        hashmap.put(16, "BIFUEL_RUNNING_ELECTRIC_AND_COMBUSTION");
        hashmap.put(17, "HYBRID_GASOLINE");
        hashmap.put(18, "HYBRID_ETHANOL");
        hashmap.put(19, "HYBRID_DIESEL");
        hashmap.put(20, "HYBRID_ELECTRIC");
        hashmap.put(21, "HYBRID_RUNNING_ELECTRIC_AND_COMBUSTION");
        hashmap.put(22, "HYBRID_REGENERATIVE");
        hashmap.put(23, "BIFUEL_RUNNING_DIESEL");
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
