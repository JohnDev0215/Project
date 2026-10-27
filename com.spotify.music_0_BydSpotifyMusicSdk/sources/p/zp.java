package p;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Result;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.CompletionHandlerException;

/* JADX INFO: loaded from: classes.dex */
public class zp extends ga0 implements yp, f20, qv2 {
    public static final AtomicIntegerFieldUpdater n = AtomicIntegerFieldUpdater.newUpdater(zp.class, "_decisionAndIndex");
    public static final AtomicReferenceFieldUpdater o = AtomicReferenceFieldUpdater.newUpdater(zp.class, Object.class, "_state");

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f218p = AtomicReferenceFieldUpdater.newUpdater(zp.class, Object.class, "_parentHandle");
    private volatile int _decisionAndIndex;
    private volatile Object _parentHandle;
    private volatile Object _state;
    public final c00 l;
    public final y10 m;

    public zp(int i, c00 c00Var) {
        super(i);
        this.l = c00Var;
        this.m = c00Var.getContext();
        this._decisionAndIndex = 536870911;
        this._state = m4.c;
    }

    public static Object C(un1 un1Var, Object obj, int i, ry0 ry0Var) {
        if ((obj instanceof hv) || !v03.x(i)) {
            return obj;
        }
        if (ry0Var != null || (un1Var instanceof sp)) {
            return new fv(obj, un1Var instanceof sp ? (sp) un1Var : null, ry0Var, (CancellationException) null, 16);
        }
        return obj;
    }

