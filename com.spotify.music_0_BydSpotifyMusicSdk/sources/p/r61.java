package p;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public class r61 extends kotlinx.coroutines.g {
    public final boolean k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r61(p61 p61Var) {
        super(true);
        boolean z = true;
        L(p61Var);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = kotlinx.coroutines.g.e;
        fs fsVar = (fs) atomicReferenceFieldUpdater.get(this);
        gs gsVar = fsVar instanceof gs ? (gs) fsVar : null;
        if (gsVar == null) {
            z = false;
            break;
        }
        kotlinx.coroutines.g gVarK = gsVar.k();
        while (!gVarK.F()) {
            fs fsVar2 = (fs) atomicReferenceFieldUpdater.get(gVarK);
            gs gsVar2 = fsVar2 instanceof gs ? (gs) fsVar2 : null;
            if (gsVar2 == null) {
                z = false;
                break;
            }
            gVarK = gsVar2.k();
        }
        this.k = z;
    }

    @Override // kotlinx.coroutines.g
    public final boolean F() {
        return this.k;
    }

    @Override // kotlinx.coroutines.g
    public final boolean G() {
        return true;
    }
}
