package S8;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class Z extends X8.i implements F, S {
    public d0 o;

    @Override // S8.F
    public final void a() {
        d0 d0VarJ = j();
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = d0.l;
            Object obj = atomicReferenceFieldUpdater.get(d0VarJ);
            if (obj instanceof Z) {
                if (obj != this) {
                    return;
                }
                H h10 = AbstractC0727y.f8151j;
                while (!atomicReferenceFieldUpdater.compareAndSet(d0VarJ, obj, h10)) {
                    if (atomicReferenceFieldUpdater.get(d0VarJ) != obj) {
                        break;
                    }
                }
                return;
            }
            if (!(obj instanceof S) || ((S) obj).d() == null) {
                return;
            }
            while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = X8.i.l;
                Object obj2 = atomicReferenceFieldUpdater2.get(this);
                if (obj2 instanceof X8.o) {
                    X8.i iVar = ((X8.o) obj2).f10576a;
                    return;
                }
                if (obj2 == this) {
                    return;
                }
                A7.m.d(obj2, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode");
                X8.i iVar2 = (X8.i) obj2;
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3 = X8.i.f10565n;
                X8.o oVar = (X8.o) atomicReferenceFieldUpdater3.get(iVar2);
                if (oVar == null) {
                    oVar = new X8.o(iVar2);
                    atomicReferenceFieldUpdater3.set(iVar2, oVar);
                }
                while (!atomicReferenceFieldUpdater2.compareAndSet(this, obj2, oVar)) {
                    if (atomicReferenceFieldUpdater2.get(this) != obj2) {
                        break;
                    }
                }
                iVar2.f();
                return;
            }
        }
    }

    @Override // S8.S
    public final boolean b() {
        return true;
    }

    @Override // S8.S
    public final f0 d() {
        return null;
    }

    public W getParent() {
        return j();
    }

    public final d0 j() {
        d0 d0Var = this.o;
        if (d0Var != null) {
            return d0Var;
        }
        A7.m.j("job");
        throw null;
    }

    public abstract boolean k();

    public abstract void l(Throwable th);

    @Override // X8.i
    public final String toString() {
        return getClass().getSimpleName() + '@' + AbstractC0727y.n(this) + "[job@" + AbstractC0727y.n(j()) + ']';
    }
}
