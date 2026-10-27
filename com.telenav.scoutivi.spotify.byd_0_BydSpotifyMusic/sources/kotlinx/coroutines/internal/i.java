package kotlinx.coroutines.internal;

import g5.l;
import java.util.concurrent.CancellationException;
import kotlinx.coroutines.h2;
import kotlinx.coroutines.l1;
import kotlinx.coroutines.y0;

/* JADX INFO: compiled from: DispatchedContinuation.kt */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final c0 f7647a = new c0("UNDEFINED");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c0 f7648b = new c0("REUSABLE_CLAIMED");

    public static final <T> void b(k5.d<? super T> dVar, Object obj, r5.l<? super Throwable, g5.r> lVar) {
        boolean z6;
        if (!(dVar instanceof h)) {
            dVar.resumeWith(obj);
            return;
        }
        h hVar = (h) dVar;
        Object objC = kotlinx.coroutines.c0.c(obj, lVar);
        if (hVar.f7644l.c0(hVar.getContext())) {
            hVar.f7641i = objC;
            hVar.f7721h = 1;
            hVar.f7644l.a0(hVar.getContext(), hVar);
            return;
        }
        y0 y0VarA = h2.f7614b.a();
        if (y0VarA.j0()) {
            hVar.f7641i = objC;
            hVar.f7721h = 1;
            y0VarA.f0(hVar);
            return;
        }
        y0VarA.h0(true);
        try {
            l1 l1Var = (l1) hVar.getContext().get(l1.f7703d);
            if (l1Var == null || l1Var.isActive()) {
                z6 = false;
            } else {
                CancellationException cancellationException = l1Var.getCancellationException();
                hVar.b(objC, cancellationException);
                l.a aVar = g5.l.f6557e;
                hVar.resumeWith(g5.l.a(g5.m.a(cancellationException)));
                z6 = true;
            }
            if (!z6) {
                k5.g context = hVar.getContext();
                Object objC2 = g0.c(context, hVar.f7643k);
                try {
                    hVar.f7645m.resumeWith(obj);
                    g5.r rVar = g5.r.f6563a;
                    g0.a(context, objC2);
                } catch (Throwable th) {
                    g0.a(context, objC2);
                    throw th;
                }
            }
            while (y0VarA.l0()) {
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    public static /* synthetic */ void c(k5.d dVar, Object obj, r5.l lVar, int i7, Object obj2) {
        if ((i7 & 2) != 0) {
            lVar = null;
        }
        b(dVar, obj, lVar);
    }

    public static final boolean d(h<? super g5.r> hVar) {
        g5.r rVar = g5.r.f6563a;
        y0 y0VarA = h2.f7614b.a();
        if (y0VarA.k0()) {
            return false;
        }
        if (y0VarA.j0()) {
            hVar.f7641i = rVar;
            hVar.f7721h = 1;
            y0VarA.f0(hVar);
            return true;
        }
        y0VarA.h0(true);
        try {
            hVar.run();
            do {
            } while (y0VarA.l0());
        } finally {
            try {
            } finally {
            }
        }
        return false;
    }
}
