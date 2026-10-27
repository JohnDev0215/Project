package kotlinx.coroutines;

import g5.l;
import java.util.Objects;

/* JADX INFO: compiled from: DispatchedTask.kt */
/* JADX INFO: loaded from: classes.dex */
public final class t0 {
    public static final <T> void a(s0<? super T> s0Var, int i7) {
        k5.d<? super T> dVarC = s0Var.c();
        boolean z6 = i7 == 4;
        if (z6 || !(dVarC instanceof kotlinx.coroutines.internal.h) || b(i7) != b(s0Var.f7721h)) {
            d(s0Var, dVarC, z6);
            return;
        }
        e0 e0Var = ((kotlinx.coroutines.internal.h) dVarC).f7644l;
        k5.g context = dVarC.getContext();
        if (e0Var.c0(context)) {
            e0Var.a0(context, s0Var);
        } else {
            e(s0Var);
        }
    }

    public static final boolean b(int i7) {
        return i7 == 1 || i7 == 2;
    }

    public static final boolean c(int i7) {
        return i7 == 2;
    }

    public static final <T> void d(s0<? super T> s0Var, k5.d<? super T> dVar, boolean z6) {
        Object objG;
        Object objJ = s0Var.j();
        Throwable thF = s0Var.f(objJ);
        if (thF != null) {
            l.a aVar = g5.l.f6557e;
            objG = g5.m.a(thF);
        } else {
            l.a aVar2 = g5.l.f6557e;
            objG = s0Var.g(objJ);
        }
        Object objA = g5.l.a(objG);
        if (!z6) {
            dVar.resumeWith(objA);
            return;
        }
        Objects.requireNonNull(dVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T>");
        kotlinx.coroutines.internal.h hVar = (kotlinx.coroutines.internal.h) dVar;
        k5.g context = hVar.getContext();
        Object objC = kotlinx.coroutines.internal.g0.c(context, hVar.f7643k);
        try {
            hVar.f7645m.resumeWith(objA);
            g5.r rVar = g5.r.f6563a;
        } finally {
            kotlinx.coroutines.internal.g0.a(context, objC);
        }
    }

    private static final void e(s0<?> s0Var) {
        y0 y0VarA = h2.f7614b.a();
        if (y0VarA.j0()) {
            y0VarA.f0(s0Var);
            return;
        }
        y0VarA.h0(true);
        try {
            d(s0Var, s0Var.c(), true);
            do {
            } while (y0VarA.l0());
        } finally {
            try {
            } finally {
            }
        }
    }
}
