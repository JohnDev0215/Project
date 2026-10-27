package defpackage;

import defpackage.m00;

/* JADX INFO: compiled from: CoroutineContext.kt */
/* JADX INFO: loaded from: classes.dex */
public final class hi3 implements m00.b, m00.c<hi3> {
    public static final hi3 e = new hi3();

    @Override // defpackage.m00
    public final <R> R fold(R r, nr0<? super R, ? super m00.b, ? extends R> nr0Var) {
        return nr0Var.d(r, this);
    }

    @Override // m00.b, defpackage.m00
    public final <E extends m00.b> E get(m00.c<E> cVar) {
        return (E) m00.b.a.a(this, cVar);
    }

    @Override // defpackage.m00
    public final m00 minusKey(m00.c<?> cVar) {
        return m00.b.a.b(this, cVar);
    }

    @Override // defpackage.m00
    public final m00 plus(m00 m00Var) {
        i21.e(m00Var, "context");
        return m00.a.a(this, m00Var);
    }

    @Override // m00.b
    public final m00.c<?> getKey() {
        return this;
    }
}
