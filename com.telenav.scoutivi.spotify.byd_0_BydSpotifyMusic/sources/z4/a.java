package z4;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: MpscLinkedQueue.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AtomicReference<C0181a<T>> f10046a = new AtomicReference<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final AtomicReference<C0181a<T>> f10047b = new AtomicReference<>();

    /* JADX INFO: renamed from: z4.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MpscLinkedQueue.java */
    static final class C0181a<E> extends AtomicReference<C0181a<E>> {
        private static final long serialVersionUID = 2404266111789071508L;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private E f10048e;

        C0181a() {
        }

        public E a() {
            E eB = b();
            e(null);
            return eB;
        }

        public E b() {
            return this.f10048e;
        }

        public C0181a<E> c() {
            return get();
        }

        public void d(C0181a<E> c0181a) {
            lazySet(c0181a);
        }

        public void e(E e7) {
            this.f10048e = e7;
        }

        C0181a(E e7) {
            e(e7);
        }
    }

    public a() {
        C0181a<T> c0181a = new C0181a<>();
        h(c0181a);
        i(c0181a);
    }

    public void a() {
        while (g() != null && !b()) {
        }
    }

    public boolean b() {
        return d() == e();
    }

    C0181a<T> c() {
        return this.f10047b.get();
    }

    C0181a<T> d() {
        return this.f10047b.get();
    }

    C0181a<T> e() {
        return this.f10046a.get();
    }

    public boolean f(T t6) {
        Objects.requireNonNull(t6, "Null is not a valid element");
        C0181a<T> c0181a = new C0181a<>(t6);
        i(c0181a).d(c0181a);
        return true;
    }

    public T g() {
        C0181a<T> c0181aC;
        C0181a<T> c0181aC2 = c();
        C0181a<T> c0181aC3 = c0181aC2.c();
        if (c0181aC3 != null) {
            T tA = c0181aC3.a();
            h(c0181aC3);
            return tA;
        }
        if (c0181aC2 == e()) {
            return null;
        }
        do {
            c0181aC = c0181aC2.c();
        } while (c0181aC == null);
        T tA2 = c0181aC.a();
        h(c0181aC);
        return tA2;
    }

    void h(C0181a<T> c0181a) {
        this.f10047b.lazySet(c0181a);
    }

    C0181a<T> i(C0181a<T> c0181a) {
        return this.f10046a.getAndSet(c0181a);
    }
}
