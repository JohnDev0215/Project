package defpackage;

import defpackage.m00;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes.dex
 */
/* JADX INFO: renamed from: n, reason: case insensitive filesystem */
/* JADX INFO: compiled from: CoroutineContextImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0199n implements m00.b {
    private final m00.c<?> key;

    public AbstractC0199n(m00.c<?> cVar) {
        i21.e(cVar, "key");
        this.key = cVar;
    }

    @Override // defpackage.m00
    public <R> R fold(R r, nr0<? super R, ? super m00.b, ? extends R> nr0Var) {
        i21.e(nr0Var, "operation");
        return nr0Var.d(r, this);
    }

    @Override // m00.b, defpackage.m00
    public <E extends m00.b> E get(m00.c<E> cVar) {
        return (E) m00.b.a.a(this, cVar);
    }

    @Override // m00.b
    public m00.c<?> getKey() {
        return this.key;
    }

    @Override // defpackage.m00
    public m00 minusKey(m00.c<?> cVar) {
        return m00.b.a.b(this, cVar);
    }

    @Override // defpackage.m00
    public m00 plus(m00 m00Var) {
        i21.e(m00Var, "context");
        return m00.a.a(this, m00Var);
    }
}
