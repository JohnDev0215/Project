package kotlinx.coroutines;

import g5.l;

/* JADX INFO: compiled from: DebugStrings.kt */
/* JADX INFO: loaded from: classes.dex */
public final class m0 {
    public static final String a(Object obj) {
        return obj.getClass().getSimpleName();
    }

    public static final String b(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    public static final String c(k5.d<?> dVar) {
        Object objA;
        if (dVar instanceof kotlinx.coroutines.internal.h) {
            return dVar.toString();
        }
        try {
            l.a aVar = g5.l.f6557e;
            objA = g5.l.a(dVar + '@' + b(dVar));
        } catch (Throwable th) {
            l.a aVar2 = g5.l.f6557e;
            objA = g5.l.a(g5.m.a(th));
        }
        if (g5.l.b(objA) != null) {
            objA = dVar.getClass().getName() + '@' + b(dVar);
        }
        return (String) objA;
    }
}
