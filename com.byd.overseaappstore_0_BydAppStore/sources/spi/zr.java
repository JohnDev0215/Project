package spi;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: loaded from: classes2.dex */
public final class zr<T> implements kp {
    public static final int i = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
    public static final Object j = new Object();
    public int b;
    public long c;
    public final int d;
    public AtomicReferenceArray<Object> e;
    public final int f;
    public AtomicReferenceArray<Object> g;
    public final AtomicLong a = new AtomicLong();
    public final AtomicLong h = new AtomicLong();

    public zr(int i2) {
        int iNumberOfLeadingZeros = 1 << (32 - Integer.numberOfLeadingZeros(Math.max(8, i2) - 1));
        int i3 = iNumberOfLeadingZeros - 1;
        AtomicReferenceArray<Object> atomicReferenceArray = new AtomicReferenceArray<>(iNumberOfLeadingZeros + 1);
        this.e = atomicReferenceArray;
        this.d = i3;
        this.b = Math.min(iNumberOfLeadingZeros / 4, i);
        this.g = atomicReferenceArray;
        this.f = i3;
        this.c = i3 - 1;
        c(0L);
    }

    public final boolean a() {
        return this.a.get() == this.h.get();
    }

    public final T b() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.g;
        long j2 = this.h.get();
        int i2 = this.f;
        int i3 = ((int) j2) & i2;
        T t = (T) atomicReferenceArray.get(i3);
        boolean z = t == j;
        if (t != null && !z) {
            atomicReferenceArray.lazySet(i3, null);
            this.h.lazySet(j2 + 1);
            return t;
        }
        if (!z) {
            return null;
        }
        int i4 = i2 + 1;
        AtomicReferenceArray<Object> atomicReferenceArray2 = (AtomicReferenceArray) atomicReferenceArray.get(i4);
        atomicReferenceArray.lazySet(i4, null);
        this.g = atomicReferenceArray2;
        T t2 = (T) atomicReferenceArray2.get(i3);
        if (t2 != null) {
            atomicReferenceArray2.lazySet(i3, null);
            this.h.lazySet(j2 + 1);
        }
        return t2;
    }

    public final void c(long j2) {
        this.a.lazySet(j2);
    }

    @Override // spi.kp
    public final void clear() {
        while (true) {
            if (b() == null && a()) {
                return;
            }
        }
    }

    /* JADX WARN: Incorrect return type in method signature: (Ljava/util/concurrent/atomic/AtomicReferenceArray<Ljava/lang/Object;>;TT;JI)Z */
    public final void d(AtomicReferenceArray atomicReferenceArray, Object obj, long j2, int i2) {
        atomicReferenceArray.lazySet(i2, obj);
        c(j2 + 1);
    }

    @Override // spi.kp
    public final boolean offer(T t) {
        Objects.requireNonNull(t, "Null is not a valid element");
        AtomicReferenceArray<Object> atomicReferenceArray = this.e;
        long j2 = this.a.get();
        int i2 = this.d;
        int i3 = ((int) j2) & i2;
        if (j2 >= this.c) {
            long j3 = ((long) this.b) + j2;
            if (atomicReferenceArray.get(((int) j3) & i2) == null) {
                this.c = j3 - 1;
            } else {
                long j4 = j2 + 1;
                if (atomicReferenceArray.get(((int) j4) & i2) != null) {
                    AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(atomicReferenceArray.length());
                    this.e = atomicReferenceArray2;
                    this.c = (((long) i2) + j2) - 1;
                    atomicReferenceArray2.lazySet(i3, t);
                    atomicReferenceArray.lazySet(atomicReferenceArray.length() - 1, atomicReferenceArray2);
                    atomicReferenceArray.lazySet(i3, j);
                    c(j4);
                    return true;
                }
            }
        }
        d(atomicReferenceArray, t, j2, i3);
        return true;
    }
}
