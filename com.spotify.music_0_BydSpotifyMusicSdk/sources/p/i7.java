package p;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class i7 {
    public final com.apollographql.apollo3.api.c a;
    public final List b;
    public final tk0 c;
    public final Boolean d;
    public final mw e;
    public final ym1 f;

    public i7(com.apollographql.apollo3.network.http.c cVar, com.apollographql.apollo3.api.c cVar2, hn1 hn1Var, ArrayList arrayList, td0 td0Var, Boolean bool) {
        this.a = cVar2;
        this.b = arrayList;
        this.c = td0Var;
        this.d = bool;
        f70 f70Var = ha0.c;
        mw mwVar = new mw(f70Var, q30.b(f70Var));
        this.e = mwVar;
        this.f = new ym1(cVar, hn1Var, mwVar.b);
    }

    public final jh1 a(fz1 fz1Var) {
        jh1 jh1Var = new jh1();
        jh1Var.a = this;
        jh1Var.b = fz1Var;
        jh1Var.c = td0.b;
        return jh1Var;
    }
}
