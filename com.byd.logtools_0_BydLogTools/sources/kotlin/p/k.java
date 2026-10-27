package kotlin.p;

import java.util.Iterator;
import kotlin.jvm.b.l;

/* JADX INFO: compiled from: Sequences.kt */
/* JADX INFO: loaded from: classes.dex */
public final class k<T, R> implements d<R> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d<T> f5152a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final l<T, R> f5153b;

    /* JADX INFO: compiled from: Sequences.kt */
    public static final class a implements Iterator<R>, kotlin.jvm.internal.n.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Iterator<T> f5154b;

        a() {
            this.f5154b = k.this.f5152a.iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f5154b.hasNext();
        }

        @Override // java.util.Iterator
        public R next() {
            return (R) k.this.f5153b.invoke(this.f5154b.next());
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public k(d<? extends T> dVar, l<? super T, ? extends R> lVar) {
        kotlin.jvm.internal.i.c(dVar, "sequence");
        kotlin.jvm.internal.i.c(lVar, "transformer");
        this.f5152a = dVar;
        this.f5153b = lVar;
    }

    @Override // kotlin.p.d
    public Iterator<R> iterator() {
        return new a();
    }
}
