package p;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class d2 extends q30 {
    public final AtomicReferenceFieldUpdater i;
    public final AtomicReferenceFieldUpdater j;
    public final AtomicReferenceFieldUpdater k;
    public final AtomicReferenceFieldUpdater l;
    public final AtomicReferenceFieldUpdater m;

    public d2(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
        this.i = atomicReferenceFieldUpdater;
        this.j = atomicReferenceFieldUpdater2;
        this.k = atomicReferenceFieldUpdater3;
        this.l = atomicReferenceFieldUpdater4;
        this.m = atomicReferenceFieldUpdater5;
    }

    @Override // p.q30
    public final boolean f(g2 g2Var, c2 c2Var, c2 c2Var2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.l;
            if (atomicReferenceFieldUpdater.compareAndSet(g2Var, c2Var, c2Var2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(g2Var) == c2Var);
        return false;
    }

    @Override // p.q30
    public final boolean g(g2 g2Var, Object obj, Object obj2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.m;
            if (atomicReferenceFieldUpdater.compareAndSet(g2Var, obj, obj2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(g2Var) == obj);
        return false;
    }

    @Override // p.q30
    public final boolean h(g2 g2Var, f2 f2Var, f2 f2Var2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.k;
            if (atomicReferenceFieldUpdater.compareAndSet(g2Var, f2Var, f2Var2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(g2Var) == f2Var);
        return false;
    }

    @Override // p.q30
    public final void p(f2 f2Var, f2 f2Var2) {
        this.j.lazySet(f2Var, f2Var2);
    }

    @Override // p.q30
    public final void q(f2 f2Var, Thread thread) {
        this.i.lazySet(f2Var, thread);
    }
}
