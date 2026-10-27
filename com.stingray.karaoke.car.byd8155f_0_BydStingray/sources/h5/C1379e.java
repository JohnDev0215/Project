package h5;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: renamed from: h5.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1379e extends l9.d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f15546b;
    public final AtomicReferenceFieldUpdater c;
    public final AtomicReferenceFieldUpdater d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f15547e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f15548f;

    public C1379e(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
        this.f15546b = atomicReferenceFieldUpdater;
        this.c = atomicReferenceFieldUpdater2;
        this.d = atomicReferenceFieldUpdater3;
        this.f15547e = atomicReferenceFieldUpdater4;
        this.f15548f = atomicReferenceFieldUpdater5;
    }

    @Override // l9.d
    public final boolean E(p pVar, C1378d c1378d, C1378d c1378d2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f15547e;
            if (atomicReferenceFieldUpdater.compareAndSet(pVar, c1378d, c1378d2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(pVar) == c1378d);
        return false;
    }

    @Override // l9.d
    public final boolean F(p pVar, Object obj, Object obj2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f15548f;
            if (atomicReferenceFieldUpdater.compareAndSet(pVar, obj, obj2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(pVar) == obj);
        return false;
    }

    @Override // l9.d
    public final boolean G(p pVar, o oVar, o oVar2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.d;
            if (atomicReferenceFieldUpdater.compareAndSet(pVar, oVar, oVar2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(pVar) == oVar);
        return false;
    }

    @Override // l9.d
    public final C1378d S(p pVar) {
        return (C1378d) this.f15547e.getAndSet(pVar, C1378d.d);
    }

    @Override // l9.d
    public final o T(p pVar) {
        return (o) this.d.getAndSet(pVar, o.c);
    }

    @Override // l9.d
    public final void Y(o oVar, o oVar2) {
        this.c.lazySet(oVar, oVar2);
    }

    @Override // l9.d
    public final void Z(o oVar, Thread thread) {
        this.f15546b.lazySet(oVar, thread);
    }
}
