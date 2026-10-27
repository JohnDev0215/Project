package b.a.b.a;

import java.util.HashMap;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashMap<Integer, String> f7a;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        f7a = map;
        map.put(0, "EXPO_NO_RESPONCE");
        f7a.put(1, "EXPO_SUCCESS");
        f7a.put(2, "EXPO_EXPORTING");
        f7a.put(3, "EXPO_EMPTY");
        f7a.put(4, "EXPO_NO_DISK_ERR");
        f7a.put(5, "EXPO_WRITE_ERR");
    }

    public static final String a(int i) {
        if (f7a.containsKey(Integer.valueOf(i))) {
            return f7a.get(Integer.valueOf(i));
        }
        return "0x" + Integer.toHexString(i);
    }
}
