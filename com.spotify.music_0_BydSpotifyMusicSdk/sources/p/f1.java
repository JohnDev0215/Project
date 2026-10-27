package p;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class f1 extends zu {
    public final AtomicReferenceFieldUpdater i;
    public final AtomicReferenceFieldUpdater j;
    public final AtomicReferenceFieldUpdater k;
    public final AtomicReferenceFieldUpdater l;
    public final AtomicReferenceFieldUpdater m;

    public f1(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
        this.i = atomicReferenceFieldUpdater;
        this.j = atomicReferenceFieldUpdater2;
        this.k = atomicReferenceFieldUpdater3;
        this.l = atomicReferenceFieldUpdater4;
        this.m = atomicReferenceFieldUpdater5;
    }

    @Override // p.zu
    public final void A0(i1 i1Var, i1 i1Var2) {
        this.j.lazySet(i1Var, i1Var2);
    }

    @Override // p.zu
    public final void B0(i1 i1Var, Thread thread) {
        this.i.lazySet(i1Var, thread);
    }

    @Override // p.zu
    public final boolean m(j1 j1Var, e1 e1Var, e1 e1Var2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.l;
            if (atomicReferenceFieldUpdater.compareAndSet(j1Var, e1Var, e1Var2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(j1Var) == e1Var);
        return false;
    }

    @Override // p.zu
    public final boolean n(j1 j1Var, Object obj, Object obj2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.m;
            if (atomicReferenceFieldUpdater.compareAndSet(j1Var, obj, obj2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(j1Var) == obj);
        return false;
    }

    @Override // p.zu
    public final boolean o(j1 j1Var, i1 i1Var, i1 i1Var2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.k;
            if (atomicReferenceFieldUpdater.compareAndSet(j1Var, i1Var, i1Var2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(j1Var) == i1Var);
        return false;
    }
}
