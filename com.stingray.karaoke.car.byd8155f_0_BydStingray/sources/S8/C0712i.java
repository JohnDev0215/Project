package S8;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import r7.EnumC2303a;
import s7.InterfaceC2411d;
import z7.InterfaceC2782k;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: S8.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0712i extends D implements InterfaceC0710g, InterfaceC2411d, v0 {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f8115q = AtomicIntegerFieldUpdater.newUpdater(C0712i.class, "_decisionAndIndex$volatile");

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f8116r = AtomicReferenceFieldUpdater.newUpdater(C0712i.class, Object.class, "_state$volatile");

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f8117s = AtomicReferenceFieldUpdater.newUpdater(C0712i.class, Object.class, "_parentHandle$volatile");
    private volatile /* synthetic */ int _decisionAndIndex$volatile;
    private volatile /* synthetic */ Object _parentHandle$volatile;
    private volatile /* synthetic */ Object _state$volatile;
    public final InterfaceC2189c o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final InterfaceC2194h f8118p;

    public C0712i(int i7, InterfaceC2189c interfaceC2189c) {
        super(i7);
        this.o = interfaceC2189c;
        this.f8118p = interfaceC2189c.getContext();
        this._decisionAndIndex$volatile = 536870911;
        this._state$volatile = C0705b.l;
    }

    public static Object D(i0 i0Var, Object obj, int i7, InterfaceC2786o interfaceC2786o) {
        if ((obj instanceof C0720q) || !AbstractC0727y.u(i7)) {
            return obj;
        }
        if (interfaceC2786o != null || (i0Var instanceof InterfaceC0709f)) {
            return new C0719p(obj, i0Var instanceof InterfaceC0709f ? (InterfaceC0709f) i0Var : null, interfaceC2786o, (CancellationException) null, 16);
        }
        return obj;
    }

    public static void y(i0 i0Var, Object obj) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + i0Var + ", already has " + obj).toString());
    }

    public final void A() {
        InterfaceC2189c interfaceC2189c = this.o;
        Throwable th = null;
        X8.f fVar = interfaceC2189c instanceof X8.f ? (X8.f) interfaceC2189c : null;
        if (fVar != null) {
            loop0: while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = X8.f.f10554s;
                Object obj = atomicReferenceFieldUpdater.get(fVar);
                B2.q qVar = X8.a.c;
                if (obj == qVar) {
                    while (!atomicReferenceFieldUpdater.compareAndSet(fVar, qVar, this)) {
                        if (atomicReferenceFieldUpdater.get(fVar) != qVar) {
                            break;
                        }
                    }
                    break loop0;
                } else {
                    if (!(obj instanceof Throwable)) {
                        throw new IllegalStateException(("Inconsistent state " + obj).toString());
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(fVar, obj, null)) {
                        if (atomicReferenceFieldUpdater.get(fVar) != obj) {
                            throw new IllegalArgumentException("Failed requirement.");
                        }
                    }
                    th = (Throwable) obj;
                }
            }
            if (th == null) {
                return;
            }
            m();
            l(th);
        }
    }

    public final void B(Object obj, int i7, InterfaceC2786o interfaceC2786o) throws IllegalAccessException, InvocationTargetException {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8116r;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 instanceof i0) {
                Object objD = D((i0) obj2, obj, i7, interfaceC2786o);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, objD)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj2) {
                        break;
                    }
                }
                if (!x()) {
                    m();
                }
                o(i7);
                return;
            }
            if (obj2 instanceof C0713j) {
                C0713j c0713j = (C0713j) obj2;
                c0713j.getClass();
                if (C0713j.c.compareAndSet(c0713j, 0, 1)) {
                    if (interfaceC2786o != null) {
                        j(interfaceC2786o, c0713j.f8130a, obj);
                        return;
                    }
                    return;
                }
            }
            throw new IllegalStateException(("Already resumed, but proposed with update " + obj).toString());
        }
    }

    public final void C(AbstractC0721s abstractC0721s) throws IllegalAccessException, InvocationTargetException {
        m7.q qVar = m7.q.f19267a;
        InterfaceC2189c interfaceC2189c = this.o;
        X8.f fVar = interfaceC2189c instanceof X8.f ? (X8.f) interfaceC2189c : null;
        B(qVar, (fVar != null ? fVar.o : null) == abstractC0721s ? 4 : this.f8083n, null);
    }

    @Override // S8.D
    public final void a(CancellationException cancellationException) throws IllegalAccessException, InvocationTargetException {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8116r;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj instanceof i0) {
                throw new IllegalStateException("Not completed");
            }
            if (obj instanceof C0720q) {
                return;
            }
            if (!(obj instanceof C0719p)) {
                C0719p c0719p = new C0719p(obj, (InterfaceC0709f) null, (InterfaceC2786o) null, cancellationException, 14);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, c0719p)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                        break;
                    }
                }
                return;
            }
            C0719p c0719p2 = (C0719p) obj;
            if (c0719p2.f8128e != null) {
                throw new IllegalStateException("Must be called at most once");
            }
            C0719p c0719pA = C0719p.a(c0719p2, null, cancellationException, 15);
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, c0719pA)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                    break;
                }
            }
            InterfaceC0709f interfaceC0709f = c0719p2.f8127b;
            if (interfaceC0709f != null) {
                h(interfaceC0709f, cancellationException);
            }
            InterfaceC2786o interfaceC2786o = c0719p2.c;
            if (interfaceC2786o != null) {
                j(interfaceC2786o, cancellationException, c0719p2.f8126a);
                return;
            }
            return;
        }
    }

    @Override // S8.v0
    public final void b(X8.r rVar, int i7) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i10;
        do {
            atomicIntegerFieldUpdater = f8115q;
            i10 = atomicIntegerFieldUpdater.get(this);
            if ((i10 & 536870911) != 536870911) {
                throw new IllegalStateException("invokeOnCancellation should be called at most once");
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i10, ((i10 >> 29) << 29) + i7));
        v(rVar);
    }

    @Override // S8.D
    public final InterfaceC2189c c() {
        return this.o;
    }

    @Override // S8.D
    public final Throwable d(Object obj) {
        Throwable thD = super.d(obj);
        if (thD != null) {
            return thD;
        }
        return null;
    }

    @Override // S8.D
    public final Object e(Object obj) {
        return obj instanceof C0719p ? ((C0719p) obj).f8126a : obj;
    }

    @Override // S8.D
    public final Object g() {
        return f8116r.get(this);
    }

    @Override // s7.InterfaceC2411d
    public final InterfaceC2411d getCallerFrame() {
        InterfaceC2189c interfaceC2189c = this.o;
        if (interfaceC2189c instanceof InterfaceC2411d) {
            return (InterfaceC2411d) interfaceC2189c;
        }
        return null;
    }

    @Override // q7.InterfaceC2189c
    public final InterfaceC2194h getContext() {
        return this.f8118p;
    }

    public final void h(InterfaceC0709f interfaceC0709f, Throwable th) throws IllegalAccessException, InvocationTargetException {
        try {
            interfaceC0709f.a(th);
        } catch (Throwable th2) {
            AbstractC0727y.q(new A9.k("Exception in invokeOnCancellation handler for " + this, th2), this.f8118p);
        }
    }

    @Override // S8.InterfaceC0710g
    public final B2.q i(Object obj, InterfaceC2786o interfaceC2786o) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8116r;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            boolean z6 = obj2 instanceof i0;
            B2.q qVar = AbstractC0727y.f8144a;
            if (!z6) {
                boolean z10 = obj2 instanceof C0719p;
                return null;
            }
            Object objD = D((i0) obj2, obj, this.f8083n, interfaceC2786o);
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, objD)) {
                if (atomicReferenceFieldUpdater.get(this) != obj2) {
                    break;
                }
            }
            if (x()) {
                return qVar;
            }
            m();
            return qVar;
        }
    }

    public final void j(InterfaceC2786o interfaceC2786o, Throwable th, Object obj) throws IllegalAccessException, InvocationTargetException {
        InterfaceC2194h interfaceC2194h = this.f8118p;
        try {
            interfaceC2786o.g(th, obj, interfaceC2194h);
        } catch (Throwable th2) {
            AbstractC0727y.q(new A9.k("Exception in resume onCancellation handler for " + this, th2), interfaceC2194h);
        }
    }

    public final void k(X8.r rVar, Throwable th) throws IllegalAccessException, InvocationTargetException {
        InterfaceC2194h interfaceC2194h = this.f8118p;
        int i7 = f8115q.get(this) & 536870911;
        if (i7 == 536870911) {
            throw new IllegalStateException("The index for Segment.onCancellation(..) is broken");
        }
        try {
            rVar.h(i7, interfaceC2194h);
        } catch (Throwable th2) {
            AbstractC0727y.q(new A9.k("Exception in invokeOnCancellation handler for " + this, th2), interfaceC2194h);
        }
    }

    @Override // S8.InterfaceC0710g
    public final boolean l(Throwable th) throws IllegalAccessException, InvocationTargetException {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8116r;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (!(obj instanceof i0)) {
                return false;
            }
            C0713j c0713j = new C0713j(this, th, (obj instanceof InterfaceC0709f) || (obj instanceof X8.r));
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, c0713j)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                    break;
                }
            }
            i0 i0Var = (i0) obj;
            if (i0Var instanceof InterfaceC0709f) {
                h((InterfaceC0709f) obj, th);
            } else if (i0Var instanceof X8.r) {
                k((X8.r) obj, th);
            }
            if (!x()) {
                m();
            }
            o(this.f8083n);
            return true;
        }
    }

    public final void m() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8117s;
        F f10 = (F) atomicReferenceFieldUpdater.get(this);
        if (f10 == null) {
            return;
        }
        f10.a();
        atomicReferenceFieldUpdater.set(this, h0.l);
    }

    @Override // S8.InterfaceC0710g
    public final void n(Object obj, InterfaceC2786o interfaceC2786o) throws IllegalAccessException, InvocationTargetException {
        B(obj, this.f8083n, interfaceC2786o);
    }

    public final void o(int i7) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i10;
        do {
            atomicIntegerFieldUpdater = f8115q;
            i10 = atomicIntegerFieldUpdater.get(this);
            int i11 = i10 >> 29;
            if (i11 != 0) {
                if (i11 != 1) {
                    throw new IllegalStateException("Already resumed");
                }
                InterfaceC2189c interfaceC2189c = this.o;
                boolean z6 = i7 == 4;
                if (z6 || !(interfaceC2189c instanceof X8.f) || AbstractC0727y.u(i7) != AbstractC0727y.u(this.f8083n)) {
                    AbstractC0727y.z(this, interfaceC2189c, z6);
                    return;
                }
                X8.f fVar = (X8.f) interfaceC2189c;
                AbstractC0721s abstractC0721s = fVar.o;
                InterfaceC2194h context = fVar.f10555p.getContext();
                if (abstractC0721s.N(context)) {
                    abstractC0721s.L(context, this);
                    return;
                }
                N nA = n0.a();
                if (nA.T()) {
                    nA.Q(this);
                    return;
                }
                nA.S(true);
                try {
                    AbstractC0727y.z(this, interfaceC2189c, true);
                    do {
                    } while (nA.V());
                } finally {
                    try {
                    } finally {
                    }
                }
                return;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i10, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED + (536870911 & i10)));
    }

    public Throwable p(d0 d0Var) {
        return d0Var.y();
    }

    public final Object q() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i7;
        boolean zX = x();
        do {
            atomicIntegerFieldUpdater = f8115q;
            i7 = atomicIntegerFieldUpdater.get(this);
            int i10 = i7 >> 29;
            if (i10 != 0) {
                if (i10 != 2) {
                    throw new IllegalStateException("Already suspended");
                }
                if (zX) {
                    A();
                }
                Object obj = f8116r.get(this);
                if (obj instanceof C0720q) {
                    throw ((C0720q) obj).f8130a;
                }
                if (AbstractC0727y.u(this.f8083n)) {
                    W w10 = (W) this.f8118p.F(C0722t.f8137m);
                    if (w10 != null && !w10.b()) {
                        CancellationException cancellationExceptionY = w10.y();
                        a(cancellationExceptionY);
                        throw cancellationExceptionY;
                    }
                }
                return e(obj);
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i7, 536870912 + (536870911 & i7)));
        if (((F) f8117s.get(this)) == null) {
            t();
        }
        if (zX) {
            A();
        }
        return EnumC2303a.l;
    }

    @Override // S8.InterfaceC0710g
    public final void r(Object obj) {
        o(this.f8083n);
    }

    @Override // q7.InterfaceC2189c
    public final void resumeWith(Object obj) {
        Throwable thA = m7.l.a(obj);
        if (thA != null) {
            obj = new C0720q(thA, false);
        }
        B(obj, this.f8083n, null);
    }

    public final void s() {
        F fT = t();
        if (fT != null && w()) {
            fT.a();
            f8117s.set(this, h0.l);
        }
    }

    public final F t() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        W w10 = (W) this.f8118p.F(C0722t.f8137m);
        if (w10 == null) {
            return null;
        }
        F fR = AbstractC0727y.r(w10, true, new C0714k(this, 0));
        do {
            atomicReferenceFieldUpdater = f8117s;
            if (atomicReferenceFieldUpdater.compareAndSet(this, null, fR)) {
                break;
            }
        } while (atomicReferenceFieldUpdater.get(this) == null);
        return fR;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(z());
        sb.append('(');
        sb.append(AbstractC0727y.C(this.o));
        sb.append("){");
        Object obj = f8116r.get(this);
        sb.append(obj instanceof i0 ? "Active" : obj instanceof C0713j ? "Cancelled" : "Completed");
        sb.append("}@");
        sb.append(AbstractC0727y.n(this));
        return sb.toString();
    }

    public final void u(InterfaceC2782k interfaceC2782k) {
        v(new C0708e(interfaceC2782k, 0));
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x00ba, code lost:
    
        y(r10, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00bd, code lost:
    
        throw null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void v(S8.i0 r10) {
        /*
            r9 = this;
        L0:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = S8.C0712i.f8116r
            java.lang.Object r7 = r0.get(r9)
            boolean r1 = r7 instanceof S8.C0705b
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
            boolean r1 = r7 instanceof S8.InterfaceC0709f
            r2 = 0
            if (r1 != 0) goto Lba
            boolean r1 = r7 instanceof X8.r
            if (r1 != 0) goto Lba
            boolean r1 = r7 instanceof S8.C0720q
            if (r1 == 0) goto L5c
            r0 = r7
            S8.q r0 = (S8.C0720q) r0
            r0.getClass()
            r1 = 1
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r3 = S8.C0720q.f8129b
            r4 = 0
            boolean r1 = r3.compareAndSet(r0, r4, r1)
            if (r1 == 0) goto L58
            boolean r1 = r7 instanceof S8.C0713j
            if (r1 == 0) goto L57
            boolean r1 = r7 instanceof S8.C0720q
            if (r1 == 0) goto L3e
            goto L3f
        L3e:
            r0 = r2
        L3f:
            if (r0 == 0) goto L43
            java.lang.Throwable r2 = r0.f8130a
        L43:
            boolean r0 = r10 instanceof S8.InterfaceC0709f
            if (r0 == 0) goto L4d
            S8.f r10 = (S8.InterfaceC0709f) r10
            r9.h(r10, r2)
            goto L57
        L4d:
            java.lang.String r0 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Segment<*>"
            A7.m.d(r10, r0)
            X8.r r10 = (X8.r) r10
            r9.k(r10, r2)
        L57:
            return
        L58:
            y(r10, r7)
            throw r2
        L5c:
            boolean r1 = r7 instanceof S8.C0719p
            java.lang.String r3 = "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler"
            if (r1 == 0) goto L95
            r1 = r7
            S8.p r1 = (S8.C0719p) r1
            S8.f r4 = r1.f8127b
            if (r4 != 0) goto L91
            boolean r4 = r10 instanceof X8.r
            if (r4 == 0) goto L6e
            return
        L6e:
            A7.m.d(r10, r3)
            r3 = r10
            S8.f r3 = (S8.InterfaceC0709f) r3
            java.lang.Throwable r4 = r1.f8128e
            if (r4 == 0) goto L7c
            r9.h(r3, r4)
            return
        L7c:
            r4 = 29
            S8.p r1 = S8.C0719p.a(r1, r3, r2, r4)
        L82:
            boolean r2 = r0.compareAndSet(r9, r7, r1)
            if (r2 == 0) goto L89
            return
        L89:
            java.lang.Object r2 = r0.get(r9)
            if (r2 == r7) goto L82
            goto L0
        L91:
            y(r10, r7)
            throw r2
        L95:
            boolean r1 = r10 instanceof X8.r
            if (r1 == 0) goto L9a
            return
        L9a:
            A7.m.d(r10, r3)
            r3 = r10
            S8.f r3 = (S8.InterfaceC0709f) r3
            S8.p r8 = new S8.p
            r4 = 0
            r5 = 0
            r6 = 28
            r1 = r8
            r2 = r7
            r1.<init>(r2, r3, r4, r5, r6)
        Lab:
            boolean r1 = r0.compareAndSet(r9, r7, r8)
            if (r1 == 0) goto Lb2
            return
        Lb2:
            java.lang.Object r1 = r0.get(r9)
            if (r1 == r7) goto Lab
            goto L0
        Lba:
            y(r10, r7)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: S8.C0712i.v(S8.i0):void");
    }

    public final boolean w() {
        return !(f8116r.get(this) instanceof i0);
    }

    public final boolean x() {
        if (this.f8083n == 2) {
            InterfaceC2189c interfaceC2189c = this.o;
            A7.m.d(interfaceC2189c, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
            if (X8.f.f10554s.get((X8.f) interfaceC2189c) != null) {
                return true;
            }
        }
        return false;
    }

    public String z() {
        return "CancellableContinuation";
    }
}
