package kotlinx.coroutines;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Builders.common.kt */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class f {
    public static final l1 a(h0 h0Var, k5.g gVar, k0 k0Var, r5.p<? super h0, ? super k5.d<? super g5.r>, ? extends Object> pVar) {
        k5.g gVarC = d0.c(h0Var, gVar);
        a u1Var = k0Var.isLazy() ? new u1(gVarC, pVar) : new f2(gVarC, true);
        u1Var.start(k0Var, u1Var, pVar);
        return u1Var;
    }

    public static /* synthetic */ l1 b(h0 h0Var, k5.g gVar, k0 k0Var, r5.p pVar, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            gVar = k5.h.f7276e;
        }
        if ((i7 & 2) != 0) {
            k0Var = k0.DEFAULT;
        }
        return e.a(h0Var, gVar, k0Var, pVar);
    }
}
