package com.apollographql.apollo3.api;

import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import p.lm;
import p.mm;
import p.ou;
import p.pk;
import p.qk;
import p.rk;
import p.ry0;
import p.td0;
import p.tk0;
import p.v03;
import p.z4;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static final boolean a(lm lmVar, final Set set, final String str, final z4 z4Var) {
        v03.j(set, "variables");
        v03.j(z4Var, "adapterContext");
        final List list = null;
        ry0 ry0Var = new ry0() { // from class: com.apollographql.apollo3.api.BooleanExpressions$evaluate$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // p.ry0
            public final Object invoke(Object obj) {
                boolean Z;
                qk qkVar = (qk) obj;
                v03.j(qkVar, "it");
                if (qkVar instanceof rk) {
                    Z = set.contains(((rk) qkVar).a);
                } else {
                    if (!(qkVar instanceof pk)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    Z = ou.Z(((pk) qkVar).a, str);
                }
                return Boolean.valueOf(Z);
            }
        };
        if (v03.b(lmVar, mm.q)) {
            return true;
        }
        if (v03.b(lmVar, mm.f156p)) {
            return false;
        }
        return ((Boolean) ry0Var.invoke(lmVar.f147p)).booleanValue();
    }

    public static tk0 b(tk0 tk0Var, tk0 tk0Var2) {
        v03.j(tk0Var2, "context");
        return tk0Var2 == td0.b ? tk0Var : (tk0) tk0Var2.c(tk0Var, ExecutionContext$plus$1.c);
    }

    public static final lm c(String... strArr) {
        return new lm(new pk(kotlin.collections.b.x0(strArr)));
    }
}
