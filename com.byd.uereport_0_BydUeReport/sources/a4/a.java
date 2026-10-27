package a4;

import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: PageIndexCache.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap<String, Integer> f78a = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ConcurrentHashMap<String, String> f79b = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final ConcurrentHashMap<String, String> f80c = new ConcurrentHashMap<>();

    public static int a(String str) {
        ConcurrentHashMap<String, Integer> concurrentHashMap = f78a;
        if (concurrentHashMap.get(str) == null) {
            return 0;
        }
        return concurrentHashMap.get(str).intValue();
    }
}
