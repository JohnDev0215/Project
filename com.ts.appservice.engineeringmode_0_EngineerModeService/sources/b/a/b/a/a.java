package b.a.b.a;

import java.util.HashMap;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashMap<Integer, String> f4a;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        f4a = map;
        map.put(0, "ALL");
        f4a.put(1, "IHU");
        f4a.put(2, "TBOX");
        f4a.put(3, "NAVI");
        f4a.put(4, "BLUTO");
        f4a.put(5, "END");
    }

    public static final String a(int i) {
        if (f4a.containsKey(Integer.valueOf(i))) {
            return f4a.get(Integer.valueOf(i));
        }
        return "0x" + Integer.toHexString(i);
    }
}
