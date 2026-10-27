package com.apollographql.apollo3.api;

import kotlin.jvm.internal.Lambda;
import p.fz0;
import p.rk0;
import p.td0;
import p.tk0;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
final class ExecutionContext$plus$1 extends Lambda implements fz0 {
    public static final ExecutionContext$plus$1 c = new ExecutionContext$plus$1();

    public ExecutionContext$plus$1() {
        super(2);
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        tk0 tk0Var = (tk0) obj;
        rk0 rk0Var = (rk0) obj2;
        v03.j(tk0Var, "acc");
        v03.j(rk0Var, "element");
        tk0 tk0VarB = tk0Var.b(rk0Var.getKey());
        return tk0VarB == td0.b ? rk0Var : new b(rk0Var, tk0VarB);
    }
}
