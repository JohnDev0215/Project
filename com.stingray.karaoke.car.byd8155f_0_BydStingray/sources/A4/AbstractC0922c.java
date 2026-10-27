package a4;

import h6.n;
import java.util.HashSet;

/* JADX INFO: renamed from: a4.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0922c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0921b f11094a = new C0921b();

    public static void a(String str) {
        f11094a.getClass();
        HashSet hashSet = C0921b.f11093a;
        if (hashSet.contains(str)) {
            return;
        }
        n.u("LOTTIE", str, null);
        hashSet.add(str);
    }

    public static void b(String str, Throwable th) {
        f11094a.getClass();
        HashSet hashSet = C0921b.f11093a;
        if (hashSet.contains(str)) {
            return;
        }
        n.u("LOTTIE", str, th);
        hashSet.add(str);
    }
}
