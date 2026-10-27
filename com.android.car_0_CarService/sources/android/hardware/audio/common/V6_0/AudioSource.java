package android.hardware.audio.common.V6_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class AudioSource {
    public static final int CAMCORDER = 5;
    public static final int DEFAULT = 0;
    public static final int ECHO_REFERENCE = 1997;
    public static final int FM_TUNER = 1998;
    public static final int HOTWORD = 1999;
    public static final int MIC = 1;
    public static final int REMOTE_SUBMIX = 8;
    public static final int UNPROCESSED = 9;
    public static final int VOICE_CALL = 4;
    public static final int VOICE_COMMUNICATION = 7;
    public static final int VOICE_DOWNLINK = 3;
    public static final int VOICE_PERFORMANCE = 10;
    public static final int VOICE_RECOGNITION = 6;
    public static final int VOICE_UPLINK = 2;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "DEFAULT");
        hashmap.put(1, "MIC");
        hashmap.put(2, "VOICE_UPLINK");
        hashmap.put(3, "VOICE_DOWNLINK");
        hashmap.put(4, "VOICE_CALL");
        hashmap.put(5, "CAMCORDER");
        hashmap.put(6, "VOICE_RECOGNITION");
        hashmap.put(7, "VOICE_COMMUNICATION");
        hashmap.put(8, "REMOTE_SUBMIX");
        hashmap.put(9, "UNPROCESSED");
        hashmap.put(10, "VOICE_PERFORMANCE");
        hashmap.put(Integer.valueOf(ECHO_REFERENCE), "ECHO_REFERENCE");
        hashmap.put(Integer.valueOf(FM_TUNER), "FM_TUNER");
        hashmap.put(Integer.valueOf(HOTWORD), "HOTWORD");
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
