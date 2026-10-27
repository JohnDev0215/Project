package p;

/* JADX INFO: loaded from: classes.dex */
public final class cn implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ fn c;

    public cn(fn fnVar) {
        this.c = fnVar;
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        um umVar = (um) obj;
        v03.j(umVar, "data");
        c7 c7Var = ln1.f;
        if (c7Var != null) {
            c7Var.g("bootstrap-inject");
        }
        this.c.c.c(umVar.a());
        c7 c7Var2 = ln1.f;
        if (c7Var2 != null) {
            c7Var2.c("bootstrap-inject");
        }
        return umVar;
    }
}
