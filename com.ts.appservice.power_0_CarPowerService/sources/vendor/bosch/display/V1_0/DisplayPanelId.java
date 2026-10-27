package vendor.bosch.display.V1_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class DisplayPanelId {
    public static final int DISPLAYPANEL_ARMREST = 4;
    public static final int DISPLAYPANEL_CENTRAL_IVI = 1;
    public static final int DISPLAYPANEL_CLUSTER = 2;
    public static final int DISPLAYPANEL_CONSOLE = 3;
    public static final int DISPLAYPANEL_COPILOT = 6;
    public static final int DISPLAYPANEL_HUD = 7;
    public static final int DISPLAYPANEL_INVALID = 0;
    public static final int DISPLAYPANEL_RES = 5;
    public static final int DISPLAYPANEL_ROBOT = 8;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "DISPLAYPANEL_INVALID");
        hashmap.put(1, "DISPLAYPANEL_CENTRAL_IVI");
        hashmap.put(2, "DISPLAYPANEL_CLUSTER");
        hashmap.put(3, "DISPLAYPANEL_CONSOLE");
        hashmap.put(4, "DISPLAYPANEL_ARMREST");
        hashmap.put(5, "DISPLAYPANEL_RES");
        hashmap.put(6, "DISPLAYPANEL_COPILOT");
        hashmap.put(7, "DISPLAYPANEL_HUD");
        hashmap.put(8, "DISPLAYPANEL_ROBOT");
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
