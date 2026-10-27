package d.a.p.f;

import d.a.p.c.d;
import d.a.p.h.c;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: compiled from: SpscLinkedArrayQueue.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> implements d<T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f3210c = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f3211d = new Object();
    public final int E3;
    public AtomicReferenceArray<Object> F3;
    public final int G3;
    public AtomicReferenceArray<Object> H3;
    public int x;
    public long y;
    public final AtomicLong s = new AtomicLong();
    public final AtomicLong I3 = new AtomicLong();

    public a(int i2) {
        int iA = c.a(Math.max(8, i2));
        int i3 = iA - 1;
        AtomicReferenceArray<Object> atomicReferenceArray = new AtomicReferenceArray<>(iA + 1);
        this.F3 = atomicReferenceArray;
        this.E3 = i3;
        a(iA);
        this.H3 = atomicReferenceArray;
        this.G3 = i3;
        this.y = i3 - 1;
        o(0L);
    }

    public static int b(int i2) {
        return i2;
    }

    public static int c(long j, int i2) {
        return b(((int) j) & i2);
    }

    public static <E> Object g(AtomicReferenceArray<Object> atomicReferenceArray, int i2) {
        return atomicReferenceArray.get(i2);
    }

    public static void m(AtomicReferenceArray<Object> atomicReferenceArray, int i2, Object obj) {
        atomicReferenceArray.lazySet(i2, obj);
    }

    public final void a(int i2) {
        this.x = Math.min(i2 / 4, f3210c);
    }

    @Override // d.a.p.c.e
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    public final long d() {
        return this.I3.get();
    }

    public final long e() {
        return this.s.get();
    }

    public final long f() {
        return this.I3.get();
    }

    public final AtomicReferenceArray<Object> h(AtomicReferenceArray<Object> atomicReferenceArray) {
        return (AtomicReferenceArray) g(atomicReferenceArray, b(atomicReferenceArray.length() - 1));
    }

    public final long i() {
        return this.s.get();
    }

    @Override // d.a.p.c.e
    public boolean isEmpty() {
        return i() == f();
    }

    public final T j(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i2) {
        this.H3 = atomicReferenceArray;
        int iC = c(j, i2);
        T t = (T) g(atomicReferenceArray, iC);
        if (t != null) {
            m(atomicReferenceArray, iC, null);
            l(j + 1);
        }
        return t;
    }

    public final void k(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i2, T t, long j2) {
        AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(atomicReferenceArray.length());
        this.F3 = atomicReferenceArray2;
        this.y = (j2 + j) - 1;
        m(atomicReferenceArray2, i2, t);
        n(atomicReferenceArray, atomicReferenceArray2);
        m(atomicReferenceArray, i2, f3211d);
        o(j + 1);
    }

    public final void l(long j) {
        this.I3.lazySet(j);
    }

    public final void n(AtomicReferenceArray<Object> atomicReferenceArray, AtomicReferenceArray<Object> atomicReferenceArray2) {
        m(atomicReferenceArray, b(atomicReferenceArray.length() - 1), atomicReferenceArray2);
    }

    public final void o(long j) {
        this.s.lazySet(j);
    }

    @Override // d.a.p.c.e
    public boolean offer(T t) {
        Objects.requireNonNull(t, "Null is not a valid element");
        AtomicReferenceArray<Object> atomicReferenceArray = this.F3;
        long jE = e();
        int i2 = this.E3;
        int iC = c(jE, i2);
        if (jE < this.y) {
            return p(atomicReferenceArray, t, jE, iC);
        }
        long j = ((long) this.x) + jE;
        if (g(atomicReferenceArray, c(j, i2)) == null) {
            this.y = j - 1;
            return p(atomicReferenceArray, t, jE, iC);
        }
        if (g(atomicReferenceArray, c(1 + jE, i2)) == null) {
            return p(atomicReferenceArray, t, jE, iC);
        }
        k(atomicReferenceArray, jE, iC, t, i2);
        return true;
    }

    public final boolean p(AtomicReferenceArray<Object> atomicReferenceArray, T t, long j, int i2) {
        m(atomicReferenceArray, i2, t);
        o(j + 1);
        return true;
    }

    @Override // d.a.p.c.d, d.a.p.c.e
    public T poll() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.H3;
        long jD = d();
        int i2 = this.G3;
        int iC = c(jD, i2);
        T t = (T) g(atomicReferenceArray, iC);
        boolean z = t == f3211d;
        if (t == null || z) {
            if (z) {
                return j(h(atomicReferenceArray), jD, i2);
            }
            return null;
        }
        m(atomicReferenceArray, iC, null);
        l(jD + 1);
        return t;
    }
}
