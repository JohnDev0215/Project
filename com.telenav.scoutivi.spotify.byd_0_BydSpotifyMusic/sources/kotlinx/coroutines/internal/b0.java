package kotlinx.coroutines.internal;

import g5.l;

/* JADX INFO: compiled from: StackTraceRecovery.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f7620a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f7621b;

    static {
        Object objA;
        Object objA2;
        try {
            l.a aVar = g5.l.f6557e;
            objA = g5.l.a(Class.forName("kotlin.coroutines.jvm.internal.a").getCanonicalName());
        } catch (Throwable th) {
            l.a aVar2 = g5.l.f6557e;
            objA = g5.l.a(g5.m.a(th));
        }
        if (g5.l.b(objA) != null) {
            objA = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";
        }
        f7620a = (String) objA;
        try {
            l.a aVar3 = g5.l.f6557e;
            objA2 = g5.l.a(b0.class.getCanonicalName());
        } catch (Throwable th2) {
            l.a aVar4 = g5.l.f6557e;
            objA2 = g5.l.a(g5.m.a(th2));
        }
        if (g5.l.b(objA2) != null) {
            objA2 = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
        }
        f7621b = (String) objA2;
    }

    public static final <E extends Throwable> E a(E e7) {
        return e7;
    }
}
