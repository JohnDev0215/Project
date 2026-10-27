package kotlinx.coroutines.internal;

import g5.l;

/* JADX INFO: compiled from: FastServiceLoader.kt */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f7653a;

    static {
        Object objA;
        try {
            l.a aVar = g5.l.f6557e;
            objA = g5.l.a(Class.forName("android.os.Build"));
        } catch (Throwable th) {
            l.a aVar2 = g5.l.f6557e;
            objA = g5.l.a(g5.m.a(th));
        }
        f7653a = g5.l.d(objA);
    }

    public static final boolean a() {
        return f7653a;
    }
}
