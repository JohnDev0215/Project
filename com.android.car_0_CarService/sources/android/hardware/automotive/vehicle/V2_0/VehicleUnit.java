package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class VehicleUnit {
    public static final int AMPERE_HOURS = 100;
    public static final int BAR = 114;
    public static final int CELSIUS = 48;
    public static final int DEGREES = 128;
    public static final int FAHRENHEIT = 49;
    public static final int GALLON = 66;
    public static final int HERTZ = 3;
    public static final int IMPERIAL_GALLON = 67;
    public static final int KELVIN = 50;
    public static final int KILOMETER = 35;
    public static final int KILOMETERS_PER_HOUR = 145;
    public static final int KILOPASCAL = 112;
    public static final int KILOWATT_HOUR = 101;
    public static final int LITER = 65;
    public static final int METER = 33;
    public static final int METER_PER_SEC = 1;
    public static final int MILE = 36;
    public static final int MILES_PER_HOUR = 144;
    public static final int MILLIAMPERE = 97;
    public static final int MILLILITER = 64;
    public static final int MILLIMETER = 32;
    public static final int MILLIVOLT = 98;
    public static final int MILLIWATTS = 99;
    public static final int NANO_SECS = 80;
    public static final int PERCENTILE = 16;
    public static final int PSI = 113;
    public static final int RPM = 2;
    public static final int SECS = 83;
    public static final int SHOULD_NOT_USE = 0;
    public static final int US_GALLON = 66;
    public static final int WATT_HOUR = 96;
    public static final int YEAR = 89;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "SHOULD_NOT_USE");
        hashmap.put(1, "METER_PER_SEC");
        hashmap.put(2, "RPM");
        hashmap.put(3, "HERTZ");
        hashmap.put(16, "PERCENTILE");
        hashmap.put(32, "MILLIMETER");
        hashmap.put(33, "METER");
        hashmap.put(35, "KILOMETER");
        hashmap.put(36, "MILE");
        hashmap.put(48, "CELSIUS");
        hashmap.put(49, "FAHRENHEIT");
        hashmap.put(50, "KELVIN");
        hashmap.put(64, "MILLILITER");
        hashmap.put(65, "LITER");
        hashmap.put(66, "GALLON");
        hashmap.put(66, "US_GALLON");
        hashmap.put(67, "IMPERIAL_GALLON");
        hashmap.put(80, "NANO_SECS");
        hashmap.put(83, "SECS");
        hashmap.put(89, "YEAR");
        hashmap.put(96, "WATT_HOUR");
        hashmap.put(97, "MILLIAMPERE");
        hashmap.put(98, "MILLIVOLT");
        hashmap.put(99, "MILLIWATTS");
        hashmap.put(100, "AMPERE_HOURS");
        hashmap.put(101, "KILOWATT_HOUR");
        hashmap.put(112, "KILOPASCAL");
        hashmap.put(113, "PSI");
        hashmap.put(114, "BAR");
        hashmap.put(128, "DEGREES");
        hashmap.put(Integer.valueOf(MILES_PER_HOUR), "MILES_PER_HOUR");
        hashmap.put(Integer.valueOf(KILOMETERS_PER_HOUR), "KILOMETERS_PER_HOUR");
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
