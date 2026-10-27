package p;

import java.util.concurrent.CancellationException;
import kotlin.Result;
import kotlinx.coroutines.CoroutinesInternalError;

/* JADX INFO: loaded from: classes.dex */
public abstract class ga0 extends zi2 {
    public int k;

    public ga0(int i) {
        super(0L, fj2.g);
        this.k = i;
    }

    public abstract void b(Object obj, CancellationException cancellationException);

    public abstract c00 c();

    public Throwable d(Object obj) {
        hv hvVar = obj instanceof hv ? (hv) obj : null;
        if (hvVar != null) {
            return hvVar.a;
        }
        return null;
    }

    public Object e(Object obj) {
        return obj;
    }

    public final void g(Throwable th, Throwable th2) {
        if (th == null && th2 == null) {
            return;
        }
        if (th != null && th2 != null) {
            kotlin.a.a(th, th2);
        }
        if (th == null) {
            th = th2;
        }
        v03.h(th);
        ux2.m(c().getContext(), new CoroutinesInternalError("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", th));
    }

    public abstract Object h();

    @Override // java.lang.Runnable
    public final void run() {
        Object objB = lq2.a;
        w1 w1Var = this.e;
        try {
            c00 c00VarC = c();
            v03.g(c00VarC, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTask>");
            ea0 ea0Var = (ea0) c00VarC;
            c00 c00Var = ea0Var.m;
            Object obj = ea0Var.o;
            y10 context = c00Var.getContext();
            Object objC = kotlinx.coroutines.internal.c.c(context, obj);
            jq2 jq2VarE = objC != kotlinx.coroutines.internal.c.a ? kotlinx.coroutines.a.e(c00Var, context, objC) : null;
            try {
                y10 context2 = c00Var.getContext();
                Object objH = h();
                Throwable thD = d(objH);
                p61 p61Var = (thD == null && v03.x(this.k)) ? (p61) context2.E0(qo.o) : null;
                if (p61Var != null && !p61Var.b()) {
                    CancellationException cancellationExceptionB = ((kotlinx.coroutines.g) p61Var).B();
                    b(objH, cancellationExceptionB);
                    c00Var.k(kotlin.a.b(cancellationExceptionB));
                } else if (thD != null) {
                    c00Var.k(kotlin.a.b(thD));
                } else {
                    c00Var.k(e(objH));
                }
                if (jq2VarE == null || jq2VarE.b0()) {
                    kotlinx.coroutines.internal.c.a(context, objC);
                }
                try {
                    w1Var.getClass();
                } catch (Throwable th) {
                    objB = kotlin.a.b(th);
                }
                g(null, Result.a(objB));
            } catch (Throwable th2) {
                if (jq2VarE == null || jq2VarE.b0()) {
                    kotlinx.coroutines.internal.c.a(context, objC);
                }
                throw th2;
            }
        } catch (Throwable th3) {
            try {
                w1Var.getClass();
            } catch (Throwable th4) {
                objB = kotlin.a.b(th4);
            }
            g(th3, Result.a(objB));
        }
    }
}
