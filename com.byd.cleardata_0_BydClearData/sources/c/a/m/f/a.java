package c.a.m.f;

import c.a.m.c.c;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: loaded from: classes.dex */
public final class a<T> implements c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final int f1850b = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Object f1851c = new Object();
    int e;
    long f;
    final int g;
    AtomicReferenceArray<Object> h;
    final int i;
    AtomicReferenceArray<Object> j;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final AtomicLong f1852d = new AtomicLong();
    final AtomicLong k = new AtomicLong();

    public a(int i) {
        int iA = c.a.m.h.c.a(Math.max(8, i));
        int i2 = iA - 1;
        AtomicReferenceArray<Object> atomicReferenceArray = new AtomicReferenceArray<>(iA + 1);
        this.h = atomicReferenceArray;
        this.g = i2;
        a(iA);
        this.j = atomicReferenceArray;
        this.i = i2;
        this.f = i2 - 1;
        r(0L);
    }

    private void a(int i) {
        this.e = Math.min(i / 4, f1850b);
    }

    private static int b(int i) {
        return i;
    }

    private static int c(long j, int i) {
        return b(((int) j) & i);
    }

    private long e() {
        return this.k.get();
    }

    private long g() {
        return this.f1852d.get();
    }

    private long h() {
        return this.k.get();
    }

    private static <E> Object j(AtomicReferenceArray<Object> atomicReferenceArray, int i) {
        return atomicReferenceArray.get(i);
    }

    private AtomicReferenceArray<Object> k(AtomicReferenceArray<Object> atomicReferenceArray, int i) {
        int iB = b(i);
        AtomicReferenceArray<Object> atomicReferenceArray2 = (AtomicReferenceArray) j(atomicReferenceArray, iB);
        p(atomicReferenceArray, iB, null);
        return atomicReferenceArray2;
    }

    private long l() {
        return this.f1852d.get();
    }

    private T m(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i) {
        this.j = atomicReferenceArray;
        int iC = c(j, i);
        T t = (T) j(atomicReferenceArray, iC);
        if (t != null) {
            p(atomicReferenceArray, iC, null);
            o(j + 1);
        }
        return t;
    }

    private void n(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i, T t, long j2) {
        AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(atomicReferenceArray.length());
        this.h = atomicReferenceArray2;
        this.f = (j2 + j) - 1;
        p(atomicReferenceArray2, i, t);
        q(atomicReferenceArray, atomicReferenceArray2);
        p(atomicReferenceArray, i, f1851c);
        r(j + 1);
    }

    private void o(long j) {
        this.k.lazySet(j);
    }

    private static void p(AtomicReferenceArray<Object> atomicReferenceArray, int i, Object obj) {
        atomicReferenceArray.lazySet(i, obj);
    }

    private void q(AtomicReferenceArray<Object> atomicReferenceArray, AtomicReferenceArray<Object> atomicReferenceArray2) {
        p(atomicReferenceArray, b(atomicReferenceArray.length() - 1), atomicReferenceArray2);
    }

    private void r(long j) {
        this.f1852d.lazySet(j);
    }

    private boolean s(AtomicReferenceArray<Object> atomicReferenceArray, T t, long j, int i) {
        p(atomicReferenceArray, i, t);
        r(j + 1);
        return true;
    }

    @Override // c.a.m.c.c
    public void clear() {
        while (true) {
            if (d() == null && isEmpty()) {
                return;
            }
        }
    }

    @Override // c.a.m.c.c
    public T d() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.j;
        long jE = e();
        int i = this.i;
        int iC = c(jE, i);
        T t = (T) j(atomicReferenceArray, iC);
        boolean z = t == f1851c;
        if (t == null || z) {
            if (z) {
                return m(k(atomicReferenceArray, i + 1), jE, i);
            }
            return null;
        }
        p(atomicReferenceArray, iC, null);
        o(jE + 1);
        return t;
    }

    @Override // c.a.m.c.c
    public boolean f(T t) {
        Objects.requireNonNull(t, "Null is not a valid element");
        AtomicReferenceArray<Object> atomicReferenceArray = this.h;
        long jG = g();
        int i = this.g;
        int iC = c(jG, i);
        if (jG < this.f) {
            return s(atomicReferenceArray, t, jG, iC);
        }
        long j = ((long) this.e) + jG;
        if (j(atomicReferenceArray, c(j, i)) == null) {
            this.f = j - 1;
            return s(atomicReferenceArray, t, jG, iC);
        }
        if (j(atomicReferenceArray, c(1 + jG, i)) == null) {
            return s(atomicReferenceArray, t, jG, iC);
        }
        n(atomicReferenceArray, jG, iC, t, i);
        return true;
    }

    @Override // c.a.m.c.c
    public boolean isEmpty() {
        return l() == h();
    }
}
