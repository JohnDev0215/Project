package io.reactivex.internal.queue;

import e.a.n.b.d;
import io.reactivex.internal.util.b;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: compiled from: SpscLinkedArrayQueue.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> implements d<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final int f5015b = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Object f5016c = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f5018e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    long f5019f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final int f5020g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    AtomicReferenceArray<Object> f5021h;
    final int i;
    AtomicReferenceArray<Object> j;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final AtomicLong f5017d = new AtomicLong();
    final AtomicLong k = new AtomicLong();

    public a(int i) {
        int iA = b.a(Math.max(8, i));
        int i2 = iA - 1;
        AtomicReferenceArray<Object> atomicReferenceArray = new AtomicReferenceArray<>(iA + 1);
        this.f5021h = atomicReferenceArray;
        this.f5020g = i2;
        a(iA);
        this.j = atomicReferenceArray;
        this.i = i2;
        this.f5019f = i2 - 1;
        o(0L);
    }

    private void a(int i) {
        this.f5018e = Math.min(i / 4, f5015b);
    }

    private static int b(int i) {
        return i;
    }

    private static int c(long j, int i) {
        return b(((int) j) & i);
    }

    private long d() {
        return this.k.get();
    }

    private long e() {
        return this.f5017d.get();
    }

    private long f() {
        return this.k.get();
    }

    private static <E> Object g(AtomicReferenceArray<Object> atomicReferenceArray, int i) {
        return atomicReferenceArray.get(i);
    }

    private AtomicReferenceArray<Object> h(AtomicReferenceArray<Object> atomicReferenceArray, int i) {
        int iB = b(i);
        AtomicReferenceArray<Object> atomicReferenceArray2 = (AtomicReferenceArray) g(atomicReferenceArray, iB);
        m(atomicReferenceArray, iB, null);
        return atomicReferenceArray2;
    }

    private long i() {
        return this.f5017d.get();
    }

    private T j(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i) {
        this.j = atomicReferenceArray;
        int iC = c(j, i);
        T t = (T) g(atomicReferenceArray, iC);
        if (t != null) {
            m(atomicReferenceArray, iC, null);
            l(j + 1);
        }
        return t;
    }

    private void k(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i, T t, long j2) {
        AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(atomicReferenceArray.length());
        this.f5021h = atomicReferenceArray2;
        this.f5019f = (j2 + j) - 1;
        m(atomicReferenceArray2, i, t);
        n(atomicReferenceArray, atomicReferenceArray2);
        m(atomicReferenceArray, i, f5016c);
        o(j + 1);
    }

    private void l(long j) {
        this.k.lazySet(j);
    }

    private static void m(AtomicReferenceArray<Object> atomicReferenceArray, int i, Object obj) {
        atomicReferenceArray.lazySet(i, obj);
    }

    private void n(AtomicReferenceArray<Object> atomicReferenceArray, AtomicReferenceArray<Object> atomicReferenceArray2) {
        m(atomicReferenceArray, b(atomicReferenceArray.length() - 1), atomicReferenceArray2);
    }

    private void o(long j) {
        this.f5017d.lazySet(j);
    }

    private boolean p(AtomicReferenceArray<Object> atomicReferenceArray, T t, long j, int i) {
        m(atomicReferenceArray, i, t);
        o(j + 1);
        return true;
    }

    @Override // e.a.n.b.e
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    @Override // e.a.n.b.e
    public boolean isEmpty() {
        return i() == f();
    }

    @Override // e.a.n.b.e
    public boolean offer(T t) {
        Objects.requireNonNull(t, "Null is not a valid element");
        AtomicReferenceArray<Object> atomicReferenceArray = this.f5021h;
        long jE = e();
        int i = this.f5020g;
        int iC = c(jE, i);
        if (jE < this.f5019f) {
            return p(atomicReferenceArray, t, jE, iC);
        }
        long j = ((long) this.f5018e) + jE;
        if (g(atomicReferenceArray, c(j, i)) == null) {
            this.f5019f = j - 1;
            return p(atomicReferenceArray, t, jE, iC);
        }
        if (g(atomicReferenceArray, c(1 + jE, i)) == null) {
            return p(atomicReferenceArray, t, jE, iC);
        }
        k(atomicReferenceArray, jE, iC, t, i);
        return true;
    }

    @Override // e.a.n.b.d, e.a.n.b.e
    public T poll() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.j;
        long jD = d();
        int i = this.i;
        int iC = c(jD, i);
        T t = (T) g(atomicReferenceArray, iC);
        boolean z = t == f5016c;
        if (t == null || z) {
            if (z) {
                return j(h(atomicReferenceArray, i + 1), jD, i);
            }
            return null;
        }
        m(atomicReferenceArray, iC, null);
        l(jD + 1);
        return t;
    }
}
