package p;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Result;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class ea0 extends ga0 implements f20, c00 {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f120p = AtomicReferenceFieldUpdater.newUpdater(ea0.class, Object.class, "_reusableCancellableContinuation");
    private volatile Object _reusableCancellableContinuation;
    public final kotlinx.coroutines.b l;
    public final c00 m;
    public Object n;
    public final Object o;

    public ea0(kotlinx.coroutines.b bVar, ContinuationImpl continuationImpl) {
        super(-1);
        this.l = bVar;
        this.m = continuationImpl;
        this.n = fa0.a;
        this.o = kotlinx.coroutines.internal.c.b(continuationImpl.getContext());
    }

    @Override // p.ga0
    public final void b(Object obj, CancellationException cancellationException) {
        if (obj instanceof iv) {
            ((iv) obj).b.invoke(cancellationException);
        }
    }

    @Override // p.ga0
    public final c00 c() {
        return this;
    }

    @Override // p.f20
    public final f20 f() {
        c00 c00Var = this.m;
        if (c00Var instanceof f20) {
            return (f20) c00Var;
        }
        return null;
    }

    @Override // p.c00
    public final y10 getContext() {
        return this.m.getContext();
    }

    @Override // p.ga0
    public final Object h() {
        Object obj = this.n;
        this.n = fa0.a;
        return obj;
    }

    @Override // p.c00
    public final void k(Object obj) {
        c00 c00Var = this.m;
        y10 context = c00Var.getContext();
        Throwable thA = Result.a(obj);
        Object hvVar = thA == null ? obj : new hv(thA, false);
        kotlinx.coroutines.b bVar = this.l;
        if (bVar.I0()) {
            this.n = hvVar;
            this.k = 0;
            bVar.G0(context, this);
            return;
        }
        pi0 pi0VarA = kk2.a();
        if (pi0VarA.O0()) {
            this.n = hvVar;
            this.k = 0;
            pi0VarA.L0(this);
            return;
        }
        pi0VarA.N0(true);
        try {
            y10 context2 = c00Var.getContext();
            Object objC = kotlinx.coroutines.internal.c.c(context2, this.o);
            try {
                c00Var.k(obj);
                while (pi0VarA.Q0()) {
                }
            } finally {
                kotlinx.coroutines.internal.c.a(context2, objC);
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    public final String toString() {
        return "DispatchedContinuation[" + this.l + ", " + f60.P(this.m) + ']';
    }
}
