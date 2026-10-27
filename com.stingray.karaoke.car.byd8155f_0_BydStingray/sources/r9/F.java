package r9;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class F {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final E f21904a = new E(new byte[0], 0, 0, false, false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f21905b;
    public static final AtomicReference[] c;

    static {
        int iHighestOneBit = Integer.highestOneBit((Runtime.getRuntime().availableProcessors() * 2) - 1);
        f21905b = iHighestOneBit;
        AtomicReference[] atomicReferenceArr = new AtomicReference[iHighestOneBit];
        for (int i7 = 0; i7 < iHighestOneBit; i7++) {
            atomicReferenceArr[i7] = new AtomicReference();
        }
        c = atomicReferenceArr;
    }

    public static final void a(E e10) {
        A7.m.f(e10, "segment");
        if (e10.f21902f != null || e10.f21903g != null) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (e10.d) {
            return;
        }
        AtomicReference atomicReference = c[(int) (Thread.currentThread().getId() & (((long) f21905b) - 1))];
        E e11 = f21904a;
        E e12 = (E) atomicReference.getAndSet(e11);
        if (e12 == e11) {
            return;
        }
        int i7 = e12 != null ? e12.c : 0;
        if (i7 >= 65536) {
            atomicReference.set(e12);
            return;
        }
        e10.f21902f = e12;
        e10.f21900b = 0;
        e10.c = i7 + 8192;
        atomicReference.set(e10);
    }

    public static final E b() {
        AtomicReference atomicReference = c[(int) (Thread.currentThread().getId() & (((long) f21905b) - 1))];
        E e10 = f21904a;
        E e11 = (E) atomicReference.getAndSet(e10);
        if (e11 == e10) {
            return new E();
        }
        if (e11 == null) {
            atomicReference.set(null);
            return new E();
        }
        atomicReference.set(e11.f21902f);
        e11.f21902f = null;
        e11.c = 0;
        return e11;
    }
}
