package b.a.b.a;

import java.util.HashMap;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashMap<Integer, String> f9a;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        f9a = map;
        map.put(0, "LOG_OFF");
        f9a.put(1, "LOG_FATAL");
        f9a.put(2, "LOG_ERROR");
        f9a.put(3, "LOG_WARN");
        f9a.put(4, "LOG_NOTICE");
        f9a.put(5, "LOG_INFO");
        f9a.put(6, "LOG_DEBUG");
        f9a.put(7, "LOG_VERBOSE");
        f9a.put(8, "LOG_DEFAULT");
        f9a.put(10, "LOG_MAX");
    }

    public static final String a(int i) {
        if (f9a.containsKey(Integer.valueOf(i))) {
            return f9a.get(Integer.valueOf(i));
        }
        return "0x" + Integer.toHexString(i);
    }
}
