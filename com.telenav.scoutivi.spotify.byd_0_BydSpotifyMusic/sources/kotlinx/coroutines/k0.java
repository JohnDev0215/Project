package kotlinx.coroutines;

/* JADX INFO: compiled from: CoroutineStart.kt */
/* JADX INFO: loaded from: classes.dex */
public enum k0 {
    DEFAULT,
    LAZY,
    ATOMIC,
    UNDISPATCHED;

    public static /* synthetic */ void isLazy$annotations() {
    }

    public final <T> void invoke(r5.l<? super k5.d<? super T>, ? extends Object> lVar, k5.d<? super T> dVar) {
        int i7 = j0.f7690a[ordinal()];
        if (i7 == 1) {
            b6.a.b(lVar, dVar);
            return;
        }
        if (i7 == 2) {
            k5.f.a(lVar, dVar);
        } else if (i7 == 3) {
            b6.b.a(lVar, dVar);
        } else if (i7 != 4) {
            throw new g5.j();
        }
    }

    public final boolean isLazy() {
        return this == LAZY;
    }

    public final <R, T> void invoke(r5.p<? super R, ? super k5.d<? super T>, ? extends Object> pVar, R r7, k5.d<? super T> dVar) {
        int i7 = j0.f7691b[ordinal()];
        if (i7 == 1) {
            b6.a.d(pVar, r7, dVar, null, 4, null);
            return;
        }
        if (i7 == 2) {
            k5.f.b(pVar, r7, dVar);
        } else if (i7 == 3) {
            b6.b.b(pVar, r7, dVar);
        } else if (i7 != 4) {
            throw new g5.j();
        }
    }
}
