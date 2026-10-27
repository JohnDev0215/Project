package vendor.ts.firewall.V1_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ResultType {
    public static final int ADD_APP_RULE = 1;
    public static final int ADD_IP_RULE = 3;
    public static final int ADD_PORT_RULE = 5;
    public static final int ADD_URL_RULE = 7;
    public static final int REMOVE_APP_RULE = 2;
    public static final int REMOVE_IP_RULE = 4;
    public static final int REMOVE_PORT_RULE = 6;
    public static final int REMOVE_URL_RULE = 8;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(1, "ADD_APP_RULE");
        hashmap.put(2, "REMOVE_APP_RULE");
        hashmap.put(3, "ADD_IP_RULE");
        hashmap.put(4, "REMOVE_IP_RULE");
        hashmap.put(5, "ADD_PORT_RULE");
        hashmap.put(6, "REMOVE_PORT_RULE");
        hashmap.put(7, "ADD_URL_RULE");
        hashmap.put(8, "REMOVE_URL_RULE");
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
