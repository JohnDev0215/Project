package p;

/* JADX INFO: loaded from: classes.dex */
public final class bn implements io.reactivex.rxjava3.functions.b {
    public final /* synthetic */ fn c;

    public bn(fn fnVar) {
        this.c = fnVar;
    }

    @Override // io.reactivex.rxjava3.functions.b
    public final void accept(Object obj, Object obj2) {
        sk2 sk2Var = this.c.f;
        v03.j(sk2Var, "timeKeeper");
        c7 c7Var = ln1.f;
        if (c7Var != null) {
            c7Var.c("bootstrap-feature");
            ((b7) sk2Var).b(c7Var.b());
        }
    }
}
