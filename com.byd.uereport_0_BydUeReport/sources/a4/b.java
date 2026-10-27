package a4;

import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: XFragmentCache.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile ConcurrentHashMap<String, ?> f81a;

    public static void a() {
        if (f81a == null) {
            return;
        }
        f81a.clear();
    }
}
