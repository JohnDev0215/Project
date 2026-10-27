package kotlinx.coroutines.channels;

import kotlinx.coroutines.k0;
import kotlinx.coroutines.s1;

/* JADX INFO: compiled from: Broadcast.kt */
/* JADX INFO: loaded from: classes.dex */
public final class j {
    public static final <E> g<E> a(kotlinx.coroutines.h0 h0Var, k5.g gVar, int i7, k0 k0Var, r5.l<? super Throwable, g5.r> lVar, r5.p<? super a0<? super E>, ? super k5.d<? super g5.r>, ? extends Object> pVar) {
        k5.g gVarC = kotlinx.coroutines.d0.c(h0Var, gVar);
        g gVarA = h.a(i7);
        i wVar = k0Var.isLazy() ? new w(gVarC, gVarA, pVar) : new i(gVarC, gVarA, true);
        if (lVar != null) {
            ((s1) wVar).invokeOnCompletion(lVar);
        }
        ((kotlinx.coroutines.a) wVar).start(k0Var, wVar, pVar);
        return (g<E>) wVar;
    }

    public static /* synthetic */ g b(kotlinx.coroutines.h0 h0Var, k5.g gVar, int i7, k0 k0Var, r5.l lVar, r5.p pVar, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            gVar = k5.h.f7276e;
        }
        k5.g gVar2 = gVar;
        if ((i8 & 2) != 0) {
            i7 = 1;
        }
        int i9 = i7;
        if ((i8 & 4) != 0) {
            k0Var = k0.LAZY;
        }
        k0 k0Var2 = k0Var;
        if ((i8 & 8) != 0) {
            lVar = null;
        }
        return a(h0Var, gVar2, i9, k0Var2, lVar, pVar);
    }
}
