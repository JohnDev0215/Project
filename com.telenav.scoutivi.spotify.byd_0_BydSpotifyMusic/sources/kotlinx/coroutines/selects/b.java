package kotlinx.coroutines.selects;

import g5.l;
import g5.r;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlinx.coroutines.c0;
import kotlinx.coroutines.g0;
import kotlinx.coroutines.internal.m;
import kotlinx.coroutines.internal.o;
import kotlinx.coroutines.internal.w;
import kotlinx.coroutines.j;
import kotlinx.coroutines.l1;
import kotlinx.coroutines.n1;
import kotlinx.coroutines.r0;
import kotlinx.coroutines.v0;
import kotlinx.coroutines.y;
import r5.l;
import r5.p;

/* JADX INFO: compiled from: Select.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b<R> extends m implements kotlinx.coroutines.selects.a<R>, f<R>, k5.d<R>, kotlin.coroutines.jvm.internal.e {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    static final AtomicReferenceFieldUpdater f7792j = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "_state");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    static final AtomicReferenceFieldUpdater f7793k = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "_result");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final k5.d<R> f7794i;
    volatile Object _state = g.e();
    volatile Object _result = g.f7805c;
    private volatile Object _parentHandle = null;

    /* JADX INFO: compiled from: Select.kt */
    private static final class a extends kotlinx.coroutines.internal.d<Object> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final long f7795b = g.f7807e.a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final b<?> f7796c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final kotlinx.coroutines.internal.b f7797d;

        public a(b<?> bVar, kotlinx.coroutines.internal.b bVar2) {
            this.f7796c = bVar;
            this.f7797d = bVar2;
            bVar2.d(this);
        }

        private final void j(Object obj) {
            boolean z6 = obj == null;
            if (j.a(b.f7792j, this.f7796c, this, z6 ? null : g.e()) && z6) {
                this.f7796c.R();
            }
        }

        private final Object k() {
            b<?> bVar = this.f7796c;
            while (true) {
                Object obj = bVar._state;
                if (obj == this) {
                    return null;
                }
                if (obj instanceof w) {
                    ((w) obj).c(this.f7796c);
                } else {
                    if (obj != g.e()) {
                        return g.d();
                    }
                    if (j.a(b.f7792j, this.f7796c, g.e(), this)) {
                        return null;
                    }
                }
            }
        }

        private final void l() {
            j.a(b.f7792j, this.f7796c, this, g.e());
        }

        @Override // kotlinx.coroutines.internal.d
        public void d(Object obj, Object obj2) {
            j(obj2);
            this.f7797d.a(this, obj2);
        }

        @Override // kotlinx.coroutines.internal.d
        public long g() {
            return this.f7795b;
        }

        @Override // kotlinx.coroutines.internal.d
        public Object i(Object obj) {
            Object objK;
            if (obj == null && (objK = k()) != null) {
                return objK;
            }
            try {
                return this.f7797d.c(this);
            } catch (Throwable th) {
                if (obj == null) {
                    l();
                }
                throw th;
            }
        }

        @Override // kotlinx.coroutines.internal.w
        public String toString() {
            return "AtomicSelectOp(sequence=" + g() + ')';
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.selects.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Select.kt */
    private static final class C0111b extends o {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final v0 f7798i;

        public C0111b(v0 v0Var) {
            this.f7798i = v0Var;
        }
    }

    /* JADX INFO: compiled from: Select.kt */
    private static final class c extends w {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final o.c f7799a;

        public c(o.c cVar) {
            this.f7799a = cVar;
        }

        @Override // kotlinx.coroutines.internal.w
        public kotlinx.coroutines.internal.d<?> a() {
            return this.f7799a.a();
        }

        @Override // kotlinx.coroutines.internal.w
        public Object c(Object obj) {
            Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectBuilderImpl<*>");
            b bVar = (b) obj;
            this.f7799a.d();
            Object objE = this.f7799a.a().e(null);
            j.a(b.f7792j, bVar, this, objE == null ? this.f7799a.f7663c : g.e());
            return objE;
        }
    }

    /* JADX INFO: compiled from: Select.kt */
    private final class d extends n1<l1> {
        public d(l1 l1Var) {
            super(l1Var);
        }

        @Override // kotlinx.coroutines.a0
        public void Q(Throwable th) {
            if (b.this.j()) {
                b.this.u(this.f7715i.getCancellationException());
            }
        }

        @Override // r5.l
        public /* bridge */ /* synthetic */ r invoke(Throwable th) {
            Q(th);
            return r.f6563a;
        }

        @Override // kotlinx.coroutines.internal.o
        public String toString() {
            return "SelectOnCancelling[" + b.this + ']';
        }
    }

    /* JADX INFO: compiled from: Runnable.kt */
    public static final class e implements Runnable {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ l f7802g;

        public e(l lVar) {
            this.f7802g = lVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (b.this.j()) {
                b6.a.b(this.f7802g, b.this.i());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public b(k5.d<? super R> dVar) {
        this.f7794i = dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void R() {
        v0 v0VarS = S();
        if (v0VarS != null) {
            v0VarS.dispose();
        }
        Object objG = G();
        Objects.requireNonNull(objG, "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
        for (o oVarH = (o) objG; !kotlin.jvm.internal.l.a(oVarH, this); oVarH = oVarH.H()) {
            if (oVarH instanceof C0111b) {
                ((C0111b) oVarH).f7798i.dispose();
            }
        }
    }

    private final v0 S() {
        return (v0) this._parentHandle;
    }

    private final void V() {
        l1 l1Var = (l1) getContext().get(l1.f7703d);
        if (l1Var != null) {
            v0 v0VarE = l1.a.e(l1Var, true, false, new d(l1Var), 2, null);
            W(v0VarE);
            if (s()) {
                v0VarE.dispose();
            }
        }
    }

    private final void W(v0 v0Var) {
        this._parentHandle = v0Var;
    }

    public final Object T() {
        if (!s()) {
            V();
        }
        Object obj = this._result;
        if (obj == g.f7805c) {
            if (j.a(f7793k, this, g.f7805c, l5.d.d())) {
                return l5.d.d();
            }
            obj = this._result;
        }
        if (obj == g.f7806d) {
            throw new IllegalStateException("Already resumed");
        }
        if (obj instanceof y) {
            throw ((y) obj).f7851a;
        }
        return obj;
    }

    public final void U(Throwable th) {
        if (j()) {
            l.a aVar = g5.l.f6557e;
            resumeWith(g5.l.a(g5.m.a(th)));
        } else {
            if (th instanceof CancellationException) {
                return;
            }
            Object objT = T();
            if ((objT instanceof y) && ((y) objT).f7851a == th) {
                return;
            }
            g0.a(getContext(), th);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0031, code lost:
    
        R();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0036, code lost:
    
        return kotlinx.coroutines.l.f7702a;
     */
    @Override // kotlinx.coroutines.selects.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object b(kotlinx.coroutines.internal.o.c r4) {
        /*
            r3 = this;
        L0:
            java.lang.Object r0 = r3._state
            java.lang.Object r1 = kotlinx.coroutines.selects.g.e()
            r2 = 0
            if (r0 != r1) goto L37
            if (r4 != 0) goto L18
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = kotlinx.coroutines.selects.b.f7792j
            java.lang.Object r1 = kotlinx.coroutines.selects.g.e()
            boolean r0 = kotlinx.coroutines.j.a(r0, r3, r1, r2)
            if (r0 != 0) goto L31
            goto L0
        L18:
            kotlinx.coroutines.selects.b$c r0 = new kotlinx.coroutines.selects.b$c
            r0.<init>(r4)
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = kotlinx.coroutines.selects.b.f7792j
            java.lang.Object r2 = kotlinx.coroutines.selects.g.e()
            boolean r1 = kotlinx.coroutines.j.a(r1, r3, r2, r0)
            if (r1 != 0) goto L2a
            goto L0
        L2a:
            java.lang.Object r4 = r0.c(r3)
            if (r4 == 0) goto L31
            return r4
        L31:
            r3.R()
            kotlinx.coroutines.internal.c0 r4 = kotlinx.coroutines.l.f7702a
            return r4
        L37:
            boolean r1 = r0 instanceof kotlinx.coroutines.internal.w
            if (r1 == 0) goto L6b
            if (r4 == 0) goto L65
            kotlinx.coroutines.internal.d r1 = r4.a()
            boolean r2 = r1 instanceof kotlinx.coroutines.selects.b.a
            if (r2 == 0) goto L59
            r2 = r1
            kotlinx.coroutines.selects.b$a r2 = (kotlinx.coroutines.selects.b.a) r2
            kotlinx.coroutines.selects.b<?> r2 = r2.f7796c
            if (r2 == r3) goto L4d
            goto L59
        L4d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "Cannot use matching select clauses on the same object"
            java.lang.String r0 = r0.toString()
            r4.<init>(r0)
            throw r4
        L59:
            r2 = r0
            kotlinx.coroutines.internal.w r2 = (kotlinx.coroutines.internal.w) r2
            boolean r1 = r1.b(r2)
            if (r1 == 0) goto L65
            java.lang.Object r4 = kotlinx.coroutines.internal.c.f7623b
            return r4
        L65:
            kotlinx.coroutines.internal.w r0 = (kotlinx.coroutines.internal.w) r0
            r0.c(r3)
            goto L0
        L6b:
            if (r4 != 0) goto L6e
            return r2
        L6e:
            kotlinx.coroutines.internal.o$a r4 = r4.f7663c
            if (r0 != r4) goto L75
            kotlinx.coroutines.internal.c0 r4 = kotlinx.coroutines.l.f7702a
            return r4
        L75:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.selects.b.b(kotlinx.coroutines.internal.o$c):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.selects.a
    public <Q> void f(kotlinx.coroutines.selects.d<? extends Q> dVar, p<? super Q, ? super k5.d<? super R>, ? extends Object> pVar) {
        dVar.b(this, pVar);
    }

    @Override // k5.d
    public k5.g getContext() {
        return this.f7794i.getContext();
    }

    @Override // kotlinx.coroutines.selects.f
    public k5.d<R> i() {
        return this;
    }

    @Override // kotlinx.coroutines.selects.f
    public boolean j() {
        Object objB = b(null);
        if (objB == kotlinx.coroutines.l.f7702a) {
            return true;
        }
        if (objB == null) {
            return false;
        }
        throw new IllegalStateException(("Unexpected trySelectIdempotent result " + objB).toString());
    }

    @Override // kotlinx.coroutines.selects.a
    public void n(long j7, r5.l<? super k5.d<? super R>, ? extends Object> lVar) {
        if (j7 > 0) {
            o(r0.b(getContext()).N(j7, new e(lVar), getContext()));
        } else if (j()) {
            b6.b.c(lVar, i());
        }
    }

    @Override // kotlinx.coroutines.selects.f
    public void o(v0 v0Var) {
        C0111b c0111b = new C0111b(v0Var);
        if (!s()) {
            A(c0111b);
            if (!s()) {
                return;
            }
        }
        v0Var.dispose();
    }

    @Override // k5.d
    public void resumeWith(Object obj) {
        while (true) {
            Object obj2 = this._result;
            if (obj2 == g.f7805c) {
                if (j.a(f7793k, this, g.f7805c, c0.d(obj, null, 1, null))) {
                    return;
                }
            } else {
                if (obj2 != l5.d.d()) {
                    throw new IllegalStateException("Already resumed");
                }
                if (j.a(f7793k, this, l5.d.d(), g.f7806d)) {
                    if (!g5.l.c(obj)) {
                        this.f7794i.resumeWith(obj);
                        return;
                    }
                    k5.d<R> dVar = this.f7794i;
                    Throwable thB = g5.l.b(obj);
                    kotlin.jvm.internal.l.c(thB);
                    l.a aVar = g5.l.f6557e;
                    dVar.resumeWith(g5.l.a(g5.m.a(thB)));
                    return;
                }
            }
        }
    }

    @Override // kotlinx.coroutines.selects.f
    public boolean s() {
        while (true) {
            Object obj = this._state;
            if (obj == g.e()) {
                return false;
            }
            if (!(obj instanceof w)) {
                return true;
            }
            ((w) obj).c(this);
        }
    }

    @Override // kotlinx.coroutines.internal.o
    public String toString() {
        return "SelectInstance(state=" + this._state + ", result=" + this._result + ')';
    }

    @Override // kotlinx.coroutines.selects.f
    public void u(Throwable th) {
        while (true) {
            Object obj = this._result;
            if (obj == g.f7805c) {
                if (j.a(f7793k, this, g.f7805c, new y(th, false, 2, null))) {
                    return;
                }
            } else {
                if (obj != l5.d.d()) {
                    throw new IllegalStateException("Already resumed");
                }
                if (j.a(f7793k, this, l5.d.d(), g.f7806d)) {
                    k5.d dVarC = l5.c.c(this.f7794i);
                    l.a aVar = g5.l.f6557e;
                    dVarC.resumeWith(g5.l.a(g5.m.a(th)));
                    return;
                }
            }
        }
    }

    @Override // kotlinx.coroutines.selects.f
    public Object w(kotlinx.coroutines.internal.b bVar) {
        return new a(this, bVar).c(null);
    }
}
