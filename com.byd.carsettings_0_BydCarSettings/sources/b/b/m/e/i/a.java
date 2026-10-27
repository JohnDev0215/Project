package b.b.m.e.i;

import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: PageIndexCache.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static StringBuilder f2117d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap<String, Integer> f2114a = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ConcurrentHashMap<String, String> f2115b = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final ConcurrentHashMap<String, String> f2116c = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile boolean f2118e = false;

    public static void a() {
        b.b.m.c.a.d("PageIndexCache", "---clear--- ");
        ConcurrentHashMap<String, Integer> concurrentHashMap = f2114a;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
        ConcurrentHashMap<String, String> concurrentHashMap2 = f2115b;
        if (concurrentHashMap2 != null) {
            concurrentHashMap2.clear();
        }
        ConcurrentHashMap<String, String> concurrentHashMap3 = f2116c;
        if (concurrentHashMap3 != null) {
            concurrentHashMap3.clear();
        }
    }

    public static void b() {
        ConcurrentHashMap<String, String> concurrentHashMap = f2116c;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
    }

    public static synchronized String c(int[] iArr) {
        if (f2117d == null) {
            f2117d = new StringBuilder();
        }
        f2117d.setLength(0);
        if (iArr != null && iArr.length > 0) {
            for (int i : iArr) {
                StringBuilder sb = f2117d;
                sb.append(i);
                sb.append("_");
            }
            if (f2117d.toString().endsWith("_")) {
                f2117d.deleteCharAt(r4.length() - 1);
            }
        }
        return f2117d.toString();
    }

    public static int d(String str) {
        ConcurrentHashMap<String, Integer> concurrentHashMap = f2114a;
        if (concurrentHashMap.get(str) == null) {
            return 0;
        }
        return concurrentHashMap.get(str).intValue();
    }

    public static String e(int... iArr) {
        return f2115b.get(c(iArr));
    }

    public static int f(String str) {
        ConcurrentHashMap<String, Integer> concurrentHashMap = f2114a;
        if (concurrentHashMap.get(str) == null) {
            return -1;
        }
        return concurrentHashMap.get(str).intValue();
    }

    public static String g(String str) {
        return f2116c.get(str);
    }

    public static boolean h(String str) {
        return f2114a.containsKey(str);
    }

    public static boolean i() {
        return f2118e;
    }

    public static boolean j() {
        return !f2115b.isEmpty();
    }

    public static void k(String str, int i) {
        if (str == null) {
            return;
        }
        f2114a.put(str, Integer.valueOf(i));
    }

    public static void l(Class<?> cls, int... iArr) {
        f2115b.put(c(iArr), cls.getName());
    }

    public static void m(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        f2116c.put(str, str2);
    }

    public static void n(String str) {
        f2114a.remove(str);
    }

    public static void o(boolean z) {
        f2118e = z;
    }
}
