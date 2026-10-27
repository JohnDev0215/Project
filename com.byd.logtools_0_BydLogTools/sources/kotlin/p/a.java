package kotlin.p;

import java.util.Iterator;

/* JADX INFO: compiled from: Sequences.kt */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> implements d<T>, b<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d<T> f5141a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f5142b;

    /* JADX INFO: renamed from: kotlin.p.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Sequences.kt */
    public static final class C0096a implements Iterator<T>, kotlin.jvm.internal.n.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Iterator<T> f5143b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f5144c;

        C0096a() {
            this.f5143b = a.this.f5141a.iterator();
            this.f5144c = a.this.f5142b;
        }

        private final void a() {
            while (this.f5144c > 0 && this.f5143b.hasNext()) {
                this.f5143b.next();
                this.f5144c--;
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            a();
            return this.f5143b.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            a();
            return this.f5143b.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public a(d<? extends T> dVar, int i) {
        kotlin.jvm.internal.i.c(dVar, "sequence");
        this.f5141a = dVar;
        this.f5142b = i;
        if (i >= 0) {
            return;
        }
        throw new IllegalArgumentException(("count must be non-negative, but was " + i + '.').toString());
    }

    @Override // kotlin.p.b
    public d<T> a(int i) {
        int i2 = this.f5142b + i;
        return i2 < 0 ? new a(this, i) : new a(this.f5141a, i2);
    }

    @Override // kotlin.p.d
    public Iterator<T> iterator() {
        return new C0096a();
    }
}
