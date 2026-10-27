package kotlin.coroutines.jvm.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import p.c00;
import p.ea0;
import p.fa0;
import p.gv;
import p.qo;
import p.v03;
import p.w10;
import p.y10;
import p.zp;

/* JADX INFO: loaded from: classes.dex */
public abstract class ContinuationImpl extends BaseContinuationImpl {
    private final y10 _context;
    public transient c00 c;

    public ContinuationImpl(c00 c00Var, y10 y10Var) {
        super(c00Var);
        this._context = y10Var;
    }

    @Override // p.c00
    public y10 getContext() {
        y10 y10Var = this._context;
        v03.h(y10Var);
        return y10Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public void j() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        c00 c00Var = this.c;
        if (c00Var != null && c00Var != this) {
            w10 w10VarE0 = getContext().E0(qo.l);
            v03.h(w10VarE0);
            ea0 ea0Var = (ea0) c00Var;
            do {
                atomicReferenceFieldUpdater = ea0.f120p;
            } while (atomicReferenceFieldUpdater.get(ea0Var) == fa0.b);
            Object obj = atomicReferenceFieldUpdater.get(ea0Var);
            zp zpVar = obj instanceof zp ? (zp) obj : null;
            if (zpVar != null) {
                zpVar.n();
            }
        }
        this.c = gv.c;
    }

    public ContinuationImpl(c00 c00Var) {
        this(c00Var, c00Var != null ? c00Var.getContext() : null);
    }
}
