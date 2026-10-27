package vendor.bosch.display.V1_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class DisplayHeightLevel {
    public static final int DISPLAY_HEIGHT_LEVEL_0 = 0;
    public static final int DISPLAY_HEIGHT_LEVEL_1 = 1;
    public static final int DISPLAY_HEIGHT_LEVEL_10 = 10;
    public static final int DISPLAY_HEIGHT_LEVEL_11 = 11;
    public static final int DISPLAY_HEIGHT_LEVEL_12 = 12;
    public static final int DISPLAY_HEIGHT_LEVEL_13 = 13;
    public static final int DISPLAY_HEIGHT_LEVEL_14 = 14;
    public static final int DISPLAY_HEIGHT_LEVEL_15 = 15;
    public static final int DISPLAY_HEIGHT_LEVEL_16 = 16;
    public static final int DISPLAY_HEIGHT_LEVEL_17 = 17;
    public static final int DISPLAY_HEIGHT_LEVEL_18 = 18;
    public static final int DISPLAY_HEIGHT_LEVEL_19 = 19;
    public static final int DISPLAY_HEIGHT_LEVEL_2 = 2;
    public static final int DISPLAY_HEIGHT_LEVEL_20 = 20;
    public static final int DISPLAY_HEIGHT_LEVEL_3 = 3;
    public static final int DISPLAY_HEIGHT_LEVEL_4 = 4;
    public static final int DISPLAY_HEIGHT_LEVEL_5 = 5;
    public static final int DISPLAY_HEIGHT_LEVEL_6 = 6;
    public static final int DISPLAY_HEIGHT_LEVEL_7 = 7;
    public static final int DISPLAY_HEIGHT_LEVEL_8 = 8;
    public static final int DISPLAY_HEIGHT_LEVEL_9 = 9;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "DISPLAY_HEIGHT_LEVEL_0");
        hashmap.put(1, "DISPLAY_HEIGHT_LEVEL_1");
        hashmap.put(2, "DISPLAY_HEIGHT_LEVEL_2");
        hashmap.put(3, "DISPLAY_HEIGHT_LEVEL_3");
        hashmap.put(4, "DISPLAY_HEIGHT_LEVEL_4");
        hashmap.put(5, "DISPLAY_HEIGHT_LEVEL_5");
        hashmap.put(6, "DISPLAY_HEIGHT_LEVEL_6");
        hashmap.put(7, "DISPLAY_HEIGHT_LEVEL_7");
        hashmap.put(8, "DISPLAY_HEIGHT_LEVEL_8");
        hashmap.put(9, "DISPLAY_HEIGHT_LEVEL_9");
        hashmap.put(10, "DISPLAY_HEIGHT_LEVEL_10");
        hashmap.put(11, "DISPLAY_HEIGHT_LEVEL_11");
        hashmap.put(12, "DISPLAY_HEIGHT_LEVEL_12");
        hashmap.put(13, "DISPLAY_HEIGHT_LEVEL_13");
        hashmap.put(14, "DISPLAY_HEIGHT_LEVEL_14");
        hashmap.put(15, "DISPLAY_HEIGHT_LEVEL_15");
        hashmap.put(16, "DISPLAY_HEIGHT_LEVEL_16");
        hashmap.put(17, "DISPLAY_HEIGHT_LEVEL_17");
        hashmap.put(18, "DISPLAY_HEIGHT_LEVEL_18");
        hashmap.put(19, "DISPLAY_HEIGHT_LEVEL_19");
        hashmap.put(20, "DISPLAY_HEIGHT_LEVEL_20");
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
