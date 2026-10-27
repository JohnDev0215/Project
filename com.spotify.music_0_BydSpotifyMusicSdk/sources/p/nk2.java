package p;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class nk2 implements ry0 {
    public static final AtomicIntegerFieldUpdater k = AtomicIntegerFieldUpdater.newUpdater(nk2.class, "_state");
    private volatile int _state;
    public final Thread c = Thread.currentThread();
    public oa0 e;

    public nk2(p61 p61Var) {
    }

    public static void b(int i) {
        throw new IllegalStateException(("Illegal state " + i).toString());
    }

    public final void a() {
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = k;
            int i = atomicIntegerFieldUpdater.get(this);
            if (i != 0) {
                if (i != 2) {
                    if (i == 3) {
                        Thread.interrupted();
                        return;
                    } else {
                        b(i);
                        throw null;
                    }
                }
            } else if (atomicIntegerFieldUpdater.compareAndSet(this, i, 1)) {
                oa0 oa0Var = this.e;
                if (oa0Var != null) {
                    oa0Var.dispose();
                    return;
                }
                return;
            }
        }
    }

    @Override // p.ry0
    public final Object invoke(Object obj) {
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = k;
            int i = atomicIntegerFieldUpdater.get(this);
            if (i != 0) {
                if (i != 1 && i != 2 && i != 3) {
                    b(i);
                    throw null;
                }
            } else if (atomicIntegerFieldUpdater.compareAndSet(this, i, 2)) {
                this.c.interrupt();
                atomicIntegerFieldUpdater.set(this, 3);
                break;
            }
        }
        return lq2.a;
    }
}
