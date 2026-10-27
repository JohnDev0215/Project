package kotlinx.coroutines;

import g5.l;
import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: DispatchedTask.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class s0<T> extends kotlinx.coroutines.scheduling.i {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f7721h;

    public s0(int i7) {
        this.f7721h = i7;
    }

    public void b(Object obj, Throwable th) {
    }

    public abstract k5.d<T> c();

    public Throwable f(Object obj) {
        if (!(obj instanceof y)) {
            obj = null;
        }
        y yVar = (y) obj;
        if (yVar != null) {
            return yVar.f7851a;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T g(Object obj) {
        return obj;
    }

    public final void i(Throwable th, Throwable th2) {
        if (th == null && th2 == null) {
            return;
        }
        if (th != null && th2 != null) {
            g5.b.a(th, th2);
        }
        if (th == null) {
            th = th2;
        }
        kotlin.jvm.internal.l.c(th);
        g0.a(c().getContext(), new l0("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", th));
    }

    public abstract Object j();

    @Override // java.lang.Runnable
    public final void run() {
        Object objA;
        Object objA2;
        kotlinx.coroutines.scheduling.j jVar = this.f7779g;
        try {
            k5.d<T> dVarC = c();
            if (dVarC == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T>");
            }
            kotlinx.coroutines.internal.h hVar = (kotlinx.coroutines.internal.h) dVarC;
            k5.d<T> dVar = hVar.f7645m;
            k5.g context = dVar.getContext();
            Object objJ = j();
            Object objC = kotlinx.coroutines.internal.g0.c(context, hVar.f7643k);
            try {
                Throwable thF = f(objJ);
                l1 l1Var = (thF == null && t0.b(this.f7721h)) ? (l1) context.get(l1.f7703d) : null;
                if (l1Var != null && !l1Var.isActive()) {
                    CancellationException cancellationException = l1Var.getCancellationException();
                    b(objJ, cancellationException);
                    l.a aVar = g5.l.f6557e;
                    dVar.resumeWith(g5.l.a(g5.m.a(cancellationException)));
                } else if (thF != null) {
                    l.a aVar2 = g5.l.f6557e;
                    dVar.resumeWith(g5.l.a(g5.m.a(thF)));
                } else {
                    T tG = g(objJ);
                    l.a aVar3 = g5.l.f6557e;
                    dVar.resumeWith(g5.l.a(tG));
                }
                g5.r rVar = g5.r.f6563a;
                try {
                    l.a aVar4 = g5.l.f6557e;
                    jVar.Z();
                    objA2 = g5.l.a(rVar);
                } catch (Throwable th) {
                    l.a aVar5 = g5.l.f6557e;
                    objA2 = g5.l.a(g5.m.a(th));
                }
                i(null, g5.l.b(objA2));
            } finally {
                kotlinx.coroutines.internal.g0.a(context, objC);
            }
        } catch (Throwable th2) {
            try {
                l.a aVar6 = g5.l.f6557e;
                jVar.Z();
                objA = g5.l.a(g5.r.f6563a);
            } catch (Throwable th3) {
                l.a aVar7 = g5.l.f6557e;
                objA = g5.l.a(g5.m.a(th3));
            }
            i(th2, g5.l.b(objA));
        }
    }
}
