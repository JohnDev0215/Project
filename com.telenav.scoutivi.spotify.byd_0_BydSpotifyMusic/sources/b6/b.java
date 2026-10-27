package b6;

import g5.l;
import g5.m;
import k5.d;
import k5.g;
import kotlin.coroutines.jvm.internal.h;
import kotlin.jvm.internal.v;
import kotlinx.coroutines.internal.g0;
import kotlinx.coroutines.internal.y;
import kotlinx.coroutines.t1;
import r5.l;
import r5.p;

/* JADX INFO: compiled from: Undispatched.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static final <T> void a(l<? super d<? super T>, ? extends Object> lVar, d<? super T> dVar) {
        d dVarA = h.a(dVar);
        try {
            g context = dVar.getContext();
            Object objC = g0.c(context, null);
            try {
                if (lVar == null) {
                    throw new NullPointerException("null cannot be cast to non-null type (kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
                }
                Object objInvoke = ((l) v.b(lVar, 1)).invoke(dVarA);
                if (objInvoke != l5.d.d()) {
                    l.a aVar = g5.l.f6557e;
                    dVarA.resumeWith(g5.l.a(objInvoke));
                }
            } finally {
                g0.a(context, objC);
            }
        } catch (Throwable th) {
            l.a aVar2 = g5.l.f6557e;
            dVarA.resumeWith(g5.l.a(m.a(th)));
        }
    }

    public static final <R, T> void b(p<? super R, ? super d<? super T>, ? extends Object> pVar, R r7, d<? super T> dVar) {
        d dVarA = h.a(dVar);
        try {
            g context = dVar.getContext();
            Object objC = g0.c(context, null);
            try {
                if (pVar == null) {
                    throw new NullPointerException("null cannot be cast to non-null type (R, kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
                }
                Object objInvoke = ((p) v.b(pVar, 2)).invoke(r7, dVarA);
                if (objInvoke != l5.d.d()) {
                    l.a aVar = g5.l.f6557e;
                    dVarA.resumeWith(g5.l.a(objInvoke));
                }
            } finally {
                g0.a(context, objC);
            }
        } catch (Throwable th) {
            l.a aVar2 = g5.l.f6557e;
            dVarA.resumeWith(g5.l.a(m.a(th)));
        }
    }

    public static final <T> void c(r5.l<? super d<? super T>, ? extends Object> lVar, d<? super T> dVar) {
        d dVarA = h.a(dVar);
        try {
            if (lVar == null) {
                throw new NullPointerException("null cannot be cast to non-null type (kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
            }
            Object objInvoke = ((r5.l) v.b(lVar, 1)).invoke(dVarA);
            if (objInvoke != l5.d.d()) {
                l.a aVar = g5.l.f6557e;
                dVarA.resumeWith(g5.l.a(objInvoke));
            }
        } catch (Throwable th) {
            l.a aVar2 = g5.l.f6557e;
            dVarA.resumeWith(g5.l.a(m.a(th)));
        }
    }

    public static final <R, T> void d(p<? super R, ? super d<? super T>, ? extends Object> pVar, R r7, d<? super T> dVar) {
        d dVarA = h.a(dVar);
        try {
            if (pVar == null) {
                throw new NullPointerException("null cannot be cast to non-null type (R, kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
            }
            Object objInvoke = ((p) v.b(pVar, 2)).invoke(r7, dVarA);
            if (objInvoke != l5.d.d()) {
                l.a aVar = g5.l.f6557e;
                dVarA.resumeWith(g5.l.a(objInvoke));
            }
        } catch (Throwable th) {
            l.a aVar2 = g5.l.f6557e;
            dVarA.resumeWith(g5.l.a(m.a(th)));
        }
    }

    public static final <T, R> Object e(y<? super T> yVar, R r7, p<? super R, ? super d<? super T>, ? extends Object> pVar) throws Throwable {
        Object yVar2;
        Object objMakeCompletingOnce$kotlinx_coroutines_core;
        yVar.initParentJob$kotlinx_coroutines_core();
        try {
        } catch (Throwable th) {
            yVar2 = new kotlinx.coroutines.y(th, false, 2, null);
        }
        if (pVar == null) {
            throw new NullPointerException("null cannot be cast to non-null type (R, kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
        }
        yVar2 = ((p) v.b(pVar, 2)).invoke(r7, yVar);
        if (yVar2 != l5.d.d() && (objMakeCompletingOnce$kotlinx_coroutines_core = yVar.makeCompletingOnce$kotlinx_coroutines_core(yVar2)) != t1.f7827b) {
            if (objMakeCompletingOnce$kotlinx_coroutines_core instanceof kotlinx.coroutines.y) {
                throw ((kotlinx.coroutines.y) objMakeCompletingOnce$kotlinx_coroutines_core).f7851a;
            }
            return t1.h(objMakeCompletingOnce$kotlinx_coroutines_core);
        }
        return l5.d.d();
    }
}
