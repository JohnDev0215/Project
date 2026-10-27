package k5;

import k5.g;
import k5.g.b;
import r5.l;

/* JADX INFO: compiled from: CoroutineContextImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class b<B extends g.b, E extends B> implements g.c<E> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final l<g.b, E> f7264a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final g.c<?> f7265b;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [k5.g$c<?>] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.Object, r5.l<? super k5.g$b, ? extends E extends B>, r5.l<k5.g$b, E extends B>] */
    public b(g.c<B> baseKey, l<? super g.b, ? extends E> safeCast) {
        kotlin.jvm.internal.l.e(baseKey, "baseKey");
        kotlin.jvm.internal.l.e(safeCast, "safeCast");
        this.f7264a = safeCast;
        this.f7265b = baseKey instanceof b ? (g.c<B>) ((b) baseKey).f7265b : baseKey;
    }

    public final boolean a(g.c<?> key) {
        kotlin.jvm.internal.l.e(key, "key");
        return key == this || this.f7265b == key;
    }

    /* JADX WARN: Incorrect return type in method signature: (Lk5/g$b;)TE; */
    public final g.b b(g.b element) {
        kotlin.jvm.internal.l.e(element, "element");
        return (g.b) this.f7264a.invoke(element);
    }
}
