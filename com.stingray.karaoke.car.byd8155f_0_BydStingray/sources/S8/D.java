package S8;

import h8.AbstractC1386c;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CancellationException;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public abstract class D extends Z8.i {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f8083n;

    public D(int i7) {
        super(0L, false);
        this.f8083n = i7;
    }

    public void a(CancellationException cancellationException) {
    }

    public abstract InterfaceC2189c c();

    public Throwable d(Object obj) {
        C0720q c0720q = obj instanceof C0720q ? (C0720q) obj : null;
        if (c0720q != null) {
            return c0720q.f8130a;
        }
        return null;
    }

    public Object e(Object obj) {
        return obj;
    }

    public final void f(Throwable th) throws IllegalAccessException, InvocationTargetException {
        AbstractC0727y.q(new K7.r0("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", th), c().getContext());
    }

    public abstract Object g();

    @Override // java.lang.Runnable
    public final void run() throws IllegalAccessException, InvocationTargetException {
        try {
            InterfaceC2189c interfaceC2189cC = c();
            A7.m.d(interfaceC2189cC, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTask>");
            X8.f fVar = (X8.f) interfaceC2189cC;
            AbstractC2410c abstractC2410c = fVar.f10555p;
            Object obj = fVar.f10557r;
            InterfaceC2194h context = abstractC2410c.getContext();
            Object objN = X8.a.n(context, obj);
            W w10 = null;
            t0 t0VarE = objN != X8.a.d ? AbstractC0727y.E(abstractC2410c, context, objN) : null;
            try {
                InterfaceC2194h context2 = abstractC2410c.getContext();
                Object objG = g();
                Throwable thD = d(objG);
                if (thD == null && AbstractC0727y.u(this.f8083n)) {
                    w10 = (W) context2.F(C0722t.f8137m);
                }
                if (w10 != null && !w10.b()) {
                    CancellationException cancellationExceptionY = w10.y();
                    a(cancellationExceptionY);
                    abstractC2410c.resumeWith(AbstractC1386c.f(cancellationExceptionY));
                } else if (thD != null) {
                    abstractC2410c.resumeWith(AbstractC1386c.f(thD));
                } else {
                    abstractC2410c.resumeWith(e(objG));
                }
                if (t0VarE == null || t0VarE.j0()) {
                    X8.a.i(context, objN);
                }
            } catch (Throwable th) {
                if (t0VarE == null || t0VarE.j0()) {
                    X8.a.i(context, objN);
                }
                throw th;
            }
        } catch (Throwable th2) {
            f(th2);
        }
    }
}
