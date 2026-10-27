package e.a.n.f;

import e.a.n.c.c;
import e.a.n.h.d;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: compiled from: SpscLinkedArrayQueue.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> implements c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final int f4897b = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Object f4898c = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f4900e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    long f4901f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final int f4902g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    AtomicReferenceArray<Object> f4903h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final int f4904i;
    AtomicReferenceArray<Object> j;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final AtomicLong f4899d = new AtomicLong();
    final AtomicLong k = new AtomicLong();

    public a(int i2) {
        int iA = d.a(Math.max(8, i2));
        int i3 = iA - 1;
        AtomicReferenceArray<Object> atomicReferenceArray = new AtomicReferenceArray<>(iA + 1);
        this.f4903h = atomicReferenceArray;
        this.f4902g = i3;
        a(iA);
        this.j = atomicReferenceArray;
        this.f4904i = i3;
        this.f4901f = i3 - 1;
        r(0L);
    }

    private void a(int i2) {
        this.f4900e = Math.min(i2 / 4, f4897b);
    }

    private static int c(int i2) {
        return i2;
    }

    private static int d(long j, int i2) {
        return c(((int) j) & i2);
    }

    private long f() {
        return this.k.get();
    }

    private long g() {
        return this.f4899d.get();
    }

    private long h() {
        return this.k.get();
    }

    private static <E> Object i(AtomicReferenceArray<Object> atomicReferenceArray, int i2) {
        return atomicReferenceArray.get(i2);
    }

    private AtomicReferenceArray<Object> k(AtomicReferenceArray<Object> atomicReferenceArray, int i2) {
        int iC = c(i2);
        AtomicReferenceArray<Object> atomicReferenceArray2 = (AtomicReferenceArray) i(atomicReferenceArray, iC);
        p(atomicReferenceArray, iC, null);
        return atomicReferenceArray2;
    }

    private long l() {
        return this.f4899d.get();
    }

    private T m(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i2) {
        this.j = atomicReferenceArray;
        int iD = d(j, i2);
        T t = (T) i(atomicReferenceArray, iD);
        if (t != null) {
            p(atomicReferenceArray, iD, null);
            o(j + 1);
        }
        return t;
    }

    private void n(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i2, T t, long j2) {
        AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(atomicReferenceArray.length());
        this.f4903h = atomicReferenceArray2;
        this.f4901f = (j2 + j) - 1;
        p(atomicReferenceArray2, i2, t);
        q(atomicReferenceArray, atomicReferenceArray2);
        p(atomicReferenceArray, i2, f4898c);
        r(j + 1);
    }

    private void o(long j) {
        this.k.lazySet(j);
    }

    private static void p(AtomicReferenceArray<Object> atomicReferenceArray, int i2, Object obj) {
        atomicReferenceArray.lazySet(i2, obj);
    }

    private void q(AtomicReferenceArray<Object> atomicReferenceArray, AtomicReferenceArray<Object> atomicReferenceArray2) {
        p(atomicReferenceArray, c(atomicReferenceArray.length() - 1), atomicReferenceArray2);
    }

    private void r(long j) {
        this.f4899d.lazySet(j);
    }

    private boolean s(AtomicReferenceArray<Object> atomicReferenceArray, T t, long j, int i2) {
        p(atomicReferenceArray, i2, t);
        r(j + 1);
        return true;
    }

    @Override // e.a.n.c.c
    public T b() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.j;
        long jF = f();
        int i2 = this.f4904i;
        int iD = d(jF, i2);
        T t = (T) i(atomicReferenceArray, iD);
        boolean z = t == f4898c;
        if (t == null || z) {
            if (z) {
                return m(k(atomicReferenceArray, i2 + 1), jF, i2);
            }
            return null;
        }
        p(atomicReferenceArray, iD, null);
        o(jF + 1);
        return t;
    }

    @Override // e.a.n.c.c
    public void clear() {
        while (true) {
            if (b() == null && isEmpty()) {
                return;
            }
        }
    }

    @Override // e.a.n.c.c
    public boolean e(T t) {
        Objects.requireNonNull(t, "Null is not a valid element");
        AtomicReferenceArray<Object> atomicReferenceArray = this.f4903h;
        long jG = g();
        int i2 = this.f4902g;
        int iD = d(jG, i2);
        if (jG < this.f4901f) {
            return s(atomicReferenceArray, t, jG, iD);
        }
        long j = ((long) this.f4900e) + jG;
        if (i(atomicReferenceArray, d(j, i2)) == null) {
            this.f4901f = j - 1;
            return s(atomicReferenceArray, t, jG, iD);
        }
        if (i(atomicReferenceArray, d(1 + jG, i2)) == null) {
            return s(atomicReferenceArray, t, jG, iD);
        }
        n(atomicReferenceArray, jG, iD, t, i2);
        return true;
    }

    @Override // e.a.n.c.c
    public boolean isEmpty() {
        return l() == h();
    }
}
