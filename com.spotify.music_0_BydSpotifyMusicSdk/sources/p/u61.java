package p;

/* JADX INFO: loaded from: classes.dex */
public final class u61 extends zp {
    public final kotlinx.coroutines.g q;

    public u61(c00 c00Var, bv bvVar) {
        super(1, c00Var);
        this.q = bvVar;
    }

    @Override // p.zp
    public final Throwable p(kotlinx.coroutines.g gVar) {
        Throwable thD;
        Object objI = this.q.I();
        return (!(objI instanceof w61) || (thD = ((w61) objI).d()) == null) ? objI instanceof hv ? ((hv) objI).a : gVar.B() : thD;
    }

    @Override // p.zp
    public final String x() {
        return "AwaitContinuation";
    }
}
