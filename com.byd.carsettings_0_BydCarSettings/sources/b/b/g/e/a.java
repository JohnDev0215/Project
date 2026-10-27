package b.b.g.e;

import b.b.g.p.j.e;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: UsbStateCache.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ConcurrentHashMap<String, e> f757a;

    public static void a() {
        ConcurrentHashMap<String, e> concurrentHashMap = f757a;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
    }

    public static e b(String str) {
        if (f757a == null) {
            f757a = new ConcurrentHashMap<>();
        }
        return f757a.get(str);
    }

    public static void c(String str, e eVar) {
        if (f757a == null) {
            f757a = new ConcurrentHashMap<>();
        }
        f757a.put(str, eVar);
    }
}
