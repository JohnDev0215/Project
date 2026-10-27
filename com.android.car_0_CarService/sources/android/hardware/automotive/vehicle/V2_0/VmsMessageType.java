package android.hardware.automotive.vehicle.V2_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class VmsMessageType {
    public static final int AVAILABILITY_CHANGE = 9;
    public static final int AVAILABILITY_REQUEST = 6;
    public static final int AVAILABILITY_RESPONSE = 8;
    public static final int DATA = 12;
    public static final int LAST_VMS_MESSAGE_TYPE = 17;
    public static final int OFFERING = 5;
    public static final int PUBLISHER_ID_REQUEST = 13;
    public static final int PUBLISHER_ID_RESPONSE = 14;
    public static final int PUBLISHER_INFORMATION_REQUEST = 15;
    public static final int PUBLISHER_INFORMATION_RESPONSE = 16;
    public static final int START_SESSION = 17;
    public static final int SUBSCRIBE = 1;
    public static final int SUBSCRIBE_TO_PUBLISHER = 2;
    public static final int SUBSCRIPTIONS_CHANGE = 11;
    public static final int SUBSCRIPTIONS_REQUEST = 7;
    public static final int SUBSCRIPTIONS_RESPONSE = 10;
    public static final int UNSUBSCRIBE = 3;
    public static final int UNSUBSCRIBE_TO_PUBLISHER = 4;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(1, "SUBSCRIBE");
        hashmap.put(2, "SUBSCRIBE_TO_PUBLISHER");
        hashmap.put(3, "UNSUBSCRIBE");
        hashmap.put(4, "UNSUBSCRIBE_TO_PUBLISHER");
        hashmap.put(5, "OFFERING");
        hashmap.put(6, "AVAILABILITY_REQUEST");
        hashmap.put(7, "SUBSCRIPTIONS_REQUEST");
        hashmap.put(8, "AVAILABILITY_RESPONSE");
        hashmap.put(9, "AVAILABILITY_CHANGE");
        hashmap.put(10, "SUBSCRIPTIONS_RESPONSE");
        hashmap.put(11, "SUBSCRIPTIONS_CHANGE");
        hashmap.put(12, "DATA");
        hashmap.put(13, "PUBLISHER_ID_REQUEST");
        hashmap.put(14, "PUBLISHER_ID_RESPONSE");
        hashmap.put(15, "PUBLISHER_INFORMATION_REQUEST");
        hashmap.put(16, "PUBLISHER_INFORMATION_RESPONSE");
        hashmap.put(17, "START_SESSION");
        hashmap.put(17, "LAST_VMS_MESSAGE_TYPE");
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
