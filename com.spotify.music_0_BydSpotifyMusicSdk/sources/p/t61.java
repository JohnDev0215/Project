package p;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class t61 extends kotlinx.coroutines.internal.a implements oa0, e41, ry0 {
    public kotlinx.coroutines.g l;

    @Override // p.e41
    public final boolean b() {
        return true;
    }

    @Override // p.e41
    public final pn1 c() {
        return null;
    }

    @Override // p.oa0
    public final void dispose() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2;
        kotlinx.coroutines.g gVarK = k();
        while (true) {
            Object objI = gVarK.I();
            if (objI instanceof t61) {
                if (objI != this) {
                    return;
                }
                rd0 rd0Var = y61.g;
                do {
                    atomicReferenceFieldUpdater2 = kotlinx.coroutines.g.c;
                    if (atomicReferenceFieldUpdater2.compareAndSet(gVarK, objI, rd0Var)) {
                        return;
                    }
                } while (atomicReferenceFieldUpdater2.get(gVarK) == objI);
            } else {
                if (!(objI instanceof e41) || ((e41) objI).c() == null) {
                    return;
                }
                while (true) {
                    Object objH = h();
                    if (objH instanceof g32) {
                        kotlinx.coroutines.internal.a aVar = ((g32) objH).a;
                        return;
                    }
                    if (objH == this) {
                        return;
                    }
                    v03.g(objH, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
                    kotlinx.coroutines.internal.a aVar2 = (kotlinx.coroutines.internal.a) objH;
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3 = kotlinx.coroutines.internal.a.k;
                    g32 g32Var = (g32) atomicReferenceFieldUpdater3.get(aVar2);
                    if (g32Var == null) {
                        g32Var = new g32(aVar2);
                        atomicReferenceFieldUpdater3.lazySet(aVar2, g32Var);
                    }
                    do {
                        atomicReferenceFieldUpdater = kotlinx.coroutines.internal.a.c;
                        if (atomicReferenceFieldUpdater.compareAndSet(this, objH, g32Var)) {
                            aVar2.d();
                            return;
                        }
                    } while (atomicReferenceFieldUpdater.get(this) == objH);
                }
            }
        }
    }

    public p61 getParent() {
        return k();
    }

    public final kotlinx.coroutines.g k() {
        kotlinx.coroutines.g gVar = this.l;
        if (gVar != null) {
            return gVar;
        }
        v03.Q("job");
        throw null;
    }

    public abstract void l(Throwable th);

    @Override // kotlinx.coroutines.internal.a
    public final String toString() {
        return getClass().getSimpleName() + '@' + f60.s(this) + "[job@" + f60.s(k()) + ']';
    }
}
