package android.hardware.tetheroffload.control.V1_0;

import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkProtocol {
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(6, "TCP");
        hashmap.put(17, "UDP");
    }

    public static final String toString(int i) {
        if (hashmap.containsKey(Integer.valueOf(i))) {
            return hashmap.get(Integer.valueOf(i));
        }
        return "0x" + Integer.toHexString(i);
    }
}
