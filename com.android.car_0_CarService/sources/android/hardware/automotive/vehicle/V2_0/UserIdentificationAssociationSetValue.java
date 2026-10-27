package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class UserIdentificationAssociationSetValue {
    public static final int ASSOCIATE_CURRENT_USER = 1;
    public static final int DISASSOCIATE_ALL_USERS = 3;
    public static final int DISASSOCIATE_CURRENT_USER = 2;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(1, "ASSOCIATE_CURRENT_USER");
        hashmap.put(2, "DISASSOCIATE_CURRENT_USER");
        hashmap.put(3, "DISASSOCIATE_ALL_USERS");
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
