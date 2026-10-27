package defpackage;

import defpackage.m00;
import java.io.Serializable;

/* JADX INFO: compiled from: CoroutineContextImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public final class dh0 implements m00, Serializable {
    public static final dh0 e = new dh0();
    private static final long serialVersionUID = 0;

    private final Object readResolve() {
        return e;
    }

    @Override // defpackage.m00
    public final <E extends m00.b> E get(m00.c<E> cVar) {
        i21.e(cVar, "key");
        return null;
    }

    public final int hashCode() {
        return 0;
    }

    @Override // defpackage.m00
    public final m00 minusKey(m00.c<?> cVar) {
        i21.e(cVar, "key");
        return this;
    }

    @Override // defpackage.m00
    public final m00 plus(m00 m00Var) {
        i21.e(m00Var, "context");
        return m00Var;
    }

    public final String toString() {
        return "EmptyCoroutineContext";
    }

    @Override // defpackage.m00
    public final <R> R fold(R r, nr0<? super R, ? super m00.b, ? extends R> nr0Var) {
        return r;
    }
}
