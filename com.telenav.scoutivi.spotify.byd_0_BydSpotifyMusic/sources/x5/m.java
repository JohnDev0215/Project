package x5;

import java.util.Iterator;

/* JADX INFO: compiled from: Sequences.kt */
/* JADX INFO: loaded from: classes.dex */
public final class m<T, R> implements d<R> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d<T> f9865a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final r5.l<T, R> f9866b;

    /* JADX INFO: compiled from: Sequences.kt */
    public static final class a implements Iterator<R>, s5.a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Iterator<T> f9867e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ m<T, R> f9868g;

        a(m<T, R> mVar) {
            this.f9868g = mVar;
            this.f9867e = ((m) mVar).f9865a.iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f9867e.hasNext();
        }

        @Override // java.util.Iterator
        public R next() {
            return (R) ((m) this.f9868g).f9866b.invoke(this.f9867e.next());
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public m(d<? extends T> sequence, r5.l<? super T, ? extends R> transformer) {
        kotlin.jvm.internal.l.e(sequence, "sequence");
        kotlin.jvm.internal.l.e(transformer, "transformer");
        this.f9865a = sequence;
        this.f9866b = transformer;
    }

    @Override // x5.d
    public Iterator<R> iterator() {
        return new a(this);
    }
}
