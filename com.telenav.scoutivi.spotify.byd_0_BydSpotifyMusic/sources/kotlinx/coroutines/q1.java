package kotlinx.coroutines;

import java.util.concurrent.CancellationException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Job.kt */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class q1 {
    public static final w a(l1 l1Var) {
        return new o1(l1Var);
    }

    public static /* synthetic */ w b(l1 l1Var, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            l1Var = null;
        }
        return p1.a(l1Var);
    }

    public static final void c(k5.g gVar, CancellationException cancellationException) {
        l1 l1Var = (l1) gVar.get(l1.f7703d);
        if (l1Var != null) {
            l1Var.cancel(cancellationException);
        }
    }

    public static /* synthetic */ void d(k5.g gVar, CancellationException cancellationException, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            cancellationException = null;
        }
        p1.c(gVar, cancellationException);
    }

    public static final void e(k5.g gVar) {
        l1 l1Var = (l1) gVar.get(l1.f7703d);
        if (l1Var != null) {
            p1.f(l1Var);
        }
    }

    public static final void f(l1 l1Var) {
        if (!l1Var.isActive()) {
            throw l1Var.getCancellationException();
        }
    }
}
