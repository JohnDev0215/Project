package a.a.a.a.a;

import java.util.HashMap;

/* JADX INFO: compiled from: VehiclePropertyStatus.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashMap<Integer, String> f5a;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        f5a = map;
        map.put(0, "AVAILABLE");
        f5a.put(1, "UNAVAILABLE");
        f5a.put(2, "ERROR");
    }

    public static final String a(int i) {
        if (f5a.containsKey(Integer.valueOf(i))) {
            return f5a.get(Integer.valueOf(i));
        }
        return "0x" + Integer.toHexString(i);
    }
}