    public static void w(Object obj, Object obj2) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + obj + ", already has " + obj2).toString());
    }

    public final void A(Object obj, int i, ry0 ry0Var) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = o;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 instanceof un1) {
                Object objC = C((un1) obj2, obj, i, ry0Var);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, objC)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj2) {
                        break;
                    }
                }
                if (!v()) {
                    n();
                }
                o(i);
                return;
            }
            if (obj2 instanceof dq) {
                dq dqVar = (dq) obj2;
                dqVar.getClass();
                if (dq.c.compareAndSet(dqVar, 0, 1)) {
                    if (ry0Var != null) {
                        j(ry0Var, dqVar.a);
                        return;
                    }
                    return;
                }
            }
            throw new IllegalStateException(("Already resumed, but proposed with update " + obj).toString());
        }
    }

    public final void B(kotlinx.coroutines.b bVar) {
        lq2 lq2Var = lq2.a;
        c00 c00Var = this.l;
        ea0 ea0Var = c00Var instanceof ea0 ? (ea0) c00Var : null;
        A(lq2Var, (ea0Var != null ? ea0Var.l : null) == bVar ? 4 : this.k, null);
    }

    public final tz2 D(Object obj, ry0 ry0Var) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = o;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (!(obj2 instanceof un1)) {
                boolean z = obj2 instanceof fv;
                return null;
            }
            Object objC = C((un1) obj2, obj, this.k, ry0Var);
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, objC)) {
                if (atomicReferenceFieldUpdater.get(this) != obj2) {
                    break;
                }
            }
            if (!v()) {
                n();
            }
            return aq.a;
        }
    }

    @Override // p.qv2
    public final void a(cb2 cb2Var, int i) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i2;
        do {
            atomicIntegerFieldUpdater = n;
            i2 = atomicIntegerFieldUpdater.get(this);
            if ((i2 & 536870911) != 536870911) {
                throw new IllegalStateException("invokeOnCancellation should be called at most once".toString());
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, ((i2 >> 29) << 29) + i));
        u(cb2Var);
    }

    @Override // p.ga0
    public final void b(Object obj, CancellationException cancellationException) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = o;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 instanceof un1) {
                throw new IllegalStateException("Not completed".toString());
            }
            if (obj2 instanceof hv) {
                return;
            }
            if (!(obj2 instanceof fv)) {
                fv fvVar = new fv(obj2, (sp) null, (ry0) null, cancellationException, 14);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, fvVar)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj2) {
                        break;
                    }
                }
                return;
            }
            fv fvVar2 = (fv) obj2;
            if (!(!(fvVar2.e != null))) {
                throw new IllegalStateException("Must be called at most once".toString());
            }
            fv fvVarA = fv.a(fvVar2, null, cancellationException, 15);
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, fvVarA)) {
                if (atomicReferenceFieldUpdater.get(this) != obj2) {
                    break;
                }
            }
            sp spVar = fvVar2.b;
            if (spVar != null) {
                i(spVar, cancellationException);
            }
            ry0 ry0Var = fvVar2.c;
            if (ry0Var != null) {
                j(ry0Var, cancellationException);
                return;
            }
            return;
        }
    }

    @Override // p.ga0
    public final c00 c() {
        return this.l;
    }

    @Override // p.ga0
    public final Throwable d(Object obj) {
        Throwable thD = super.d(obj);
        if (thD != null) {
            return thD;
        }
        return null;
    }

    @Override // p.ga0
    public final Object e(Object obj) {
        return obj instanceof fv ? ((fv) obj).a : obj;
    }

    @Override // p.f20
    public final f20 f() {
        c00 c00Var = this.l;
        if (c00Var instanceof f20) {
            return (f20) c00Var;
        }
        return null;
    }

    @Override // p.c00
    public final y10 getContext() {
        return this.m;
    }

    @Override // p.ga0
    public final Object h() {
        return o.get(this);
    }

    public final void i(sp spVar, Throwable th) {
        try {
            spVar.a(th);
        } catch (Throwable th2) {
            ux2.m(this.m, new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    public final void j(ry0 ry0Var, Throwable th) {
        try {
            ry0Var.invoke(th);
        } catch (Throwable th2) {
            ux2.m(this.m, new CompletionHandlerException("Exception in resume onCancellation handler for " + this, th2));
        }
    }

    @Override // p.c00
    public final void k(Object obj) {
        Throwable thA = Result.a(obj);
        if (thA != null) {
            obj = new hv(thA, false);
        }
        A(obj, this.k, null);
    }

    public final void l(cb2 cb2Var, Throwable th) {
        y10 y10Var = this.m;
        int i = n.get(this) & 536870911;
        if (i == 536870911) {
            throw new IllegalStateException("The index for Segment.onCancellation(..) is broken".toString());
        }
        try {
            cb2Var.g(i, y10Var);
        } catch (Throwable th2) {
            ux2.m(y10Var, new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    public final void m(Throwable th) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = o;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj instanceof un1) {
                dq dqVar = new dq(this, th, (obj instanceof sp) || (obj instanceof cb2));
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, dqVar)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                        break;
                    }
                }
                un1 un1Var = (un1) obj;
                if (un1Var instanceof sp) {
                    i((sp) obj, th);
                } else if (un1Var instanceof cb2) {
                    l((cb2) obj, th);
                }
                if (!v()) {
                    n();
                }
                o(this.k);
                return;
            }
            return;
        }
    }

    public final void n() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f218p;
        oa0 oa0Var = (oa0) atomicReferenceFieldUpdater.get(this);
        if (oa0Var == null) {
            return;
        }
        oa0Var.dispose();
        atomicReferenceFieldUpdater.set(this, rn1.c);
    }

    public final void o(int i) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i2;
        do {
            atomicIntegerFieldUpdater = n;
            i2 = atomicIntegerFieldUpdater.get(this);
            int i3 = i2 >> 29;
            if (i3 != 0) {
                if (i3 != 1) {
                    throw new IllegalStateException("Already resumed".toString());
                }
                boolean z = i == 4;
                c00 c00Var = this.l;
                if (z || !(c00Var instanceof ea0) || v03.x(i) != v03.x(this.k)) {
                    v03.H(this, c00Var, z);
                    return;
                }
                kotlinx.coroutines.b bVar = ((ea0) c00Var).l;
                y10 context = c00Var.getContext();
                if (bVar.I0()) {
                    bVar.G0(context, this);
                    return;
                }
                pi0 pi0VarA = kk2.a();
                if (pi0VarA.O0()) {
                    pi0VarA.L0(this);
                    return;
                }
                pi0VarA.N0(true);
                try {
                    v03.H(this, c00Var, true);
                    do {
                    } while (pi0VarA.Q0());
                } finally {
                    try {
                    } finally {
                    }
                }
                return;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, 1073741824 + (536870911 & i2)));
    }

    public Throwable p(kotlinx.coroutines.g gVar) {
        return gVar.B();
    }

    public final Object q() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i;
        boolean zV = v();
        do {
            atomicIntegerFieldUpdater = n;
            i = atomicIntegerFieldUpdater.get(this);
            int i2 = i >> 29;
            if (i2 != 0) {
                if (i2 != 2) {
                    throw new IllegalStateException("Already suspended".toString());
                }
                if (zV) {
                    y();
                }
                Object obj = o.get(this);
                if (obj instanceof hv) {
                    throw ((hv) obj).a;
                }
                if (v03.x(this.k)) {
                    p61 p61Var = (p61) this.m.E0(qo.o);
                    if (p61Var != null && !p61Var.b()) {
                        CancellationException cancellationExceptionB = ((kotlinx.coroutines.g) p61Var).B();
                        b(obj, cancellationExceptionB);
                        throw cancellationExceptionB;
                    }
                }
                return e(obj);
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i, 536870912 + (536870911 & i)));
        if (((oa0) f218p.get(this)) == null) {
            s();
        }
        if (zV) {
            y();
        }
        return CoroutineSingletons.c;
    }

    public final void r() {
        oa0 oa0VarS = s();
        if (oa0VarS != null && (!(o.get(this) instanceof un1))) {
            oa0VarS.dispose();
            f218p.set(this, rn1.c);
        }
    }

    public final oa0 s() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        p61 p61Var = (p61) this.m.E0(qo.o);
        if (p61Var == null) {
            return null;
        }
        oa0 oa0VarM = q30.m(p61Var, true, new es(this), 2);
        do {
            atomicReferenceFieldUpdater = f218p;
            if (atomicReferenceFieldUpdater.compareAndSet(this, null, oa0VarM)) {
                break;
            }
        } while (atomicReferenceFieldUpdater.get(this) == null);
        return oa0VarM;
    }

    public final void t(ry0 ry0Var) {
        u(ry0Var instanceof sp ? (sp) ry0Var : new rp(2, ry0Var));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(x());
        sb.append('(');
        sb.append(f60.P(this.l));
        sb.append("){");
        Object obj = o.get(this);
        sb.append(obj instanceof un1 ? "Active" : obj instanceof dq ? "Cancelled" : "Completed");
        sb.append("}@");
        sb.append(f60.s(this));
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x00b8, code lost:
    
        w(r10, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00bb, code lost:
    
        throw null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void u(java.lang.Object r10) {
        /*
            r9 = this;
        L0:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = p.zp.o
            java.lang.Object r7 = r0.get(r9)
            boolean r1 = r7 instanceof p.m4
            if (r1 == 0) goto L18
        La:
            boolean r1 = r0.compareAndSet(r9, r7, r10)
            if (r1 == 0) goto L11
            return
        L11:
            java.lang.Object r1 = r0.get(r9)
            if (r1 == r7) goto La
            goto L0
        L18:
            boolean r1 = r7 instanceof p.sp
            r2 = 0
            if (r1 != 0) goto Lb8
            boolean r1 = r7 instanceof p.cb2
            if (r1 != 0) goto Lb8
            boolean r1 = r7 instanceof p.hv
            if (r1 == 0) goto L5a
            r0 = r7
            p.hv r0 = (p.hv) r0
            r0.getClass()
            r3 = 1
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r4 = p.hv.b
            r5 = 0
            boolean r3 = r4.compareAndSet(r0, r5, r3)
            if (r3 == 0) goto L56
            boolean r3 = r7 instanceof p.dq
            if (r3 == 0) goto L55
            if (r1 == 0) goto L3c
            goto L3d
        L3c:
            r0 = r2
        L3d:
            if (r0 == 0) goto L41
            java.lang.Throwable r2 = r0.a
        L41:
            boolean r0 = r10 instanceof p.sp
            if (r0 == 0) goto L4b
            p.sp r10 = (p.sp) r10
            r9.i(r10, r2)
            goto L55
        L4b:
            java.lang.String r0 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Segment<*>"
            p.v03.g(r10, r0)
            p.cb2 r10 = (p.cb2) r10
            r9.l(r10, r2)
        L55:
            return
        L56:
            w(r10, r7)
            throw r2
        L5a:
            boolean r1 = r7 instanceof p.fv
            java.lang.String r3 = "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler"
            if (r1 == 0) goto L93
            r1 = r7
            p.fv r1 = (p.fv) r1
            p.sp r4 = r1.b
            if (r4 != 0) goto L8f
            boolean r4 = r10 instanceof p.cb2
            if (r4 == 0) goto L6c
            return
        L6c:
            p.v03.g(r10, r3)
            r3 = r10
            p.sp r3 = (p.sp) r3
            java.lang.Throwable r4 = r1.e
            if (r4 == 0) goto L7a
            r9.i(r3, r4)
            return
        L7a:
            r4 = 29
            p.fv r1 = p.fv.a(r1, r3, r2, r4)
        L80:
            boolean r2 = r0.compareAndSet(r9, r7, r1)
            if (r2 == 0) goto L87
            return
        L87:
            java.lang.Object r2 = r0.get(r9)
            if (r2 == r7) goto L80
            goto L0
        L8f:
            w(r10, r7)
            throw r2
        L93:
            boolean r1 = r10 instanceof p.cb2
            if (r1 == 0) goto L98
            return
        L98:
            p.v03.g(r10, r3)
            r3 = r10
            p.sp r3 = (p.sp) r3
            p.fv r8 = new p.fv
            r4 = 0
            r5 = 0
            r6 = 28
            r1 = r8
            r2 = r7
            r1.<init>(r2, r3, r4, r5, r6)
        La9:
            boolean r1 = r0.compareAndSet(r9, r7, r8)
            if (r1 == 0) goto Lb0
            return
        Lb0:
            java.lang.Object r1 = r0.get(r9)
            if (r1 == r7) goto La9
            goto L0
        Lb8:
            w(r10, r7)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p.zp.u(java.lang.Object):void");
    }

    public final boolean v() {
        if (this.k == 2) {
            c00 c00Var = this.l;
            v03.g(c00Var, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
            if (ea0.f120p.get((ea0) c00Var) != null) {
                return true;
            }
        }
        return false;
    }

    public String x() {
        return "CancellableContinuation";
    }

    public final void y() {
        c00 c00Var = this.l;
        Throwable th = null;
        ea0 ea0Var = c00Var instanceof ea0 ? (ea0) c00Var : null;
        if (ea0Var != null) {
            loop0: while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = ea0.f120p;
                Object obj = atomicReferenceFieldUpdater.get(ea0Var);
                tz2 tz2Var = fa0.b;
                if (obj == tz2Var) {
                    while (!atomicReferenceFieldUpdater.compareAndSet(ea0Var, tz2Var, this)) {
                        if (atomicReferenceFieldUpdater.get(ea0Var) != tz2Var) {
                            break;
                        }
                    }
                    break loop0;
                } else {
                    if (!(obj instanceof Throwable)) {
                        throw new IllegalStateException(("Inconsistent state " + obj).toString());
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(ea0Var, obj, null)) {
                        if (atomicReferenceFieldUpdater.get(ea0Var) != obj) {
                            throw new IllegalArgumentException("Failed requirement.".toString());
                        }
                    }
                    th = (Throwable) obj;
                }
            }
            if (th == null) {
                return;
            }
            n();
            m(th);
        }
    }

    public final void z(Object obj, ry0 ry0Var) {
        A(obj, this.k, ry0Var);
    }
}
