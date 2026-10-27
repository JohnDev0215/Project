package x5;

import java.util.Iterator;

/* JADX INFO: compiled from: Sequences.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b<T> implements d<T>, c<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d<T> f9854a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f9855b;

    /* JADX INFO: compiled from: Sequences.kt */
    public static final class a implements Iterator<T>, s5.a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Iterator<T> f9856e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f9857g;

        a(b<T> bVar) {
            this.f9856e = ((b) bVar).f9854a.iterator();
            this.f9857g = ((b) bVar).f9855b;
        }

        private final void a() {
            while (this.f9857g > 0 && this.f9856e.hasNext()) {
                this.f9856e.next();
                this.f9857g--;
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            a();
            return this.f9856e.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            a();
            return this.f9856e.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public b(d<? extends T> sequence, int i7) {
        kotlin.jvm.internal.l.e(sequence, "sequence");
        this.f9854a = sequence;
        this.f9855b = i7;
        if (i7 >= 0) {
            return;
        }
        throw new IllegalArgumentException(("count must be non-negative, but was " + i7 + '.').toString());
    }

    @Override // x5.c
    public d<T> a(int i7) {
        int i8 = this.f9855b + i7;
        return i8 < 0 ? new b(this, i7) : new b(this.f9854a, i8);
    }

    @Override // x5.d
    public Iterator<T> iterator() {
        return new a(this);
    }
}
