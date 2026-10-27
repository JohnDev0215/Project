package a3;

import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: BTCache.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ConcurrentHashMap<String, b3.a> f76a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static ConcurrentHashMap<String, String> f77b;

    public static void a() {
        ConcurrentHashMap<String, b3.a> concurrentHashMap = f76a;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
    }

    public static void b() {
        ConcurrentHashMap<String, String> concurrentHashMap = f77b;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
        ConcurrentHashMap<String, b3.a> concurrentHashMap2 = f76a;
        if (concurrentHashMap2 != null) {
            concurrentHashMap2.clear();
        }
    }

    public static b3.a c(String str) {
        if (f76a == null) {
            f76a = new ConcurrentHashMap<>();
        }
        return f76a.get(str);
    }

    public static String d(String str) {
        if (f77b == null) {
            f77b = new ConcurrentHashMap<>();
        }
        return f77b.get(str);
    }

    public static void e(String str, b3.a aVar) {
        if (f76a == null) {
            f76a = new ConcurrentHashMap<>();
        }
        f76a.put(str, aVar);
    }

    public static void f(String str, String str2) {
        if (f77b == null) {
            f77b = new ConcurrentHashMap<>();
        }
        f77b.put(str, str2);
    }
}
