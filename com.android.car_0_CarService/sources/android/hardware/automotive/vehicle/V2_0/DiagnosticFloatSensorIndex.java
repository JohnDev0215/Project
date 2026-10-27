package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class DiagnosticFloatSensorIndex {
    public static final int ABSOLUTE_EVAPORATION_SYSTEM_VAPOR_PRESSURE = 58;
    public static final int ABSOLUTE_LOAD_VALUE = 48;
    public static final int ABSOLUTE_THROTTLE_POSITION_B = 51;
    public static final int ABSOLUTE_THROTTLE_POSITION_C = 52;
    public static final int ACCELERATOR_PEDAL_POSITION_D = 53;
    public static final int ACCELERATOR_PEDAL_POSITION_E = 54;
    public static final int ACCELERATOR_PEDAL_POSITION_F = 55;
    public static final int CALCULATED_ENGINE_LOAD = 0;
    public static final int CATALYST_TEMPERATURE_BANK1_SENSOR1 = 44;
    public static final int CATALYST_TEMPERATURE_BANK1_SENSOR2 = 46;
    public static final int CATALYST_TEMPERATURE_BANK2_SENSOR1 = 45;
    public static final int CATALYST_TEMPERATURE_BANK2_SENSOR2 = 47;
    public static final int COMMANDED_EVAPORATIVE_PURGE = 41;
    public static final int COMMANDED_EXHAUST_GAS_RECIRCULATION = 39;
    public static final int COMMANDED_THROTTLE_ACTUATOR = 56;
    public static final int ENGINE_COOLANT_TEMPERATURE = 1;
    public static final int ENGINE_FUEL_RATE = 70;
    public static final int ENGINE_RPM = 8;
    public static final int ETHANOL_FUEL_PERCENTAGE = 57;
    public static final int EVAPORATION_SYSTEM_VAPOR_PRESSURE = 43;
    public static final int EXHAUST_GAS_RECIRCULATION_ERROR = 40;
    public static final int FUEL_AIR_COMMANDED_EQUIVALENCE_RATIO = 49;
    public static final int FUEL_INJECTION_TIMING = 69;
    public static final int FUEL_PRESSURE = 6;
    public static final int FUEL_RAIL_GAUGE_PRESSURE = 38;
    public static final int FUEL_RAIL_PRESSURE = 37;
    public static final int FUEL_TANK_LEVEL_INPUT = 42;
    public static final int HYBRID_BATTERY_PACK_REMAINING_LIFE = 68;
    public static final int INTAKE_MANIFOLD_ABSOLUTE_PRESSURE = 7;
    public static final int LAST_SYSTEM_INDEX = 70;
    public static final int LONG_TERM_FUEL_TRIM_BANK1 = 3;
    public static final int LONG_TERM_FUEL_TRIM_BANK2 = 5;
    public static final int LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK1 = 63;
    public static final int LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK2 = 64;
    public static final int LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK3 = 65;
    public static final int LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK4 = 66;
    public static final int MAF_AIR_FLOW_RATE = 11;
    public static final int OXYGEN_SENSOR1_FUEL_AIR_EQUIVALENCE_RATIO = 15;
    public static final int OXYGEN_SENSOR1_SHORT_TERM_FUEL_TRIM = 14;
    public static final int OXYGEN_SENSOR1_VOLTAGE = 13;
    public static final int OXYGEN_SENSOR2_FUEL_AIR_EQUIVALENCE_RATIO = 18;
    public static final int OXYGEN_SENSOR2_SHORT_TERM_FUEL_TRIM = 17;
    public static final int OXYGEN_SENSOR2_VOLTAGE = 16;
    public static final int OXYGEN_SENSOR3_FUEL_AIR_EQUIVALENCE_RATIO = 21;
    public static final int OXYGEN_SENSOR3_SHORT_TERM_FUEL_TRIM = 20;
    public static final int OXYGEN_SENSOR3_VOLTAGE = 19;
    public static final int OXYGEN_SENSOR4_FUEL_AIR_EQUIVALENCE_RATIO = 24;
    public static final int OXYGEN_SENSOR4_SHORT_TERM_FUEL_TRIM = 23;
    public static final int OXYGEN_SENSOR4_VOLTAGE = 22;
    public static final int OXYGEN_SENSOR5_FUEL_AIR_EQUIVALENCE_RATIO = 27;
    public static final int OXYGEN_SENSOR5_SHORT_TERM_FUEL_TRIM = 26;
    public static final int OXYGEN_SENSOR5_VOLTAGE = 25;
    public static final int OXYGEN_SENSOR6_FUEL_AIR_EQUIVALENCE_RATIO = 30;
    public static final int OXYGEN_SENSOR6_SHORT_TERM_FUEL_TRIM = 29;
    public static final int OXYGEN_SENSOR6_VOLTAGE = 28;
    public static final int OXYGEN_SENSOR7_FUEL_AIR_EQUIVALENCE_RATIO = 33;
    public static final int OXYGEN_SENSOR7_SHORT_TERM_FUEL_TRIM = 32;
    public static final int OXYGEN_SENSOR7_VOLTAGE = 31;
    public static final int OXYGEN_SENSOR8_FUEL_AIR_EQUIVALENCE_RATIO = 36;
    public static final int OXYGEN_SENSOR8_SHORT_TERM_FUEL_TRIM = 35;
    public static final int OXYGEN_SENSOR8_VOLTAGE = 34;
    public static final int RELATIVE_ACCELERATOR_PEDAL_POSITION = 67;
    public static final int RELATIVE_THROTTLE_POSITION = 50;
    public static final int SHORT_TERM_FUEL_TRIM_BANK1 = 2;
    public static final int SHORT_TERM_FUEL_TRIM_BANK2 = 4;
    public static final int SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK1 = 59;
    public static final int SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK2 = 60;
    public static final int SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK3 = 61;
    public static final int SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK4 = 62;
    public static final int THROTTLE_POSITION = 12;
    public static final int TIMING_ADVANCE = 10;
    public static final int VEHICLE_SPEED = 9;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "CALCULATED_ENGINE_LOAD");
        hashmap.put(1, "ENGINE_COOLANT_TEMPERATURE");
        hashmap.put(2, "SHORT_TERM_FUEL_TRIM_BANK1");
        hashmap.put(3, "LONG_TERM_FUEL_TRIM_BANK1");
        hashmap.put(4, "SHORT_TERM_FUEL_TRIM_BANK2");
        hashmap.put(5, "LONG_TERM_FUEL_TRIM_BANK2");
        hashmap.put(6, "FUEL_PRESSURE");
        hashmap.put(7, "INTAKE_MANIFOLD_ABSOLUTE_PRESSURE");
        hashmap.put(8, "ENGINE_RPM");
        hashmap.put(9, "VEHICLE_SPEED");
        hashmap.put(10, "TIMING_ADVANCE");
        hashmap.put(11, "MAF_AIR_FLOW_RATE");
        hashmap.put(12, "THROTTLE_POSITION");
        hashmap.put(13, "OXYGEN_SENSOR1_VOLTAGE");
        hashmap.put(14, "OXYGEN_SENSOR1_SHORT_TERM_FUEL_TRIM");
        hashmap.put(15, "OXYGEN_SENSOR1_FUEL_AIR_EQUIVALENCE_RATIO");
        hashmap.put(16, "OXYGEN_SENSOR2_VOLTAGE");
        hashmap.put(17, "OXYGEN_SENSOR2_SHORT_TERM_FUEL_TRIM");
        hashmap.put(18, "OXYGEN_SENSOR2_FUEL_AIR_EQUIVALENCE_RATIO");
        hashmap.put(19, "OXYGEN_SENSOR3_VOLTAGE");
        hashmap.put(20, "OXYGEN_SENSOR3_SHORT_TERM_FUEL_TRIM");
        hashmap.put(21, "OXYGEN_SENSOR3_FUEL_AIR_EQUIVALENCE_RATIO");
        hashmap.put(22, "OXYGEN_SENSOR4_VOLTAGE");
        hashmap.put(23, "OXYGEN_SENSOR4_SHORT_TERM_FUEL_TRIM");
        hashmap.put(24, "OXYGEN_SENSOR4_FUEL_AIR_EQUIVALENCE_RATIO");
        hashmap.put(25, "OXYGEN_SENSOR5_VOLTAGE");
        hashmap.put(26, "OXYGEN_SENSOR5_SHORT_TERM_FUEL_TRIM");
        hashmap.put(27, "OXYGEN_SENSOR5_FUEL_AIR_EQUIVALENCE_RATIO");
        hashmap.put(28, "OXYGEN_SENSOR6_VOLTAGE");
        hashmap.put(29, "OXYGEN_SENSOR6_SHORT_TERM_FUEL_TRIM");
        hashmap.put(30, "OXYGEN_SENSOR6_FUEL_AIR_EQUIVALENCE_RATIO");
        hashmap.put(31, "OXYGEN_SENSOR7_VOLTAGE");
        hashmap.put(32, "OXYGEN_SENSOR7_SHORT_TERM_FUEL_TRIM");
        hashmap.put(33, "OXYGEN_SENSOR7_FUEL_AIR_EQUIVALENCE_RATIO");
        hashmap.put(34, "OXYGEN_SENSOR8_VOLTAGE");
        hashmap.put(35, "OXYGEN_SENSOR8_SHORT_TERM_FUEL_TRIM");
        hashmap.put(36, "OXYGEN_SENSOR8_FUEL_AIR_EQUIVALENCE_RATIO");
        hashmap.put(37, "FUEL_RAIL_PRESSURE");
        hashmap.put(38, "FUEL_RAIL_GAUGE_PRESSURE");
        hashmap.put(39, "COMMANDED_EXHAUST_GAS_RECIRCULATION");
        hashmap.put(40, "EXHAUST_GAS_RECIRCULATION_ERROR");
        hashmap.put(41, "COMMANDED_EVAPORATIVE_PURGE");
        hashmap.put(42, "FUEL_TANK_LEVEL_INPUT");
        hashmap.put(43, "EVAPORATION_SYSTEM_VAPOR_PRESSURE");
        hashmap.put(44, "CATALYST_TEMPERATURE_BANK1_SENSOR1");
        hashmap.put(45, "CATALYST_TEMPERATURE_BANK2_SENSOR1");
        hashmap.put(46, "CATALYST_TEMPERATURE_BANK1_SENSOR2");
        hashmap.put(47, "CATALYST_TEMPERATURE_BANK2_SENSOR2");
        hashmap.put(48, "ABSOLUTE_LOAD_VALUE");
        hashmap.put(49, "FUEL_AIR_COMMANDED_EQUIVALENCE_RATIO");
        hashmap.put(50, "RELATIVE_THROTTLE_POSITION");
        hashmap.put(51, "ABSOLUTE_THROTTLE_POSITION_B");
        hashmap.put(52, "ABSOLUTE_THROTTLE_POSITION_C");
        hashmap.put(53, "ACCELERATOR_PEDAL_POSITION_D");
        hashmap.put(54, "ACCELERATOR_PEDAL_POSITION_E");
        hashmap.put(55, "ACCELERATOR_PEDAL_POSITION_F");
        hashmap.put(56, "COMMANDED_THROTTLE_ACTUATOR");
        hashmap.put(57, "ETHANOL_FUEL_PERCENTAGE");
        hashmap.put(58, "ABSOLUTE_EVAPORATION_SYSTEM_VAPOR_PRESSURE");
        hashmap.put(59, "SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK1");
        hashmap.put(60, "SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK2");
        hashmap.put(61, "SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK3");
        hashmap.put(62, "SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK4");
        hashmap.put(63, "LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK1");
        hashmap.put(64, "LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK2");
        hashmap.put(65, "LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK3");
        hashmap.put(66, "LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK4");
        hashmap.put(67, "RELATIVE_ACCELERATOR_PEDAL_POSITION");
        hashmap.put(68, "HYBRID_BATTERY_PACK_REMAINING_LIFE");
        hashmap.put(69, "FUEL_INJECTION_TIMING");
        hashmap.put(70, "ENGINE_FUEL_RATE");
        hashmap.put(70, "LAST_SYSTEM_INDEX");
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
