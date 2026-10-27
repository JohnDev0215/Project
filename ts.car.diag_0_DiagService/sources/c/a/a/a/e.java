package c.a.a.a;

import java.util.HashMap;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashMap<Integer, String> f1290a;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        f1290a = map;
        map.put(0, "OK");
        f1290a.put(1, "ERROR");
    }

    public static final String a(int i) {
        if (f1290a.containsKey(Integer.valueOf(i))) {
            return f1290a.get(Integer.valueOf(i));
        }
        return "0x" + Integer.toHexString(i);
    }
}
