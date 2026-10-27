package com.apollographql.apollo3.interceptor;

import com.apollographql.apollo3.api.http.HttpMethod;
import p.dv0;
import p.e70;
import p.j7;
import p.k62;
import p.l7;
import p.m7;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class a implements j7 {
    public final HttpMethod a;
    public final HttpMethod b;

    public a(HttpMethod httpMethod, HttpMethod httpMethod2) {
        this.a = httpMethod;
        this.b = httpMethod2;
    }

    @Override // p.j7
    public final dv0 a(m7 m7Var, e70 e70Var) {
        v03.j(m7Var, "request");
        Boolean bool = m7Var.h;
        if (bool != null && !bool.booleanValue()) {
            return e70Var.a(m7Var);
        }
        l7 l7VarA = m7Var.a();
        l7VarA.d = this.a;
        l7VarA.g = Boolean.FALSE;
        l7VarA.f = Boolean.TRUE;
        return new k62(new AutoPersistedQueryInterceptor$intercept$1(e70Var, l7VarA.a(), this, false, null));
    }
}
