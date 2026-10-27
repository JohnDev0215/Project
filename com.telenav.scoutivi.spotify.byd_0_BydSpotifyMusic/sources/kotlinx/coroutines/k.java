package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlinx.coroutines.l1;

/* JADX INFO: compiled from: CancellableContinuationImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public class k<T> extends s0<T> implements i<T>, kotlin.coroutines.jvm.internal.e {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f7695k = AtomicIntegerFieldUpdater.newUpdater(k.class, "_decision");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f7696l = AtomicReferenceFieldUpdater.newUpdater(k.class, Object.class, "_state");
    private volatile int _decision;
    private volatile Object _parentHandle;
    private volatile Object _state;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final k5.g f7697i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final k5.d<T> f7698j;

    /* JADX WARN: Multi-variable type inference failed */
    public k(k5.d<? super T> dVar, int i7) {
        super(i7);
        this.f7698j = dVar;
        this.f7697i = dVar.getContext();
        this._decision = 0;
        this._state = b.f7515e;
        this._parentHandle = null;
    }

    private final boolean C() {
        k5.d<T> dVar = this.f7698j;
        return (dVar instanceof kotlinx.coroutines.internal.h) && ((kotlinx.coroutines.internal.h) dVar).o(this);
    }

    private final g D(r5.l<? super Throwable, g5.r> lVar) {
        return lVar instanceof g ? (g) lVar : new i1(lVar);
    }

    private final void E(r5.l<? super Throwable, g5.r> lVar, Object obj) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + lVar + ", already has " + obj).toString());
    }

    private final void I(Object obj, int i7, r5.l<? super Throwable, g5.r> lVar) {
        Object obj2;
        do {
            obj2 = this._state;
            if (!(obj2 instanceof y1)) {
                if (obj2 instanceof n) {
                    n nVar = (n) obj2;
                    if (nVar.c()) {
                        if (lVar != null) {
                            n(lVar, nVar.f7851a);
                            return;
                        }
                        return;
                    }
                }
                k(obj);
                throw new g5.e();
            }
        } while (!j.a(f7696l, this, obj2, K((y1) obj2, obj, i7, lVar, null)));
        t();
        u(i7);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void J(k kVar, Object obj, int i7, r5.l lVar, int i8, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resumeImpl");
        }
        if ((i8 & 4) != 0) {
            lVar = null;
        }
        kVar.I(obj, i7, lVar);
    }

    private final Object K(y1 y1Var, Object obj, int i7, r5.l<? super Throwable, g5.r> lVar, Object obj2) {
        if (obj instanceof y) {
            return obj;
        }
        if (!t0.b(i7) && obj2 == null) {
            return obj;
        }
        if (lVar == null && ((!(y1Var instanceof g) || (y1Var instanceof c)) && obj2 == null)) {
            return obj;
        }
        if (!(y1Var instanceof g)) {
            y1Var = null;
        }
        return new x(obj, (g) y1Var, lVar, obj2, null, 16, null);
    }

    private final void L(v0 v0Var) {
        this._parentHandle = v0Var;
    }

    private final void M() {
        l1 l1Var;
        if (q() || x() != null || (l1Var = (l1) this.f7698j.getContext().get(l1.f7703d)) == null) {
            return;
        }
        v0 v0VarE = l1.a.e(l1Var, true, false, new o(l1Var, this), 2, null);
        L(v0VarE);
        if (!B() || C()) {
            return;
        }
        v0VarE.dispose();
        L(x1.f7849e);
    }

    private final boolean N() {
        do {
            int i7 = this._decision;
            if (i7 != 0) {
                if (i7 == 1) {
                    return false;
                }
                throw new IllegalStateException("Already resumed".toString());
            }
        } while (!f7695k.compareAndSet(this, 0, 2));
        return true;
    }

    private final kotlinx.coroutines.internal.c0 O(Object obj, Object obj2, r5.l<? super Throwable, g5.r> lVar) {
        Object obj3;
        do {
            obj3 = this._state;
            if (!(obj3 instanceof y1)) {
                if ((obj3 instanceof x) && obj2 != null && ((x) obj3).f7846d == obj2) {
                    return l.f7702a;
                }
                return null;
            }
        } while (!j.a(f7696l, this, obj3, K((y1) obj3, obj, this.f7721h, lVar, obj2)));
        t();
        return l.f7702a;
    }

    private final boolean P() {
        do {
            int i7 = this._decision;
            if (i7 != 0) {
                if (i7 == 2) {
                    return false;
                }
                throw new IllegalStateException("Already suspended".toString());
            }
        } while (!f7695k.compareAndSet(this, 0, 1));
        return true;
    }

    private final Void k(Object obj) {
        throw new IllegalStateException(("Already resumed, but proposed with update " + obj).toString());
    }

    private final void m(r5.l<? super Throwable, g5.r> lVar, Throwable th) {
        try {
            lVar.invoke(th);
        } catch (Throwable th2) {
            g0.a(getContext(), new b0("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    private final boolean p(Throwable th) {
        if (!t0.c(this.f7721h)) {
            return false;
        }
        k5.d<T> dVar = this.f7698j;
        if (!(dVar instanceof kotlinx.coroutines.internal.h)) {
            dVar = null;
        }
        kotlinx.coroutines.internal.h hVar = (kotlinx.coroutines.internal.h) dVar;
        if (hVar != null) {
            return hVar.p(th);
        }
        return false;
    }

    private final boolean q() {
        Throwable thK;
        boolean zB = B();
        if (!t0.c(this.f7721h)) {
            return zB;
        }
        k5.d<T> dVar = this.f7698j;
        if (!(dVar instanceof kotlinx.coroutines.internal.h)) {
            dVar = null;
        }
        kotlinx.coroutines.internal.h hVar = (kotlinx.coroutines.internal.h) dVar;
        if (hVar == null || (thK = hVar.k(this)) == null) {
            return zB;
        }
        if (!zB) {
            o(thK);
        }
        return true;
    }

    private final void t() {
        if (C()) {
            return;
        }
        s();
    }

    private final void u(int i7) {
        if (N()) {
            return;
        }
        t0.a(this, i7);
    }

    private final v0 x() {
        return (v0) this._parentHandle;
    }

    public void A() {
        M();
    }

    public boolean B() {
        return !(z() instanceof y1);
    }

    protected String F() {
        return "CancellableContinuation";
    }

    public final void G(Throwable th) {
        if (p(th)) {
            return;
        }
        o(th);
        t();
    }

    public final boolean H() {
        Object obj = this._state;
        if ((obj instanceof x) && ((x) obj).f7846d != null) {
            s();
            return false;
        }
        this._decision = 0;
        this._state = b.f7515e;
        return true;
    }

    @Override // kotlinx.coroutines.i
    public void a(T t6, r5.l<? super Throwable, g5.r> lVar) {
        I(t6, this.f7721h, lVar);
    }

    @Override // kotlinx.coroutines.s0
    public void b(Object obj, Throwable th) {
        while (true) {
            Object obj2 = this._state;
            if (obj2 instanceof y1) {
                throw new IllegalStateException("Not completed".toString());
            }
            if (obj2 instanceof y) {
                return;
            }
            if (obj2 instanceof x) {
                x xVar = (x) obj2;
                if (!(!xVar.c())) {
                    throw new IllegalStateException("Must be called at most once".toString());
                }
                if (j.a(f7696l, this, obj2, x.b(xVar, null, null, null, null, th, 15, null))) {
                    xVar.d(this, th);
                    return;
                }
            } else if (j.a(f7696l, this, obj2, new x(obj2, null, null, null, th, 14, null))) {
                return;
            }
        }
    }

    @Override // kotlinx.coroutines.s0
    public final k5.d<T> c() {
        return this.f7698j;
    }

    @Override // kotlinx.coroutines.i
    public void d(r5.l<? super Throwable, g5.r> lVar) {
        g gVarD = D(lVar);
        while (true) {
            Object obj = this._state;
            if (obj instanceof b) {
                if (j.a(f7696l, this, obj, gVarD)) {
                    return;
                }
            } else if (obj instanceof g) {
                E(lVar, obj);
            } else {
                boolean z6 = obj instanceof y;
                if (z6) {
                    if (!((y) obj).b()) {
                        E(lVar, obj);
                    }
                    if (obj instanceof n) {
                        if (!z6) {
                            obj = null;
                        }
                        y yVar = (y) obj;
                        m(lVar, yVar != null ? yVar.f7851a : null);
                        return;
                    }
                    return;
                }
                if (obj instanceof x) {
                    x xVar = (x) obj;
                    if (xVar.f7844b != null) {
                        E(lVar, obj);
                    }
                    if (gVarD instanceof c) {
                        return;
                    }
                    if (xVar.c()) {
                        m(lVar, xVar.f7847e);
                        return;
                    } else {
                        if (j.a(f7696l, this, obj, x.b(xVar, null, gVarD, null, null, null, 29, null))) {
                            return;
                        }
                    }
                } else {
                    if (gVarD instanceof c) {
                        return;
                    }
                    if (j.a(f7696l, this, obj, new x(obj, gVarD, null, null, null, 28, null))) {
                        return;
                    }
                }
            }
        }
    }

    @Override // kotlinx.coroutines.i
    public Object e(T t6, Object obj) {
        return O(t6, obj, null);
    }

    @Override // kotlinx.coroutines.s0
    public Throwable f(Object obj) {
        Throwable thF = super.f(obj);
        if (thF != null) {
            return thF;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.s0
    public <T> T g(Object obj) {
        return obj instanceof x ? (T) ((x) obj).f7843a : obj;
    }

    @Override // k5.d
    public k5.g getContext() {
        return this.f7697i;
    }

    @Override // kotlinx.coroutines.i
    public void h(e0 e0Var, T t6) {
        k5.d<T> dVar = this.f7698j;
        if (!(dVar instanceof kotlinx.coroutines.internal.h)) {
            dVar = null;
        }
        kotlinx.coroutines.internal.h hVar = (kotlinx.coroutines.internal.h) dVar;
        J(this, t6, (hVar != null ? hVar.f7644l : null) == e0Var ? 4 : this.f7721h, null, 4, null);
    }

    @Override // kotlinx.coroutines.s0
    public Object j() {
        return z();
    }

    public final void l(g gVar, Throwable th) {
        try {
            gVar.a(th);
        } catch (Throwable th2) {
            g0.a(getContext(), new b0("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    public final void n(r5.l<? super Throwable, g5.r> lVar, Throwable th) {
        try {
            lVar.invoke(th);
        } catch (Throwable th2) {
            g0.a(getContext(), new b0("Exception in resume onCancellation handler for " + this, th2));
        }
    }

    public boolean o(Throwable th) {
        Object obj;
        boolean z6;
        do {
            obj = this._state;
            if (!(obj instanceof y1)) {
                return false;
            }
            z6 = obj instanceof g;
        } while (!j.a(f7696l, this, obj, new n(this, th, z6)));
        if (!z6) {
            obj = null;
        }
        g gVar = (g) obj;
        if (gVar != null) {
            l(gVar, th);
        }
        t();
        u(this.f7721h);
        return true;
    }

    @Override // kotlinx.coroutines.i
    public Object r(T t6, Object obj, r5.l<? super Throwable, g5.r> lVar) {
        return O(t6, obj, lVar);
    }

    @Override // k5.d
    public void resumeWith(Object obj) {
        J(this, c0.b(obj, this), this.f7721h, null, 4, null);
    }

    public final void s() {
        v0 v0VarX = x();
        if (v0VarX != null) {
            v0VarX.dispose();
        }
        L(x1.f7849e);
    }

    public String toString() {
        return F() + '(' + m0.c(this.f7698j) + "){" + z() + "}@" + m0.b(this);
    }

    @Override // kotlinx.coroutines.i
    public void v(Object obj) {
        u(this.f7721h);
    }

    public Throwable w(l1 l1Var) {
        return l1Var.getCancellationException();
    }

    public final Object y() throws Throwable {
        l1 l1Var;
        M();
        if (P()) {
            return l5.d.d();
        }
        Object objZ = z();
        if (objZ instanceof y) {
            throw ((y) objZ).f7851a;
        }
        if (!t0.b(this.f7721h) || (l1Var = (l1) getContext().get(l1.f7703d)) == null || l1Var.isActive()) {
            return g(objZ);
        }
        CancellationException cancellationException = l1Var.getCancellationException();
        b(objZ, cancellationException);
        throw cancellationException;
    }

    public final Object z() {
        return this._state;
    }
}
