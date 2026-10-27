package b.a.d.d.g;

import com.byd.mvp.mvp.XFragment;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: XFragmentCache.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile ConcurrentHashMap<String, XFragment> f336a;

    public static void a() {
        if (f336a == null) {
            return;
        }
        f336a.clear();
    }

    public static XFragment b(String str) {
        if (f336a == null) {
            return null;
        }
        return f336a.get(str);
    }

    public static void c(String str) {
        if (f336a == null) {
            return;
        }
        f336a.remove(str);
    }
}
