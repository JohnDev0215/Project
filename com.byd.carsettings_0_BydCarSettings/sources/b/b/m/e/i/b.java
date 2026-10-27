package b.b.m.e.i;

import com.byd.mvp.mvp.XFragment;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: XFragmentCache.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile ConcurrentHashMap<String, XFragment> f2119a;

    public static void a() {
        if (f2119a == null) {
            return;
        }
        f2119a.clear();
    }

    public static void b(String str) {
        if (f2119a == null) {
            return;
        }
        for (String str2 : f2119a.keySet()) {
            if (str2.contains(str)) {
                f2119a.remove(str2);
            }
        }
    }

    public static XFragment c(String str) {
        if (f2119a == null) {
            return null;
        }
        return f2119a.get(str);
    }

    public static void d(String str) {
        if (f2119a == null) {
            return;
        }
        f2119a.remove(str);
    }
}
