package p;

import kotlin.coroutines.intrinsics.CoroutineSingletons;

/* JADX INFO: loaded from: classes.dex */
public final class j21 implements dv0 {
    public final /* synthetic */ dv0 c;
    public final /* synthetic */ wc0 e;
    public final /* synthetic */ com.apollographql.apollo3.network.http.c k;
    public final /* synthetic */ tq1 l;
    public final /* synthetic */ com.apollographql.apollo3.api.c m;

    public j21(kotlinx.coroutines.flow.f fVar, wc0 wc0Var, com.apollographql.apollo3.network.http.c cVar, tq1 tq1Var, com.apollographql.apollo3.api.c cVar2) {
        this.c = fVar;
        this.e = wc0Var;
        this.k = cVar;
        this.l = tq1Var;
        this.m = cVar2;
    }

    @Override // p.dv0
    public final Object c(fv0 fv0Var, c00 c00Var) {
        Object objC = this.c.c(new com.apollographql.apollo3.network.http.b(fv0Var, this.e, this.k, this.l, this.m), c00Var);
        return objC == CoroutineSingletons.c ? objC : lq2.a;
    }
}
