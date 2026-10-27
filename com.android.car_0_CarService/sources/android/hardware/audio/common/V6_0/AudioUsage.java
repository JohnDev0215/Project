package android.hardware.audio.common.V6_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class AudioUsage {
    public static final int ALARM = 4;
    public static final int ANNOUNCEMENT = 1003;
    public static final int ASSISTANCE_ACCESSIBILITY = 11;
    public static final int ASSISTANCE_NAVIGATION_GUIDANCE = 12;
    public static final int ASSISTANCE_SONIFICATION = 13;
    public static final int ASSISTANT = 16;
    public static final int CALL_ASSISTANT = 17;
    public static final int EMERGENCY = 1000;
    public static final int GAME = 14;
    public static final int MEDIA = 1;
    public static final int NOTIFICATION = 5;
    public static final int NOTIFICATION_TELEPHONY_RINGTONE = 6;
    public static final int SAFETY = 1001;
    public static final int UNKNOWN = 0;
    public static final int VEHICLE_STATUS = 1002;
    public static final int VIRTUAL_SOURCE = 15;
    public static final int VOICE_COMMUNICATION = 2;
    public static final int VOICE_COMMUNICATION_SIGNALLING = 3;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "UNKNOWN");
        hashmap.put(1, "MEDIA");
        hashmap.put(2, "VOICE_COMMUNICATION");
        hashmap.put(3, "VOICE_COMMUNICATION_SIGNALLING");
        hashmap.put(4, "ALARM");
        hashmap.put(5, "NOTIFICATION");
        hashmap.put(6, "NOTIFICATION_TELEPHONY_RINGTONE");
        hashmap.put(11, "ASSISTANCE_ACCESSIBILITY");
        hashmap.put(12, "ASSISTANCE_NAVIGATION_GUIDANCE");
        hashmap.put(13, "ASSISTANCE_SONIFICATION");
        hashmap.put(14, "GAME");
        hashmap.put(15, "VIRTUAL_SOURCE");
        hashmap.put(16, "ASSISTANT");
        hashmap.put(17, "CALL_ASSISTANT");
        hashmap.put(1000, "EMERGENCY");
        hashmap.put(1001, "SAFETY");
        hashmap.put(1002, "VEHICLE_STATUS");
        hashmap.put(1003, "ANNOUNCEMENT");
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
