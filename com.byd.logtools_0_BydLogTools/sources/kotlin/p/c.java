package kotlin.p;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.TypeCastException;
import kotlin.jvm.b.l;

/* JADX INFO: compiled from: Sequences.kt */
/* JADX INFO: loaded from: classes.dex */
final class c<T> implements d<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final kotlin.jvm.b.a<T> f5146a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final l<T, T> f5147b;

    /* JADX INFO: compiled from: Sequences.kt */
    public static final class a implements Iterator<T>, kotlin.jvm.internal.n.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private T f5148b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f5149c = -2;

        a() {
        }

        private final void a() {
            T t;
            if (this.f5149c == -2) {
                t = (T) c.this.f5146a.invoke();
            } else {
                l lVar = c.this.f5147b;
                T t2 = this.f5148b;
                if (t2 == null) {
                    kotlin.jvm.internal.i.g();
                }
                t = (T) lVar.invoke(t2);
            }
            this.f5148b = t;
            this.f5149c = t == null ? 0 : 1;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f5149c < 0) {
                a();
            }
            return this.f5149c == 1;
        }

        @Override // java.util.Iterator
        public T next() {
            if (this.f5149c < 0) {
                a();
            }
            if (this.f5149c == 0) {
                throw new NoSuchElementException();
            }
            T t = this.f5148b;
            if (t == null) {
                throw new TypeCastException("null cannot be cast to non-null type T");
            }
            this.f5149c = -1;
            return t;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public c(kotlin.jvm.b.a<? extends T> aVar, l<? super T, ? extends T> lVar) {
        kotlin.jvm.internal.i.c(aVar, "getInitialValue");
        kotlin.jvm.internal.i.c(lVar, "getNextValue");
        this.f5146a = aVar;
        this.f5147b = lVar;
    }

    @Override // kotlin.p.d
    public Iterator<T> iterator() {
        return new a();
    }
}
