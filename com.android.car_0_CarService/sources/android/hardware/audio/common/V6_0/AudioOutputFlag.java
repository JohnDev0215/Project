package android.hardware.audio.common.V6_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class AudioOutputFlag {
    public static final int COMPRESS_OFFLOAD = 16;
    public static final int DEEP_BUFFER = 8;
    public static final int DIRECT = 1;
    public static final int DIRECT_PCM = 8192;
    public static final int FAST = 4;
    public static final int HW_AV_SYNC = 64;
    public static final int IEC958_NONAUDIO = 1024;
    public static final int INCALL_MUSIC = 65536;
    public static final int MMAP_NOIRQ = 16384;
    public static final int NONE = 0;
    public static final int NON_BLOCKING = 32;
    public static final int PRIMARY = 2;
    public static final int RAW = 256;
    public static final int SYNC = 512;
    public static final int TTS = 128;
    public static final int VOIP_RX = 32768;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "NONE");
        hashmap.put(1, "DIRECT");
        hashmap.put(2, "PRIMARY");
        hashmap.put(4, "FAST");
        hashmap.put(8, "DEEP_BUFFER");
        hashmap.put(16, "COMPRESS_OFFLOAD");
        hashmap.put(32, "NON_BLOCKING");
        hashmap.put(64, "HW_AV_SYNC");
        hashmap.put(128, "TTS");
        hashmap.put(256, "RAW");
        hashmap.put(512, "SYNC");
        hashmap.put(1024, "IEC958_NONAUDIO");
        hashmap.put(8192, "DIRECT_PCM");
        hashmap.put(16384, "MMAP_NOIRQ");
        hashmap.put(32768, "VOIP_RX");
        hashmap.put(65536, "INCALL_MUSIC");
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
