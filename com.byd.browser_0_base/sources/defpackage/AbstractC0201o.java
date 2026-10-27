package defpackage;

import defpackage.m00;
import m00.b;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes.dex
 */
/* JADX INFO: renamed from: o, reason: case insensitive filesystem */
/* JADX INFO: compiled from: CoroutineContextImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0201o<B extends m00.b, E extends B> implements m00.c<E> {
    public final jr0<m00.b, E> e;
    public final m00.c<?> f;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [m00$c<?>] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r3v0, types: [jr0<? super m00$b, ? extends E extends B>, jr0<m00$b, E extends B>] */
    public AbstractC0201o(m00.c<B> cVar, jr0<? super m00.b, ? extends E> jr0Var) {
        i21.e(cVar, "baseKey");
        this.e = jr0Var;
        this.f = cVar instanceof AbstractC0201o ? (m00.c<B>) ((AbstractC0201o) cVar).f : cVar;
    }

    /* JADX WARN: Incorrect return type in method signature: (Lm00$b;)TE; */
    public final m00.b a(m00.b bVar) {
        i21.e(bVar, "element");
        return (m00.b) this.e.c(bVar);
    }
}
