package p;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class db2 {
    public static final bb2 a = new bb2(new byte[0], 0, 0, false, false);
    public static final int b;
    public static final AtomicReference[] c;

    static {
        int iHighestOneBit = Integer.highestOneBit((Runtime.getRuntime().availableProcessors() * 2) - 1);
        b = iHighestOneBit;
        AtomicReference[] atomicReferenceArr = new AtomicReference[iHighestOneBit];
        for (int i = 0; i < iHighestOneBit; i++) {
            atomicReferenceArr[i] = new AtomicReference();
        }
        c = atomicReferenceArr;
    }

    public static final void a(bb2 bb2Var) {
        if (bb2Var.f != null || bb2Var.g != null) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (bb2Var.d) {
            return;
        }
        AtomicReference atomicReference = c[(int) (Thread.currentThread().getId() & (((long) b) - 1))];
        bb2 bb2Var2 = a;
        bb2 bb2Var3 = (bb2) atomicReference.getAndSet(bb2Var2);
        if (bb2Var3 == bb2Var2) {
            return;
        }
        int i = bb2Var3 != null ? bb2Var3.c : 0;
        if (i >= 65536) {
            atomicReference.set(bb2Var3);
            return;
        }
        bb2Var.f = bb2Var3;
        bb2Var.b = 0;
        bb2Var.c = i + 8192;
        atomicReference.set(bb2Var);
    }

    public static final bb2 b() {
        AtomicReference atomicReference = c[(int) (Thread.currentThread().getId() & (((long) b) - 1))];
        bb2 bb2Var = a;
        bb2 bb2Var2 = (bb2) atomicReference.getAndSet(bb2Var);
        if (bb2Var2 == bb2Var) {
            return new bb2();
        }
        if (bb2Var2 == null) {
            atomicReference.set(null);
            return new bb2();
        }
        atomicReference.set(bb2Var2.f);
        bb2Var2.f = null;
        bb2Var2.c = 0;
        return bb2Var2;
    }
}
