package kotlinx.coroutines;

import g5.l;

/* JADX INFO: compiled from: CompletionState.kt */
/* JADX INFO: loaded from: classes.dex */
public final class c0 {
    public static final <T> Object a(Object obj, k5.d<? super T> dVar) {
        if (obj instanceof y) {
            l.a aVar = g5.l.f6557e;
            return g5.l.a(g5.m.a(((y) obj).f7851a));
        }
        l.a aVar2 = g5.l.f6557e;
        return g5.l.a(obj);
    }

    public static final <T> Object b(Object obj, i<?> iVar) {
        Throwable thB = g5.l.b(obj);
        return thB == null ? obj : new y(thB, false, 2, null);
    }

    public static final <T> Object c(Object obj, r5.l<? super Throwable, g5.r> lVar) {
        Throwable thB = g5.l.b(obj);
        return thB == null ? lVar != null ? new z(obj, lVar) : obj : new y(thB, false, 2, null);
    }

    public static /* synthetic */ Object d(Object obj, r5.l lVar, int i7, Object obj2) {
        if ((i7 & 1) != 0) {
            lVar = null;
        }
        return c(obj, lVar);
    }
}
