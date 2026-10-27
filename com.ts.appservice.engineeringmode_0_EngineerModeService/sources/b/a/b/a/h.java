package b.a.b.a;

import java.util.HashMap;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashMap<Integer, String> f12a;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        f12a = map;
        map.put(0, "ALL_MODULE");
        f12a.put(1, "SCC");
        f12a.put(11, "QNX_AUDIO");
        f12a.put(12, "QNX_ETH");
        f12a.put(13, "QNX_VEHICLE");
        f12a.put(14, "QNX_DIAG");
        f12a.put(15, "QNX_APPL");
        f12a.put(16, "QNX_INC");
        f12a.put(17, "QNX_LCM");
        f12a.put(18, "QNX_CLUSTER");
        f12a.put(19, "QNX_ESM");
        f12a.put(20, "QNX_BSP");
        f12a.put(40, "ANDROID_KERNEL");
        f12a.put(41, "ANDROID_SYSTEM");
        f12a.put(42, "END");
    }

    public static final String a(int i) {
        if (f12a.containsKey(Integer.valueOf(i))) {
            return f12a.get(Integer.valueOf(i));
        }
        return "0x" + Integer.toHexString(i);
    }
}
