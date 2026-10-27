package p;

import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.CompletionHandlerException;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: classes.dex */
public abstract class a1 extends kotlinx.coroutines.g implements c00, e20 {
    public final y10 k;

    public a1(y10 y10Var, boolean z, boolean z2) {
        super(z2);
        if (z) {
            L((p61) y10Var.E0(qo.o));
        }
        this.k = y10Var.o0(this);
    }

    @Override // kotlinx.coroutines.g
    public final void K(CompletionHandlerException completionHandlerException) {
        ux2.m(this.k, completionHandlerException);
    }

    @Override // kotlinx.coroutines.g
    public String P() {
        return super.P();
    }

    @Override // kotlinx.coroutines.g
    public final void S(Object obj) {
        if (!(obj instanceof hv)) {
            Z(obj);
            return;
        }
        hv hvVar = (hv) obj;
        Throwable th = hvVar.a;
        hvVar.getClass();
        Y(th, hv.b.get(hvVar) != 0);
    }

    public void Y(Throwable th, boolean z) {
    }

    public void Z(Object obj) {
    }

    public final void a0(CoroutineStart coroutineStart, a1 a1Var, fz0 fz0Var) {
        int iOrdinal = coroutineStart.ordinal();
        if (iOrdinal == 0) {
            zu.F0(fz0Var, a1Var, this);
            return;
        }
        if (iOrdinal != 1) {
            if (iOrdinal == 2) {
                v03.j(fz0Var, "<this>");
                vr2.k(vr2.c(a1Var, this, fz0Var)).k(lq2.a);
                return;
            }
            if (iOrdinal != 3) {
                throw new NoWhenBranchMatchedException();
            }
            try {
                y10 y10Var = this.k;
                Object objC = kotlinx.coroutines.internal.c.c(y10Var, null);
                try {
                    he1.d(2, fz0Var);
                    Object objG = fz0Var.g(a1Var, this);
                    if (objG != CoroutineSingletons.c) {
                        k(objG);
                    }
                } finally {
                    kotlinx.coroutines.internal.c.a(y10Var, objC);
                }
            } catch (Throwable th) {
                k(kotlin.a.b(th));
            }
        }
    }

    @Override // kotlinx.coroutines.g, p.p61
    public boolean b() {
        return super.b();
    }

    @Override // p.c00
    public final y10 getContext() {
        return this.k;
    }

    @Override // p.e20
    public final y10 j() {
        return this.k;
    }

    @Override // p.c00
    public final void k(Object obj) {
        Throwable thA = Result.a(obj);
        if (thA != null) {
            obj = new hv(thA, false);
        }
        Object objO = O(obj);
        if (objO == y61.b) {
            return;
        }
        s(objO);
    }

    @Override // kotlinx.coroutines.g
    public final String w() {
        return getClass().getSimpleName().concat(" was cancelled");
    }
}
