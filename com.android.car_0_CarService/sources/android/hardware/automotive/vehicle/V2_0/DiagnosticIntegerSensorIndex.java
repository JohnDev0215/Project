package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class DiagnosticIntegerSensorIndex {
    public static final int ABSOLUTE_BAROMETRIC_PRESSURE = 11;
    public static final int AMBIENT_AIR_TEMPERATURE = 13;
    public static final int COMMANDED_SECONDARY_AIR_STATUS = 5;
    public static final int CONTROL_MODULE_VOLTAGE = 12;
    public static final int DISTANCE_TRAVELED_SINCE_CODES_CLEARED = 10;
    public static final int DISTANCE_TRAVELED_WITH_MALFUNCTION_INDICATOR_LIGHT_ON = 8;
    public static final int DRIVER_DEMAND_PERCENT_TORQUE = 24;
    public static final int ENGINE_ACTUAL_PERCENT_TORQUE = 25;
    public static final int ENGINE_OIL_TEMPERATURE = 23;
    public static final int ENGINE_PERCENT_TORQUE_DATA_IDLE = 27;
    public static final int ENGINE_PERCENT_TORQUE_DATA_POINT1 = 28;
    public static final int ENGINE_PERCENT_TORQUE_DATA_POINT2 = 29;
    public static final int ENGINE_PERCENT_TORQUE_DATA_POINT3 = 30;
    public static final int ENGINE_PERCENT_TORQUE_DATA_POINT4 = 31;
    public static final int ENGINE_REFERENCE_PERCENT_TORQUE = 26;
    public static final int FUEL_RAIL_ABSOLUTE_PRESSURE = 22;
    public static final int FUEL_SYSTEM_STATUS = 0;
    public static final int FUEL_TYPE = 21;
    public static final int IGNITION_MONITORS_SUPPORTED = 2;
    public static final int IGNITION_SPECIFIC_MONITORS = 3;
    public static final int INTAKE_AIR_TEMPERATURE = 4;
    public static final int LAST_SYSTEM_INDEX = 31;
    public static final int MALFUNCTION_INDICATOR_LIGHT_ON = 1;
    public static final int MAX_AIR_FLOW_RATE_FROM_MASS_AIR_FLOW_SENSOR = 20;
    public static final int MAX_FUEL_AIR_EQUIVALENCE_RATIO = 16;
    public static final int MAX_INTAKE_MANIFOLD_ABSOLUTE_PRESSURE = 19;
    public static final int MAX_OXYGEN_SENSOR_CURRENT = 18;
    public static final int MAX_OXYGEN_SENSOR_VOLTAGE = 17;
    public static final int NUM_OXYGEN_SENSORS_PRESENT = 6;
    public static final int RUNTIME_SINCE_ENGINE_START = 7;
    public static final int TIME_SINCE_TROUBLE_CODES_CLEARED = 15;
    public static final int TIME_WITH_MALFUNCTION_LIGHT_ON = 14;
    public static final int WARMUPS_SINCE_CODES_CLEARED = 9;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "FUEL_SYSTEM_STATUS");
        hashmap.put(1, "MALFUNCTION_INDICATOR_LIGHT_ON");
        hashmap.put(2, "IGNITION_MONITORS_SUPPORTED");
        hashmap.put(3, "IGNITION_SPECIFIC_MONITORS");
        hashmap.put(4, "INTAKE_AIR_TEMPERATURE");
        hashmap.put(5, "COMMANDED_SECONDARY_AIR_STATUS");
        hashmap.put(6, "NUM_OXYGEN_SENSORS_PRESENT");
        hashmap.put(7, "RUNTIME_SINCE_ENGINE_START");
        hashmap.put(8, "DISTANCE_TRAVELED_WITH_MALFUNCTION_INDICATOR_LIGHT_ON");
        hashmap.put(9, "WARMUPS_SINCE_CODES_CLEARED");
        hashmap.put(10, "DISTANCE_TRAVELED_SINCE_CODES_CLEARED");
        hashmap.put(11, "ABSOLUTE_BAROMETRIC_PRESSURE");
        hashmap.put(12, "CONTROL_MODULE_VOLTAGE");
        hashmap.put(13, "AMBIENT_AIR_TEMPERATURE");
        hashmap.put(14, "TIME_WITH_MALFUNCTION_LIGHT_ON");
        hashmap.put(15, "TIME_SINCE_TROUBLE_CODES_CLEARED");
        hashmap.put(16, "MAX_FUEL_AIR_EQUIVALENCE_RATIO");
        hashmap.put(17, "MAX_OXYGEN_SENSOR_VOLTAGE");
        hashmap.put(18, "MAX_OXYGEN_SENSOR_CURRENT");
        hashmap.put(19, "MAX_INTAKE_MANIFOLD_ABSOLUTE_PRESSURE");
        hashmap.put(20, "MAX_AIR_FLOW_RATE_FROM_MASS_AIR_FLOW_SENSOR");
        hashmap.put(21, "FUEL_TYPE");
        hashmap.put(22, "FUEL_RAIL_ABSOLUTE_PRESSURE");
        hashmap.put(23, "ENGINE_OIL_TEMPERATURE");
        hashmap.put(24, "DRIVER_DEMAND_PERCENT_TORQUE");
        hashmap.put(25, "ENGINE_ACTUAL_PERCENT_TORQUE");
        hashmap.put(26, "ENGINE_REFERENCE_PERCENT_TORQUE");
        hashmap.put(27, "ENGINE_PERCENT_TORQUE_DATA_IDLE");
        hashmap.put(28, "ENGINE_PERCENT_TORQUE_DATA_POINT1");
        hashmap.put(29, "ENGINE_PERCENT_TORQUE_DATA_POINT2");
        hashmap.put(30, "ENGINE_PERCENT_TORQUE_DATA_POINT3");
        hashmap.put(31, "ENGINE_PERCENT_TORQUE_DATA_POINT4");
        hashmap.put(31, "LAST_SYSTEM_INDEX");
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
