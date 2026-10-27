package p;

/* JADX INFO: loaded from: classes.dex */
public final class ev0 implements az1 {
    public final dv0 c;
    public final y10 e;

    public ev0(y10 y10Var, dv0 dv0Var) {
        this.c = dv0Var;
        this.e = y10Var;
    }

    @Override // p.az1
    public final void subscribe(rg2 rg2Var) {
        rg2Var.getClass();
        rg2Var.onSubscribe(new kotlinx.coroutines.reactive.b(this.c, rg2Var, this.e));
    }
}
