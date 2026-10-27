package p;

import kotlin.coroutines.intrinsics.CoroutineSingletons;

/* JADX INFO: loaded from: classes.dex */
public final class dw2 implements dv0 {
    public final /* synthetic */ dv0 c;
    public final /* synthetic */ m7 e;

    public dw2(kotlinx.coroutines.flow.i iVar, m7 m7Var) {
        this.c = iVar;
        this.e = m7Var;
    }

    @Override // p.dv0
    public final Object c(fv0 fv0Var, c00 c00Var) {
        Object objC = this.c.c(new com.apollographql.apollo3.network.ws.d(fv0Var, this.e), c00Var);
        return objC == CoroutineSingletons.c ? objC : lq2.a;
    }
}
