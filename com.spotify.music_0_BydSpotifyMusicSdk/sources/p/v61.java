package p;

/* JADX INFO: loaded from: classes.dex */
public final class v61 extends t61 {
    public final kotlinx.coroutines.g m;
    public final w61 n;
    public final gs o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Object f200p;

    public v61(kotlinx.coroutines.g gVar, w61 w61Var, gs gsVar, Object obj) {
        this.m = gVar;
        this.n = w61Var;
        this.o = gsVar;
        this.f200p = obj;
    }

    @Override // p.ry0
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        l((Throwable) obj);
        return lq2.a;
    }

    @Override // p.t61
    public final void l(Throwable th) {
        kotlinx.coroutines.g gVar = this.m;
        gVar.getClass();
        gs gsVarQ = kotlinx.coroutines.g.Q(this.o);
        w61 w61Var = this.n;
        Object obj = this.f200p;
        if (gsVarQ != null) {
            while (q30.m(gsVarQ.m, false, new v61(gVar, w61Var, gsVarQ, obj), 1) == rn1.c) {
                gsVarQ = kotlinx.coroutines.g.Q(gsVarQ);
                if (gsVarQ == null) {
                }
            }
            return;
        }
        gVar.r(gVar.A(w61Var, obj));
    }
}
