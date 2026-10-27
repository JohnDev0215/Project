package b6;

import g5.l;
import g5.m;
import g5.r;
import k5.d;
import kotlinx.coroutines.internal.i;
import l5.c;
import r5.p;

/* JADX INFO: compiled from: Cancellable.kt */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final void a(d<? super r> dVar, d<?> dVar2) {
        try {
            d dVarC = c.c(dVar);
            l.a aVar = l.f6557e;
            i.c(dVarC, l.a(r.f6563a), null, 2, null);
        } catch (Throwable th) {
            l.a aVar2 = l.f6557e;
            dVar2.resumeWith(l.a(m.a(th)));
        }
    }

    public static final <T> void b(r5.l<? super d<? super T>, ? extends Object> lVar, d<? super T> dVar) {
        try {
            d dVarC = c.c(c.a(lVar, dVar));
            l.a aVar = l.f6557e;
            i.c(dVarC, l.a(r.f6563a), null, 2, null);
        } catch (Throwable th) {
            l.a aVar2 = l.f6557e;
            dVar.resumeWith(l.a(m.a(th)));
        }
    }

    public static final <R, T> void c(p<? super R, ? super d<? super T>, ? extends Object> pVar, R r7, d<? super T> dVar, r5.l<? super Throwable, r> lVar) {
        try {
            d dVarC = c.c(c.b(pVar, r7, dVar));
            l.a aVar = l.f6557e;
            i.b(dVarC, l.a(r.f6563a), lVar);
        } catch (Throwable th) {
            l.a aVar2 = l.f6557e;
            dVar.resumeWith(l.a(m.a(th)));
        }
    }

    public static /* synthetic */ void d(p pVar, Object obj, d dVar, r5.l lVar, int i7, Object obj2) {
        if ((i7 & 4) != 0) {
            lVar = null;
        }
        c(pVar, obj, dVar, lVar);
    }
}
