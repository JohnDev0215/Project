package com.apollographql.apollo3.api;

import java.util.LinkedHashMap;
import java.util.Map;
import p.fz0;
import p.ln1;
import p.qo;
import p.rk0;
import p.sk0;
import p.tk0;
import p.v03;
import p.z4;

/* JADX INFO: loaded from: classes.dex */
public final class c implements rk0 {
    public static final qo d = new qo();
    public static final c e = new c(new LinkedHashMap(), new z4(null, null));
    public final z4 b;
    public final Map c;

    public c(LinkedHashMap linkedHashMap, z4 z4Var) {
        this.b = z4Var;
        this.c = linkedHashMap;
    }

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
        return ((ExecutionContext$plus$1) fz0Var).g(obj, this);
    }

    @Override // p.tk0
    public final tk0 d(tk0 tk0Var) {
        v03.j(tk0Var, "context");
        return a.b(this, tk0Var);
    }

    @Override // p.rk0
    public final qo getKey() {
        return d;
    }
}
