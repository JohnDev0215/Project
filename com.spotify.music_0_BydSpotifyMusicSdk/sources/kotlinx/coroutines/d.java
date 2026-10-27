package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import p.fa0;
import p.he1;
import p.o72;
import p.vr2;

/* JADX INFO: loaded from: classes.dex */
public final class d extends o72 {
    public static final AtomicIntegerFieldUpdater m = AtomicIntegerFieldUpdater.newUpdater(d.class, "_decision");
    private volatile int _decision;

    @Override // p.o72, kotlinx.coroutines.g
    public final void r(Object obj) {
        s(obj);
    }

    @Override // p.o72, kotlinx.coroutines.g
    public final void s(Object obj) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        do {
            atomicIntegerFieldUpdater = m;
            int i = atomicIntegerFieldUpdater.get(this);
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("Already resumed".toString());
                }
                fa0.a(vr2.k(this.l), he1.L(obj), null);
                return;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, 0, 2));
    }
}
