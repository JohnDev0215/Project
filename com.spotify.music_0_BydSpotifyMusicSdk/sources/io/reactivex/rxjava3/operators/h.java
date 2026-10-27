package io.reactivex.rxjava3.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: loaded from: classes.dex */
public final class h implements f {
    public static final int q = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
    public static final Object r = new Object();
    public final AtomicLong c;
    public final int e;
    public long k;
    public final int l;
    public AtomicReferenceArray m;
    public final int n;
    public AtomicReferenceArray o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final AtomicLong f83p;

    public h(int i) {
        AtomicLong atomicLong = new AtomicLong();
        this.c = atomicLong;
        this.f83p = new AtomicLong();
        int iNumberOfLeadingZeros = 1 << (32 - Integer.numberOfLeadingZeros(Math.max(8, i) - 1));
        int i2 = iNumberOfLeadingZeros - 1;
        AtomicReferenceArray atomicReferenceArray = new AtomicReferenceArray(iNumberOfLeadingZeros + 1);
        this.m = atomicReferenceArray;
        this.l = i2;
        this.e = Math.min(iNumberOfLeadingZeros / 4, q);
        this.o = atomicReferenceArray;
        this.n = i2;
        this.k = iNumberOfLeadingZeros - 2;
        atomicLong.lazySet(0L);
    }

    public final void a(Number number, Object obj) {
        AtomicReferenceArray atomicReferenceArray = this.m;
        AtomicLong atomicLong = this.c;
        long j = atomicLong.get();
        long j2 = 2 + j;
        int i = this.l;
        if (atomicReferenceArray.get(((int) j2) & i) == null) {
            int i2 = ((int) j) & i;
            atomicReferenceArray.lazySet(i2 + 1, obj);
            atomicReferenceArray.lazySet(i2, number);
            atomicLong.lazySet(j2);
            return;
        }
        AtomicReferenceArray atomicReferenceArray2 = new AtomicReferenceArray(atomicReferenceArray.length());
        this.m = atomicReferenceArray2;
        int i3 = ((int) j) & i;
        atomicReferenceArray2.lazySet(i3 + 1, obj);
        atomicReferenceArray2.lazySet(i3, number);
        atomicReferenceArray.lazySet(atomicReferenceArray.length() - 1, atomicReferenceArray2);
        atomicReferenceArray.lazySet(i3, r);
        atomicLong.lazySet(j2);
    }

    public final Object c() {
        AtomicReferenceArray atomicReferenceArray = this.o;
        int i = (int) this.f83p.get();
        int i2 = this.n;
        int i3 = i & i2;
        Object obj = atomicReferenceArray.get(i3);
        if (obj != r) {
            return obj;
        }
        int i4 = i2 + 1;
        AtomicReferenceArray atomicReferenceArray2 = (AtomicReferenceArray) atomicReferenceArray.get(i4);
        atomicReferenceArray.lazySet(i4, null);
        this.o = atomicReferenceArray2;
        return atomicReferenceArray2.get(i3);
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        return this.c.get() == this.f83p.get();
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean offer(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        AtomicReferenceArray atomicReferenceArray = this.m;
        AtomicLong atomicLong = this.c;
        long j = atomicLong.get();
        int i = this.l;
        int i2 = ((int) j) & i;
        if (j < this.k) {
            atomicReferenceArray.lazySet(i2, obj);
            atomicLong.lazySet(j + 1);
            return true;
        }
        long j2 = ((long) this.e) + j;
        if (atomicReferenceArray.get(((int) j2) & i) == null) {
            this.k = j2 - 1;
            atomicReferenceArray.lazySet(i2, obj);
            atomicLong.lazySet(j + 1);
            return true;
        }
        long j3 = j + 1;
        if (atomicReferenceArray.get(((int) j3) & i) == null) {
            atomicReferenceArray.lazySet(i2, obj);
            atomicLong.lazySet(j3);
            return true;
        }
        AtomicReferenceArray atomicReferenceArray2 = new AtomicReferenceArray(atomicReferenceArray.length());
        this.m = atomicReferenceArray2;
        this.k = (j + ((long) i)) - 1;
        atomicReferenceArray2.lazySet(i2, obj);
        atomicReferenceArray.lazySet(atomicReferenceArray.length() - 1, atomicReferenceArray2);
        atomicReferenceArray.lazySet(i2, r);
        atomicLong.lazySet(j3);
        return true;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        AtomicReferenceArray atomicReferenceArray = this.o;
        AtomicLong atomicLong = this.f83p;
        long j = atomicLong.get();
        int i = this.n;
        int i2 = ((int) j) & i;
        Object obj = atomicReferenceArray.get(i2);
        boolean z = obj == r;
        if (obj != null && !z) {
            atomicReferenceArray.lazySet(i2, null);
            atomicLong.lazySet(j + 1);
            return obj;
        }
        if (!z) {
            return null;
        }
        int i3 = i + 1;
        AtomicReferenceArray atomicReferenceArray2 = (AtomicReferenceArray) atomicReferenceArray.get(i3);
        atomicReferenceArray.lazySet(i3, null);
        this.o = atomicReferenceArray2;
        Object obj2 = atomicReferenceArray2.get(i2);
        if (obj2 != null) {
            atomicReferenceArray2.lazySet(i2, null);
            atomicLong.lazySet(j + 1);
        }
        return obj2;
    }
}
