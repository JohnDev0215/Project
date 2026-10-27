package kotlin.coroutines.jvm.internal;

import k5.g;

/* JADX INFO: compiled from: ContinuationImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class d extends a {
    private final k5.g _context;
    private transient k5.d<Object> intercepted;

    public d(k5.d<Object> dVar, k5.g gVar) {
        super(dVar);
        this._context = gVar;
    }

    @Override // k5.d
    public k5.g getContext() {
        k5.g gVar = this._context;
        kotlin.jvm.internal.l.c(gVar);
        return gVar;
    }

    public final k5.d<Object> intercepted() {
        k5.d<Object> dVarI = this.intercepted;
        if (dVarI == null) {
            k5.e eVar = (k5.e) getContext().get(k5.e.f7273b);
            if (eVar == null || (dVarI = eVar.I(this)) == null) {
                dVarI = this;
            }
            this.intercepted = dVarI;
        }
        return dVarI;
    }

    @Override // kotlin.coroutines.jvm.internal.a
    protected void releaseIntercepted() {
        k5.d<?> dVar = this.intercepted;
        if (dVar != null && dVar != this) {
            g.b bVar = getContext().get(k5.e.f7273b);
            kotlin.jvm.internal.l.c(bVar);
            ((k5.e) bVar).z(dVar);
        }
        this.intercepted = c.f7484e;
    }

    public d(k5.d<Object> dVar) {
        this(dVar, dVar != null ? dVar.getContext() : null);
    }
}
