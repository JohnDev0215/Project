package p;

import kotlin.coroutines.intrinsics.CoroutineSingletons;

/* JADX INFO: loaded from: classes.dex */
public final class ew2 implements dv0 {
    public final /* synthetic */ dv0 c;
    public final /* synthetic */ m7 e;
    public final /* synthetic */ wc0 k;

    public ew2(k62 k62Var, m7 m7Var, wc0 wc0Var) {
        this.c = k62Var;
        this.e = m7Var;
        this.k = wc0Var;
    }

    @Override // p.dv0
    public final Object c(fv0 fv0Var, c00 c00Var) {
        Object objC = this.c.c(new com.apollographql.apollo3.network.ws.e(fv0Var, this.e, this.k), c00Var);
        return objC == CoroutineSingletons.c ? objC : lq2.a;
    }
}
