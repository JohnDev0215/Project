package kotlinx.coroutines.channels;

/* JADX INFO: compiled from: Channel.kt */
/* JADX INFO: loaded from: classes.dex */
public final class o {
    public static final <E> l<E> a(int i7, k kVar, r5.l<? super E, g5.r> lVar) {
        if (i7 == -2) {
            return new f(kVar == k.SUSPEND ? l.f7574f.a() : 1, kVar, lVar);
        }
        if (i7 != -1) {
            return i7 != 0 ? i7 != Integer.MAX_VALUE ? (i7 == 1 && kVar == k.DROP_OLDEST) ? new v(lVar) : new f(i7, kVar, lVar) : new x(lVar) : kVar == k.SUSPEND ? new e0(lVar) : new f(1, kVar, lVar);
        }
        if ((kVar != k.SUSPEND ? 0 : 1) != 0) {
            return new v(lVar);
        }
        throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow".toString());
    }

    public static /* synthetic */ l b(int i7, k kVar, r5.l lVar, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            i7 = 0;
        }
        if ((i8 & 2) != 0) {
            kVar = k.SUSPEND;
        }
        if ((i8 & 4) != 0) {
            lVar = null;
        }
        return a(i7, kVar, lVar);
    }
}
