package k5;

import k5.g;
import kotlin.jvm.internal.l;
import r5.p;

/* JADX INFO: compiled from: CoroutineContextImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements g.b {
    private final g.c<?> key;

    public a(g.c<?> key) {
        l.e(key, "key");
        this.key = key;
    }

    @Override // k5.g
    public <R> R fold(R r7, p<? super R, ? super g.b, ? extends R> pVar) {
        return (R) g.b.a.a(this, r7, pVar);
    }

    @Override // k5.g.b, k5.g
    public <E extends g.b> E get(g.c<E> cVar) {
        return (E) g.b.a.b(this, cVar);
    }

    @Override // k5.g.b
    public g.c<?> getKey() {
        return this.key;
    }

    @Override // k5.g
    public g minusKey(g.c<?> cVar) {
        return g.b.a.c(this, cVar);
    }

    @Override // k5.g
    public g plus(g gVar) {
        return g.b.a.d(this, gVar);
    }
}
