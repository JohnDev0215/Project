package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class Obd2SparkIgnitionMonitors {
    public static final int AC_REFRIGERANT_AVAILABLE = 4096;
    public static final int AC_REFRIGERANT_INCOMPLETE = 8192;
    public static final int CATALYST_AVAILABLE = 1048576;
    public static final int CATALYST_INCOMPLETE = 2097152;
    public static final int COMPONENTS_AVAILABLE = 1;
    public static final int COMPONENTS_INCOMPLETE = 2;
    public static final int EGR_AVAILABLE = 64;
    public static final int EGR_INCOMPLETE = 128;
    public static final int EVAPORATIVE_SYSTEM_AVAILABLE = 65536;
    public static final int EVAPORATIVE_SYSTEM_INCOMPLETE = 131072;
    public static final int FUEL_SYSTEM_AVAILABLE = 4;
    public static final int FUEL_SYSTEM_INCOMPLETE = 8;
    public static final int HEATED_CATALYST_AVAILABLE = 262144;
    public static final int HEATED_CATALYST_INCOMPLETE = 524288;
    public static final int MISFIRE_AVAILABLE = 16;
    public static final int MISFIRE_INCOMPLETE = 32;
    public static final int OXYGEN_SENSOR_AVAILABLE = 1024;
    public static final int OXYGEN_SENSOR_HEATER_AVAILABLE = 256;
    public static final int OXYGEN_SENSOR_HEATER_INCOMPLETE = 512;
    public static final int OXYGEN_SENSOR_INCOMPLETE = 2048;
    public static final int SECONDARY_AIR_SYSTEM_AVAILABLE = 16384;
    public static final int SECONDARY_AIR_SYSTEM_INCOMPLETE = 32768;
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
        hashmap.put(64, "EGR_AVAILABLE");
        hashmap.put(128, "EGR_INCOMPLETE");
        hashmap.put(256, "OXYGEN_SENSOR_HEATER_AVAILABLE");
        hashmap.put(512, "OXYGEN_SENSOR_HEATER_INCOMPLETE");
        hashmap.put(1024, "OXYGEN_SENSOR_AVAILABLE");
        hashmap.put(2048, "OXYGEN_SENSOR_INCOMPLETE");
        hashmap.put(4096, "AC_REFRIGERANT_AVAILABLE");
        hashmap.put(8192, "AC_REFRIGERANT_INCOMPLETE");
        hashmap.put(16384, "SECONDARY_AIR_SYSTEM_AVAILABLE");
        hashmap.put(32768, "SECONDARY_AIR_SYSTEM_INCOMPLETE");
        hashmap.put(65536, "EVAPORATIVE_SYSTEM_AVAILABLE");
        hashmap.put(131072, "EVAPORATIVE_SYSTEM_INCOMPLETE");
        hashmap.put(262144, "HEATED_CATALYST_AVAILABLE");
        hashmap.put(524288, "HEATED_CATALYST_INCOMPLETE");
        hashmap.put(1048576, "CATALYST_AVAILABLE");
        hashmap.put(2097152, "CATALYST_INCOMPLETE");
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
