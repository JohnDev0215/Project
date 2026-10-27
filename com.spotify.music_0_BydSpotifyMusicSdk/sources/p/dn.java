package p;

/* JADX INFO: loaded from: classes.dex */
public final class dn implements io.reactivex.rxjava3.functions.g {
    public final /* synthetic */ fn c;

    public dn(fn fnVar) {
        this.c = fnVar;
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final void accept(Object obj) {
        fn fnVar = this.c;
        fnVar.d.getClass();
        fnVar.g = System.currentTimeMillis();
        c7 c7Var = ln1.f;
        if (c7Var != null) {
            c7Var.g("bootstrap-network-request");
        }
    }
}
