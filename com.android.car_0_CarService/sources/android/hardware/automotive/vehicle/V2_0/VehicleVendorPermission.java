package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class VehicleVendorPermission {
    public static final int PERMISSION_DEFAULT = 0;
    public static final int PERMISSION_GET_VENDOR_CATEGORY_1 = 69632;
    public static final int PERMISSION_GET_VENDOR_CATEGORY_10 = 659456;
    public static final int PERMISSION_GET_VENDOR_CATEGORY_2 = 135168;
    public static final int PERMISSION_GET_VENDOR_CATEGORY_3 = 200704;
    public static final int PERMISSION_GET_VENDOR_CATEGORY_4 = 266240;
    public static final int PERMISSION_GET_VENDOR_CATEGORY_5 = 331776;
    public static final int PERMISSION_GET_VENDOR_CATEGORY_6 = 397312;
    public static final int PERMISSION_GET_VENDOR_CATEGORY_7 = 462848;
    public static final int PERMISSION_GET_VENDOR_CATEGORY_8 = 528384;
    public static final int PERMISSION_GET_VENDOR_CATEGORY_9 = 593920;
    public static final int PERMISSION_GET_VENDOR_CATEGORY_DOOR = 4;
    public static final int PERMISSION_GET_VENDOR_CATEGORY_ENGINE = 12;
    public static final int PERMISSION_GET_VENDOR_CATEGORY_HVAC = 14;
    public static final int PERMISSION_GET_VENDOR_CATEGORY_INFO = 10;
    public static final int PERMISSION_GET_VENDOR_CATEGORY_LIGHT = 16;
    public static final int PERMISSION_GET_VENDOR_CATEGORY_MIRROR = 8;
    public static final int PERMISSION_GET_VENDOR_CATEGORY_SEAT = 6;
    public static final int PERMISSION_GET_VENDOR_CATEGORY_WINDOW = 2;
    public static final int PERMISSION_NOT_ACCESSIBLE = -268435456;
    public static final int PERMISSION_SET_VENDOR_CATEGORY_1 = 65536;
    public static final int PERMISSION_SET_VENDOR_CATEGORY_10 = 655360;
    public static final int PERMISSION_SET_VENDOR_CATEGORY_2 = 131072;
    public static final int PERMISSION_SET_VENDOR_CATEGORY_3 = 196608;
    public static final int PERMISSION_SET_VENDOR_CATEGORY_4 = 262144;
    public static final int PERMISSION_SET_VENDOR_CATEGORY_5 = 327680;
    public static final int PERMISSION_SET_VENDOR_CATEGORY_6 = 393216;
    public static final int PERMISSION_SET_VENDOR_CATEGORY_7 = 458752;
    public static final int PERMISSION_SET_VENDOR_CATEGORY_8 = 524288;
    public static final int PERMISSION_SET_VENDOR_CATEGORY_9 = 589824;
    public static final int PERMISSION_SET_VENDOR_CATEGORY_DOOR = 3;
    public static final int PERMISSION_SET_VENDOR_CATEGORY_ENGINE = 11;
    public static final int PERMISSION_SET_VENDOR_CATEGORY_HVAC = 13;
    public static final int PERMISSION_SET_VENDOR_CATEGORY_INFO = 9;
    public static final int PERMISSION_SET_VENDOR_CATEGORY_LIGHT = 15;
    public static final int PERMISSION_SET_VENDOR_CATEGORY_MIRROR = 7;
    public static final int PERMISSION_SET_VENDOR_CATEGORY_SEAT = 5;
    public static final int PERMISSION_SET_VENDOR_CATEGORY_WINDOW = 1;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "PERMISSION_DEFAULT");
        hashmap.put(1, "PERMISSION_SET_VENDOR_CATEGORY_WINDOW");
        hashmap.put(2, "PERMISSION_GET_VENDOR_CATEGORY_WINDOW");
        hashmap.put(3, "PERMISSION_SET_VENDOR_CATEGORY_DOOR");
        hashmap.put(4, "PERMISSION_GET_VENDOR_CATEGORY_DOOR");
        hashmap.put(5, "PERMISSION_SET_VENDOR_CATEGORY_SEAT");
        hashmap.put(6, "PERMISSION_GET_VENDOR_CATEGORY_SEAT");
        hashmap.put(7, "PERMISSION_SET_VENDOR_CATEGORY_MIRROR");
        hashmap.put(8, "PERMISSION_GET_VENDOR_CATEGORY_MIRROR");
        hashmap.put(9, "PERMISSION_SET_VENDOR_CATEGORY_INFO");
        hashmap.put(10, "PERMISSION_GET_VENDOR_CATEGORY_INFO");
        hashmap.put(11, "PERMISSION_SET_VENDOR_CATEGORY_ENGINE");
        hashmap.put(12, "PERMISSION_GET_VENDOR_CATEGORY_ENGINE");
        hashmap.put(13, "PERMISSION_SET_VENDOR_CATEGORY_HVAC");
        hashmap.put(14, "PERMISSION_GET_VENDOR_CATEGORY_HVAC");
        hashmap.put(15, "PERMISSION_SET_VENDOR_CATEGORY_LIGHT");
        hashmap.put(16, "PERMISSION_GET_VENDOR_CATEGORY_LIGHT");
        hashmap.put(65536, "PERMISSION_SET_VENDOR_CATEGORY_1");
        hashmap.put(Integer.valueOf(PERMISSION_GET_VENDOR_CATEGORY_1), "PERMISSION_GET_VENDOR_CATEGORY_1");
        hashmap.put(131072, "PERMISSION_SET_VENDOR_CATEGORY_2");
        hashmap.put(Integer.valueOf(PERMISSION_GET_VENDOR_CATEGORY_2), "PERMISSION_GET_VENDOR_CATEGORY_2");
        hashmap.put(Integer.valueOf(PERMISSION_SET_VENDOR_CATEGORY_3), "PERMISSION_SET_VENDOR_CATEGORY_3");
        hashmap.put(Integer.valueOf(PERMISSION_GET_VENDOR_CATEGORY_3), "PERMISSION_GET_VENDOR_CATEGORY_3");
        hashmap.put(262144, "PERMISSION_SET_VENDOR_CATEGORY_4");
        hashmap.put(Integer.valueOf(PERMISSION_GET_VENDOR_CATEGORY_4), "PERMISSION_GET_VENDOR_CATEGORY_4");
        hashmap.put(Integer.valueOf(PERMISSION_SET_VENDOR_CATEGORY_5), "PERMISSION_SET_VENDOR_CATEGORY_5");
        hashmap.put(Integer.valueOf(PERMISSION_GET_VENDOR_CATEGORY_5), "PERMISSION_GET_VENDOR_CATEGORY_5");
        hashmap.put(Integer.valueOf(PERMISSION_SET_VENDOR_CATEGORY_6), "PERMISSION_SET_VENDOR_CATEGORY_6");
        hashmap.put(Integer.valueOf(PERMISSION_GET_VENDOR_CATEGORY_6), "PERMISSION_GET_VENDOR_CATEGORY_6");
        hashmap.put(Integer.valueOf(PERMISSION_SET_VENDOR_CATEGORY_7), "PERMISSION_SET_VENDOR_CATEGORY_7");
        hashmap.put(Integer.valueOf(PERMISSION_GET_VENDOR_CATEGORY_7), "PERMISSION_GET_VENDOR_CATEGORY_7");
        hashmap.put(524288, "PERMISSION_SET_VENDOR_CATEGORY_8");
        hashmap.put(Integer.valueOf(PERMISSION_GET_VENDOR_CATEGORY_8), "PERMISSION_GET_VENDOR_CATEGORY_8");
        hashmap.put(Integer.valueOf(PERMISSION_SET_VENDOR_CATEGORY_9), "PERMISSION_SET_VENDOR_CATEGORY_9");
        hashmap.put(Integer.valueOf(PERMISSION_GET_VENDOR_CATEGORY_9), "PERMISSION_GET_VENDOR_CATEGORY_9");
        hashmap.put(Integer.valueOf(PERMISSION_SET_VENDOR_CATEGORY_10), "PERMISSION_SET_VENDOR_CATEGORY_10");
        hashmap.put(Integer.valueOf(PERMISSION_GET_VENDOR_CATEGORY_10), "PERMISSION_GET_VENDOR_CATEGORY_10");
        hashmap.put(-268435456, "PERMISSION_NOT_ACCESSIBLE");
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
