package p;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class cb2 extends pw implements un1 {
    public static final AtomicIntegerFieldUpdater l = AtomicIntegerFieldUpdater.newUpdater(cb2.class, "cleanedAndPointers");
    private volatile int cleanedAndPointers;
    public final long k;

    public cb2(long j, cb2 cb2Var, int i) {
        super(cb2Var);
        this.k = j;
        this.cleanedAndPointers = i << 16;
    }

    @Override // p.pw
    public final boolean c() {
        return l.get(this) == f() && b() != null;
    }

    public final boolean e() {
        return l.addAndGet(this, -65536) == f() && b() != null;
    }

    public abstract int f();

    public abstract void g(int i, y10 y10Var);

    public final void h() {
        if (l.incrementAndGet(this) == f()) {
            d();
        }
    }

    public final boolean i() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i;
        do {
            atomicIntegerFieldUpdater = l;
            i = atomicIntegerFieldUpdater.get(this);
            if (i == f() && b() != null) {
                return false;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i, 65536 + i));
        return true;
    }
}
