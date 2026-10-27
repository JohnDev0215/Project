package p;

/* JADX INFO: loaded from: classes.dex */
public final class mg implements rk0 {
    public static final qo b = new qo();

    @Override // p.tk0
    public final rk0 a(sk0 sk0Var) {
        return ln1.l(this, sk0Var);
    }

    @Override // p.tk0
    public final tk0 b(sk0 sk0Var) {
        return ln1.s(this, sk0Var);
    }

    @Override // p.tk0
    public final Object c(Object obj, fz0 fz0Var) {
        v03.j(fz0Var, "operation");
        return fz0Var.g(obj, this);
    }

    @Override // p.tk0
    public final tk0 d(tk0 tk0Var) {
        v03.j(tk0Var, "context");
        return com.apollographql.apollo3.api.a.b(this, tk0Var);
    }

    @Override // p.rk0
    public final qo getKey() {
        return b;
    }
}
