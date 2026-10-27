package kotlinx.coroutines.flow.internal;

import k5.g;
import r5.p;

/* JADX INFO: compiled from: SafeCollector.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DownstreamExceptionElement implements g.b {
    public static final Key Key = new Key(null);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Throwable f7611e;
    private final g.c<?> key = Key;

    /* JADX INFO: compiled from: SafeCollector.kt */
    public static final class Key implements g.c<DownstreamExceptionElement> {
        private Key() {
        }

        public /* synthetic */ Key(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    public DownstreamExceptionElement(Throwable th) {
        this.f7611e = th;
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
