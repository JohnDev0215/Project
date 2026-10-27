package b.b.d.d;

import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: BTCache.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ConcurrentHashMap<String, b.b.d.e.a> f614a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static ConcurrentHashMap<String, String> f615b;

    public static void a() {
        ConcurrentHashMap<String, b.b.d.e.a> concurrentHashMap = f614a;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
    }

    public static b.b.d.e.a b(String str) {
        if (f614a == null) {
            f614a = new ConcurrentHashMap<>();
        }
        return f614a.get(str);
    }

    public static String c(String str) {
        if (f615b == null) {
            f615b = new ConcurrentHashMap<>();
        }
        return f615b.get(str);
    }

    public static void d(String str, b.b.d.e.a aVar) {
        if (f614a == null) {
            f614a = new ConcurrentHashMap<>();
        }
        f614a.put(str, aVar);
    }

    public static void e(String str, String str2) {
        if (f615b == null) {
            f615b = new ConcurrentHashMap<>();
        }
        f615b.put(str, str2);
    }
}
