package y4;

import java.util.concurrent.atomic.AtomicReference;
import q4.g;
import q4.h;

/* JADX INFO: compiled from: SingleCreate.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> extends q4.e<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final h<T> f9967a;

    /* JADX INFO: renamed from: y4.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SingleCreate.java */
    static final class C0179a<T> extends AtomicReference<t4.b> implements q4.f<T>, t4.b {
        private static final long serialVersionUID = -2467358622224974244L;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final g<? super T> f9968e;

        C0179a(g<? super T> gVar) {
            this.f9968e = gVar;
        }

        @Override // q4.f
        public void a(Throwable th) {
            if (c(th)) {
                return;
            }
            d5.a.k(th);
        }

        @Override // q4.f
        public void b(T t6) {
            t4.b andSet;
            t4.b bVar = get();
            w4.b bVar2 = w4.b.DISPOSED;
            if (bVar == bVar2 || (andSet = getAndSet(bVar2)) == bVar2) {
                return;
            }
            try {
                if (t6 == null) {
                    this.f9968e.a(new NullPointerException("onSuccess called with null. Null values are generally not allowed in 2.x operators and sources."));
                } else {
                    this.f9968e.b(t6);
                }
                if (andSet != null) {
                    andSet.dispose();
                }
            } catch (Throwable th) {
                if (andSet != null) {
                    andSet.dispose();
                }
                throw th;
            }
        }

        public boolean c(Throwable th) {
            t4.b andSet;
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            t4.b bVar = get();
            w4.b bVar2 = w4.b.DISPOSED;
            if (bVar == bVar2 || (andSet = getAndSet(bVar2)) == bVar2) {
                return false;
            }
            try {
                this.f9968e.a(th);
            } finally {
                if (andSet != null) {
                    andSet.dispose();
                }
            }
        }

        @Override // t4.b
        public void dispose() {
            w4.b.dispose(this);
        }

        @Override // t4.b
        public boolean isDisposed() {
            return w4.b.isDisposed(get());
        }

        @Override // java.util.concurrent.atomic.AtomicReference
        public String toString() {
            return String.format("%s{%s}", C0179a.class.getSimpleName(), super.toString());
        }
    }

    public a(h<T> hVar) {
        this.f9967a = hVar;
    }

    @Override // q4.e
    protected void g(g<? super T> gVar) {
        C0179a c0179a = new C0179a(gVar);
        gVar.c(c0179a);
        try {
            this.f9967a.a(c0179a);
        } catch (Throwable th) {
            u4.b.b(th);
            c0179a.a(th);
        }
    }
}
