package p;

/* JADX INFO: loaded from: classes.dex */
public final class mh1 {
    public final tp1 a;
    public final m b;

    public mh1(tp1 tp1Var, m mVar) {
        v03.j(tp1Var, "onDemandController");
        v03.j(mVar, "properties");
        this.a = tp1Var;
        this.b = mVar;
    }

    public final lh1 a(tx1 tx1Var) {
        v03.j(tx1Var, "productState");
        return new lh1(this.a, this.b, tx1Var);
    }
}
