package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class Obd2CompressionIgnitionMonitors {
    public static final int BOOST_PRESSURE_AVAILABLE = 4096;
    public static final int BOOST_PRESSURE_INCOMPLETE = 8192;
    public static final int COMPONENTS_AVAILABLE = 1;
    public static final int COMPONENTS_INCOMPLETE = 2;
    public static final int EGR_OR_VVT_AVAILABLE = 64;
    public static final int EGR_OR_VVT_INCOMPLETE = 128;
    public static final int EXHAUST_GAS_SENSOR_AVAILABLE = 1024;
    public static final int EXHAUST_GAS_SENSOR_INCOMPLETE = 2048;
    public static final int FUEL_SYSTEM_AVAILABLE = 4;
    public static final int FUEL_SYSTEM_INCOMPLETE = 8;
    public static final int MISFIRE_AVAILABLE = 16;
    public static final int MISFIRE_INCOMPLETE = 32;
    public static final int NMHC_CATALYST_AVAILABLE = 65536;
    public static final int NMHC_CATALYST_INCOMPLETE = 131072;
    public static final int NOx_SCR_AVAILABLE = 16384;
    public static final int NOx_SCR_INCOMPLETE = 32768;
    public static final int PM_FILTER_AVAILABLE = 256;
    public static final int PM_FILTER_INCOMPLETE = 512;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(1, "COMPONENTS_AVAILABLE");
        hashmap.put(2, "COMPONENTS_INCOMPLETE");
        hashmap.put(4, "FUEL_SYSTEM_AVAILABLE");
        hashmap.put(8, "FUEL_SYSTEM_INCOMPLETE");
        hashmap.put(16, "MISFIRE_AVAILABLE");
        hashmap.put(32, "MISFIRE_INCOMPLETE");
        hashmap.put(64, "EGR_OR_VVT_AVAILABLE");
        hashmap.put(128, "EGR_OR_VVT_INCOMPLETE");
        hashmap.put(256, "PM_FILTER_AVAILABLE");
        hashmap.put(512, "PM_FILTER_INCOMPLETE");
        hashmap.put(1024, "EXHAUST_GAS_SENSOR_AVAILABLE");
        hashmap.put(2048, "EXHAUST_GAS_SENSOR_INCOMPLETE");
        hashmap.put(4096, "BOOST_PRESSURE_AVAILABLE");
        hashmap.put(8192, "BOOST_PRESSURE_INCOMPLETE");
        hashmap.put(16384, "NOx_SCR_AVAILABLE");
        hashmap.put(32768, "NOx_SCR_INCOMPLETE");
        hashmap.put(65536, "NMHC_CATALYST_AVAILABLE");
        hashmap.put(131072, "NMHC_CATALYST_INCOMPLETE");
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
