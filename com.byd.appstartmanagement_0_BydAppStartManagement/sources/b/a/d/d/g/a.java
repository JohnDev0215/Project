package b.a.d.d.g;

import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: PageIndexCache.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap<String, Integer> f332a = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ConcurrentHashMap<String, String> f333b = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final ConcurrentHashMap<String, String> f334c = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile boolean f335d = false;

    public static int a(String str) {
        ConcurrentHashMap<String, Integer> concurrentHashMap = f332a;
        if (concurrentHashMap.get(str) == null) {
            return 0;
        }
        return concurrentHashMap.get(str).intValue();
    }

    public static String b(String str) {
        return f334c.get(str);
    }
}
