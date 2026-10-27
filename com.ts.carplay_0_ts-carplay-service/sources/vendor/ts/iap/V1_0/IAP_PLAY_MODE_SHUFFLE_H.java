package vendor.ts.iap.V1_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class IAP_PLAY_MODE_SHUFFLE_H {
    public static final int IAP_PLAY_MODE_SHUFFLE_ALBUMS = 2;
    public static final int IAP_PLAY_MODE_SHUFFLE_OFF = 0;
    public static final int IAP_PLAY_MODE_SHUFFLE_TRACKS = 1;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "IAP_PLAY_MODE_SHUFFLE_OFF");
        hashmap.put(1, "IAP_PLAY_MODE_SHUFFLE_TRACKS");
        hashmap.put(2, "IAP_PLAY_MODE_SHUFFLE_ALBUMS");
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
