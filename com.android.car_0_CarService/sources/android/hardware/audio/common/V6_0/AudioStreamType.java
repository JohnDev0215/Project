package android.hardware.audio.common.V6_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class AudioStreamType {
    public static final int ACCESSIBILITY = 10;
    public static final int ALARM = 4;
    public static final int ASSISTANT = 11;
    public static final int BLUETOOTH_SCO = 6;
    public static final int DEFAULT = -1;
    public static final int DTMF = 8;
    public static final int ENFORCED_AUDIBLE = 7;
    public static final int MIN = 0;
    public static final int MUSIC = 3;
    public static final int NOTIFICATION = 5;
    public static final int RING = 2;
    public static final int SYSTEM = 1;
    public static final int TTS = 9;
    public static final int VOICE_CALL = 0;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(-1, "DEFAULT");
        hashmap.put(0, "MIN");
        hashmap.put(0, "VOICE_CALL");
        hashmap.put(1, "SYSTEM");
        hashmap.put(2, "RING");
        hashmap.put(3, "MUSIC");
        hashmap.put(4, "ALARM");
        hashmap.put(5, "NOTIFICATION");
        hashmap.put(6, "BLUETOOTH_SCO");
        hashmap.put(7, "ENFORCED_AUDIBLE");
        hashmap.put(8, "DTMF");
        hashmap.put(9, "TTS");
        hashmap.put(10, "ACCESSIBILITY");
        hashmap.put(11, "ASSISTANT");
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
