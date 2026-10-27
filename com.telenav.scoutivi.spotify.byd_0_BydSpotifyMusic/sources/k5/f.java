package k5;

import g5.l;
import g5.r;
import r5.l;
import r5.p;

/* JADX INFO: compiled from: Continuation.kt */
/* JADX INFO: loaded from: classes.dex */
public final class f {
    public static final <T> void a(l<? super d<? super T>, ? extends Object> lVar, d<? super T> completion) {
        kotlin.jvm.internal.l.e(lVar, "<this>");
        kotlin.jvm.internal.l.e(completion, "completion");
        d dVarC = l5.c.c(l5.c.a(lVar, completion));
        l.a aVar = g5.l.f6557e;
        dVarC.resumeWith(g5.l.a(r.f6563a));
    }

    public static final <R, T> void b(p<? super R, ? super d<? super T>, ? extends Object> pVar, R r7, d<? super T> completion) {
        kotlin.jvm.internal.l.e(pVar, "<this>");
        kotlin.jvm.internal.l.e(completion, "completion");
        d dVarC = l5.c.c(l5.c.b(pVar, r7, completion));
        l.a aVar = g5.l.f6557e;
        dVarC.resumeWith(g5.l.a(r.f6563a));
    }
}
