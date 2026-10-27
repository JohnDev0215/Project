package com.apollographql.apollo3.api;

import p.fz0;
import p.qo;
import p.rk0;
import p.sk0;
import p.td0;
import p.tk0;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class b implements tk0 {
    public final tk0 b;
    public final rk0 c;

    public b(rk0 rk0Var, tk0 tk0Var) {
        v03.j(tk0Var, "left");
        v03.j(rk0Var, "element");
        this.b = tk0Var;
        this.c = rk0Var;
    }

    @Override // p.tk0
    public final rk0 a(sk0 sk0Var) {
        qo qoVar = c.d;
        b bVar = this;
        while (true) {
            rk0 rk0VarA = bVar.c.a(qoVar);
            if (rk0VarA != null) {
                return rk0VarA;
            }
            tk0 tk0Var = bVar.b;
            if (!(tk0Var instanceof b)) {
                return tk0Var.a(qoVar);
            }
            bVar = (b) tk0Var;
        }
    }

    @Override // p.tk0
    public final tk0 b(sk0 sk0Var) {
        v03.j(sk0Var, "key");
        rk0 rk0Var = this.c;
        rk0 rk0VarA = rk0Var.a(sk0Var);
        tk0 tk0Var = this.b;
        if (rk0VarA != null) {
            return tk0Var;
        }
        tk0 tk0VarB = tk0Var.b(sk0Var);
        return tk0VarB == tk0Var ? this : tk0VarB == td0.b ? rk0Var : new b(rk0Var, tk0VarB);
    }

    @Override // p.tk0
    public final Object c(Object obj, fz0 fz0Var) {
        v03.j(fz0Var, "operation");
        return ((ExecutionContext$plus$1) fz0Var).g(this.b.c(obj, fz0Var), this.c);
    }

    @Override // p.tk0
    public final tk0 d(tk0 tk0Var) {
        return a.b(this, tk0Var);
    }
}
