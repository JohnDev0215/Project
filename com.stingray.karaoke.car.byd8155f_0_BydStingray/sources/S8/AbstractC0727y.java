package S8;

import F0.C0223q;
import Q5.C0576h2;
import X5.C0884t;
import h8.AbstractC1386c;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import q7.AbstractC2187a;
import q7.C2190d;
import q7.C2195i;
import q7.InterfaceC2189c;
import q7.InterfaceC2191e;
import q7.InterfaceC2192f;
import q7.InterfaceC2193g;
import q7.InterfaceC2194h;
import r7.EnumC2303a;
import s7.AbstractC2408a;
import s7.AbstractC2410c;
import s7.InterfaceC2411d;
import x3.AbstractC2673a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: S8.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0727y {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final B2.q f8145b;
    public static final B2.q c;
    public static final B2.q d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final B2.q f8146e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final B2.q f8147f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final B2.q f8148g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final B2.q f8149h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final B2.q f8144a = new B2.q("RESUME_TOKEN", 3);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final H f8150i = new H(false);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final H f8151j = new H(true);

    static {
        int i7 = 3;
        f8145b = new B2.q("REMOVED_TASK", i7);
        c = new B2.q("CLOSED_EMPTY", i7);
        int i10 = 3;
        d = new B2.q("COMPLETING_ALREADY", i10);
        f8146e = new B2.q("COMPLETING_WAITING_CHILDREN", i10);
        f8147f = new B2.q("COMPLETING_RETRY", i10);
        f8148g = new B2.q("TOO_LATE_TO_CANCEL", i10);
        f8149h = new B2.q("SEALED", i10);
    }

    public static final Object A(InterfaceC2194h interfaceC2194h, InterfaceC2785n interfaceC2785n) throws Throwable {
        N nA;
        InterfaceC2194h interfaceC2194hL;
        Thread threadCurrentThread = Thread.currentThread();
        InterfaceC2193g interfaceC2193g = C2190d.l;
        InterfaceC2191e interfaceC2191e = (InterfaceC2191e) interfaceC2194h.F(interfaceC2193g);
        C2195i c2195i = C2195i.l;
        if (interfaceC2191e == null) {
            nA = n0.a();
            interfaceC2194hL = l(c2195i, interfaceC2194h.v(nA), true);
            Z8.e eVar = E.f8084a;
            if (interfaceC2194hL != eVar && interfaceC2194hL.F(interfaceC2193g) == null) {
                interfaceC2194hL = interfaceC2194hL.v(eVar);
            }
        } else {
            if (interfaceC2191e instanceof N) {
            }
            nA = (N) n0.f8122a.get();
            interfaceC2194hL = l(c2195i, interfaceC2194h, true);
            Z8.e eVar2 = E.f8084a;
            if (interfaceC2194hL != eVar2 && interfaceC2194hL.F(interfaceC2193g) == null) {
                interfaceC2194hL = interfaceC2194hL.v(eVar2);
            }
        }
        C0706c c0706c = new C0706c(interfaceC2194hL, threadCurrentThread, nA);
        c0706c.i0(EnumC0726x.l, c0706c, interfaceC2785n);
        N n10 = c0706c.f8107p;
        if (n10 != null) {
            int i7 = N.f8093q;
            n10.S(false);
        }
        while (!Thread.interrupted()) {
            try {
                long jU = n10 != null ? n10.U() : Long.MAX_VALUE;
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = d0.l;
                if (!(atomicReferenceFieldUpdater.get(c0706c) instanceof S)) {
                    if (n10 != null) {
                        int i10 = N.f8093q;
                        n10.P(false);
                    }
                    Object objD = D(atomicReferenceFieldUpdater.get(c0706c));
                    C0720q c0720q = objD instanceof C0720q ? (C0720q) objD : null;
                    if (c0720q == null) {
                        return objD;
                    }
                    throw c0720q.f8130a;
                }
                LockSupport.parkNanos(c0706c, jU);
            } catch (Throwable th) {
                if (n10 != null) {
                    int i11 = N.f8093q;
                    n10.P(false);
                }
                throw th;
            }
        }
        InterruptedException interruptedException = new InterruptedException();
        c0706c.w(interruptedException);
        throw interruptedException;
    }

    public static final Object B(q0 q0Var, InterfaceC2785n interfaceC2785n) throws Throwable {
        Object c0720q;
        Object objU;
        r(q0Var, true, new G(m(q0Var.o.getContext()).I(q0Var.f8131p, q0Var, q0Var.f8101n), 0));
        try {
            if (interfaceC2785n instanceof AbstractC2408a) {
                A7.E.e(2, interfaceC2785n);
                c0720q = interfaceC2785n.invoke(q0Var, q0Var);
            } else {
                c0720q = h6.e.t(interfaceC2785n, q0Var, q0Var);
            }
        } catch (Throwable th) {
            c0720q = new C0720q(th, false);
        }
        EnumC2303a enumC2303a = EnumC2303a.l;
        if (c0720q == enumC2303a || (objU = q0Var.U(c0720q)) == f8146e) {
            return enumC2303a;
        }
        if (objU instanceof C0720q) {
            Throwable th2 = ((C0720q) objU).f8130a;
            if (!(th2 instanceof p0) || ((p0) th2).l != q0Var) {
                throw th2;
            }
            if (c0720q instanceof C0720q) {
                throw ((C0720q) c0720q).f8130a;
            }
        } else {
            c0720q = D(objU);
        }
        return c0720q;
    }

    public static final String C(InterfaceC2189c interfaceC2189c) {
        Object objF;
        if (interfaceC2189c instanceof X8.f) {
            return ((X8.f) interfaceC2189c).toString();
        }
        try {
            objF = interfaceC2189c + '@' + n(interfaceC2189c);
        } catch (Throwable th) {
            objF = AbstractC1386c.f(th);
        }
        if (m7.l.a(objF) != null) {
            objF = interfaceC2189c.getClass().getName() + '@' + n(interfaceC2189c);
        }
        return (String) objF;
    }

    public static final Object D(Object obj) {
        S s6;
        T t10 = obj instanceof T ? (T) obj : null;
        return (t10 == null || (s6 = t10.f8096a) == null) ? obj : s6;
    }

    public static final t0 E(InterfaceC2189c interfaceC2189c, InterfaceC2194h interfaceC2194h, Object obj) {
        t0 t0Var = null;
        if (!(interfaceC2189c instanceof InterfaceC2411d)) {
            return null;
        }
        if (interfaceC2194h.F(u0.l) != null) {
            InterfaceC2411d callerFrame = (InterfaceC2411d) interfaceC2189c;
            while (true) {
                if ((callerFrame instanceof C) || (callerFrame = callerFrame.getCallerFrame()) == null) {
                    break;
                }
                if (callerFrame instanceof t0) {
                    t0Var = (t0) callerFrame;
                    break;
                }
            }
            if (t0Var != null) {
                t0Var.k0(interfaceC2194h, obj);
            }
        }
        return t0Var;
    }

    public static final Object F(InterfaceC2194h interfaceC2194h, InterfaceC2785n interfaceC2785n, InterfaceC2189c interfaceC2189c) throws Throwable {
        Object objD;
        InterfaceC2194h context = interfaceC2189c.getContext();
        InterfaceC2194h interfaceC2194hV = !((Boolean) interfaceC2194h.j(Boolean.FALSE, new C0576h2(1))).booleanValue() ? context.v(interfaceC2194h) : l(context, interfaceC2194h, false);
        k(interfaceC2194hV);
        if (interfaceC2194hV == context) {
            X8.q qVar = new X8.q(interfaceC2189c, interfaceC2194hV);
            objD = io.sentry.config.a.m0(qVar, qVar, interfaceC2785n);
        } else {
            C2190d c2190d = C2190d.l;
            if (A7.m.a(interfaceC2194hV.F(c2190d), context.F(c2190d))) {
                t0 t0Var = new t0(interfaceC2189c, interfaceC2194hV);
                InterfaceC2194h interfaceC2194h2 = t0Var.f8101n;
                Object objN = X8.a.n(interfaceC2194h2, null);
                try {
                    Object objM0 = io.sentry.config.a.m0(t0Var, t0Var, interfaceC2785n);
                    X8.a.i(interfaceC2194h2, objN);
                    objD = objM0;
                } catch (Throwable th) {
                    X8.a.i(interfaceC2194h2, objN);
                    throw th;
                }
            } else {
                C c10 = new C(interfaceC2189c, interfaceC2194hV);
                try {
                    X8.a.j(m7.q.f19267a, h6.e.k(h6.e.g(c10, c10, interfaceC2785n)));
                    while (true) {
                        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = C.f8082p;
                        int i7 = atomicIntegerFieldUpdater.get(c10);
                        if (i7 != 0) {
                            if (i7 != 2) {
                                throw new IllegalStateException("Already suspended");
                            }
                            objD = D(d0.l.get(c10));
                            if (objD instanceof C0720q) {
                                throw ((C0720q) objD).f8130a;
                            }
                        } else if (atomicIntegerFieldUpdater.compareAndSet(c10, 0, 1)) {
                            objD = EnumC2303a.l;
                            break;
                        }
                    }
                } catch (Throwable th2) {
                    c10.resumeWith(AbstractC1386c.f(th2));
                    throw th2;
                }
            }
        }
        EnumC2303a enumC2303a = EnumC2303a.l;
        return objD;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x005e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object G(long r7, S0.C0695g r9, s7.AbstractC2410c r10) throws java.lang.Throwable {
        /*
            boolean r0 = r10 instanceof S8.r0
            if (r0 == 0) goto L13
            r0 = r10
            S8.r0 r0 = (S8.r0) r0
            int r1 = r0.f8134n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f8134n = r1
            goto L18
        L13:
            S8.r0 r0 = new S8.r0
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.f8133m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f8134n
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L34
            if (r2 != r4) goto L2c
            A7.z r7 = r0.l
            h8.AbstractC1386c.u(r10)     // Catch: S8.p0 -> L2a
            goto L55
        L2a:
            r8 = move-exception
            goto L58
        L2c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L34:
            h8.AbstractC1386c.u(r10)
            r5 = 0
            int r10 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r10 > 0) goto L3e
            return r3
        L3e:
            A7.z r10 = new A7.z
            r10.<init>()
            r0.l = r10     // Catch: S8.p0 -> L56
            r0.f8134n = r4     // Catch: S8.p0 -> L56
            S8.q0 r2 = new S8.q0     // Catch: S8.p0 -> L56
            r2.<init>(r7, r0)     // Catch: S8.p0 -> L56
            r10.l = r2     // Catch: S8.p0 -> L56
            java.lang.Object r10 = B(r2, r9)     // Catch: S8.p0 -> L56
            if (r10 != r1) goto L55
            return r1
        L55:
            return r10
        L56:
            r8 = move-exception
            r7 = r10
        L58:
            S8.q0 r9 = r8.l
            java.lang.Object r7 = r7.l
            if (r9 != r7) goto L5f
            return r3
        L5f:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: S8.AbstractC0727y.G(long, S0.g, s7.c):java.lang.Object");
    }

    public static final Object H(AbstractC2410c abstractC2410c) {
        Object obj;
        InterfaceC2194h context = abstractC2410c.getContext();
        k(context);
        InterfaceC2189c interfaceC2189cK = h6.e.k(abstractC2410c);
        X8.f fVar = interfaceC2189cK instanceof X8.f ? (X8.f) interfaceC2189cK : null;
        m7.q qVar = m7.q.f19267a;
        if (fVar == null) {
            obj = qVar;
        } else {
            AbstractC0721s abstractC0721s = fVar.o;
            if (abstractC0721s.N(context)) {
                fVar.f10556q = qVar;
                fVar.f8083n = 1;
                abstractC0721s.M(context, fVar);
            } else {
                InterfaceC2194h interfaceC2194hV = context.v(new w0(w0.f8140m));
                fVar.f10556q = qVar;
                fVar.f8083n = 1;
                abstractC0721s.M(interfaceC2194hV, fVar);
            }
            obj = EnumC2303a.l;
        }
        return obj == EnumC2303a.l ? obj : qVar;
    }

    public static final X8.c a(InterfaceC2194h interfaceC2194h) {
        if (interfaceC2194h.F(C0722t.f8137m) == null) {
            interfaceC2194h = interfaceC2194h.v(new Y(null));
        }
        return new X8.c(interfaceC2194h);
    }

    public static final X8.c b() {
        l0 l0VarC = c();
        Z8.e eVar = E.f8084a;
        return new X8.c(h6.q.x(l0VarC, X8.m.f10573a));
    }

    public static l0 c() {
        return new l0(null);
    }

    public static final void d(InterfaceC0725w interfaceC0725w, CancellationException cancellationException) {
        W w10 = (W) interfaceC0725w.getCoroutineContext().F(C0722t.f8137m);
        if (w10 != null) {
            w10.g(cancellationException);
        } else {
            throw new IllegalStateException(("Scope cannot be cancelled because it does not have a job: " + interfaceC0725w).toString());
        }
    }

    public static final void e(InterfaceC2194h interfaceC2194h, CancellationException cancellationException) {
        W w10 = (W) interfaceC2194h.F(C0722t.f8137m);
        if (w10 != null) {
            w10.g(cancellationException);
        }
    }

    public static final Object g(W w10, s7.i iVar) {
        w10.g(null);
        Object objK = w10.K(iVar);
        return objK == EnumC2303a.l ? objK : m7.q.f19267a;
    }

    public static final Object h(InterfaceC2785n interfaceC2785n, InterfaceC2189c interfaceC2189c) {
        X8.q qVar = new X8.q(interfaceC2189c, interfaceC2189c.getContext());
        Object objM0 = io.sentry.config.a.m0(qVar, qVar, interfaceC2785n);
        EnumC2303a enumC2303a = EnumC2303a.l;
        return objM0;
    }

    public static final Object i(long j3, InterfaceC2189c interfaceC2189c) {
        m7.q qVar = m7.q.f19267a;
        if (j3 <= 0) {
            return qVar;
        }
        C0712i c0712i = new C0712i(1, h6.e.k(interfaceC2189c));
        c0712i.s();
        if (j3 < Long.MAX_VALUE) {
            m(c0712i.f8118p).i(j3, c0712i);
        }
        Object objQ = c0712i.q();
        return objQ == EnumC2303a.l ? objQ : qVar;
    }

    public static final Object j(long j3, C0884t c0884t) {
        int i7 = R8.a.f7565n;
        long jB = 0;
        boolean z6 = j3 > 0;
        if (z6) {
            long jN = com.bumptech.glide.d.N(999999L, R8.c.f7567m);
            if (R8.a.c(j3)) {
                if (R8.a.c(jN) && (j3 ^ jN) < 0) {
                    throw new IllegalArgumentException("Summing infinite durations of different signs yields an undefined result.");
                }
            } else if (R8.a.c(jN)) {
                j3 = jN;
            } else {
                int i10 = ((int) j3) & 1;
                if (i10 == (((int) jN) & 1)) {
                    long j10 = (j3 >> 1) + (jN >> 1);
                    if (i10 == 0) {
                        if (-4611686018426999999L > j10 || j10 >= 4611686018427000000L) {
                            j3 = com.bumptech.glide.d.z(j10 / ((long) 1000000));
                        } else {
                            j3 = j10 << 1;
                            int i11 = R8.b.f7566a;
                        }
                    } else if (-4611686018426L > j10 || j10 >= 4611686018427L) {
                        j3 = com.bumptech.glide.d.z(AbstractC2673a.w(j10, -4611686018427387903L, 4611686018427387903L));
                    } else {
                        j3 = (j10 * ((long) 1000000)) << 1;
                        int i12 = R8.b.f7566a;
                    }
                } else {
                    j3 = i10 == 1 ? R8.a.a(j3 >> 1, jN >> 1) : R8.a.a(jN >> 1, j3 >> 1);
                }
            }
            jB = R8.a.b(j3);
        } else if (z6) {
            throw new A9.k();
        }
        Object objI = i(jB, c0884t);
        return objI == EnumC2303a.l ? objI : m7.q.f19267a;
    }

    public static final void k(InterfaceC2194h interfaceC2194h) {
        W w10 = (W) interfaceC2194h.F(C0722t.f8137m);
        if (w10 != null && !w10.b()) {
            throw w10.y();
        }
    }

    public static final InterfaceC2194h l(InterfaceC2194h interfaceC2194h, InterfaceC2194h interfaceC2194h2, boolean z6) {
        Boolean bool = Boolean.FALSE;
        boolean zBooleanValue = ((Boolean) interfaceC2194h.j(bool, new C0576h2(1))).booleanValue();
        boolean zBooleanValue2 = ((Boolean) interfaceC2194h2.j(bool, new C0576h2(1))).booleanValue();
        if (!zBooleanValue && !zBooleanValue2) {
            return interfaceC2194h.v(interfaceC2194h2);
        }
        C2195i c2195i = C2195i.l;
        InterfaceC2194h interfaceC2194h3 = (InterfaceC2194h) interfaceC2194h.j(c2195i, new C0576h2(2));
        Object objJ = interfaceC2194h2;
        if (zBooleanValue2) {
            objJ = interfaceC2194h2.j(c2195i, new C0576h2(3));
        }
        return interfaceC2194h3.v((InterfaceC2194h) objJ);
    }

    public static final B m(InterfaceC2194h interfaceC2194h) {
        InterfaceC2192f interfaceC2192fF = interfaceC2194h.F(C2190d.l);
        B b10 = interfaceC2192fF instanceof B ? (B) interfaceC2192fF : null;
        return b10 == null ? A.f8081a : b10;
    }

    public static final String n(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    public static final W o(InterfaceC2194h interfaceC2194h) {
        W w10 = (W) interfaceC2194h.F(C0722t.f8137m);
        if (w10 != null) {
            return w10;
        }
        throw new IllegalStateException(("Current context doesn't contain Job in it: " + interfaceC2194h).toString());
    }

    public static final C0712i p(InterfaceC2189c interfaceC2189c) {
        C0712i c0712i;
        C0712i c0712i2;
        if (!(interfaceC2189c instanceof X8.f)) {
            return new C0712i(1, interfaceC2189c);
        }
        X8.f fVar = (X8.f) interfaceC2189c;
        loop0: while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = X8.f.f10554s;
            Object obj = atomicReferenceFieldUpdater.get(fVar);
            B2.q qVar = X8.a.c;
            c0712i = null;
            if (obj == null) {
                atomicReferenceFieldUpdater.set(fVar, qVar);
                c0712i2 = null;
                break;
            }
            if (obj instanceof C0712i) {
                while (!atomicReferenceFieldUpdater.compareAndSet(fVar, obj, qVar)) {
                    if (atomicReferenceFieldUpdater.get(fVar) != obj) {
                        break;
                    }
                }
                c0712i2 = (C0712i) obj;
                break loop0;
            }
            if (obj != qVar && !(obj instanceof Throwable)) {
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        }
        if (c0712i2 != null) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = C0712i.f8116r;
            Object obj2 = atomicReferenceFieldUpdater2.get(c0712i2);
            if (!(obj2 instanceof C0719p) || ((C0719p) obj2).d == null) {
                C0712i.f8115q.set(c0712i2, 536870911);
                atomicReferenceFieldUpdater2.set(c0712i2, C0705b.l);
                c0712i = c0712i2;
            } else {
                c0712i2.m();
            }
            if (c0712i != null) {
                return c0712i;
            }
        }
        return new C0712i(2, interfaceC2189c);
    }

    public static final void q(Throwable th, InterfaceC2194h interfaceC2194h) throws IllegalAccessException, InvocationTargetException {
        try {
            InterfaceC0723u interfaceC0723u = (InterfaceC0723u) interfaceC2194h.F(C0722t.l);
            if (interfaceC0723u != null) {
                interfaceC0723u.G(th, interfaceC2194h);
            } else {
                X8.a.f(th, interfaceC2194h);
            }
        } catch (Throwable th2) {
            if (th != th2) {
                RuntimeException runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                h6.q.l(runtimeException, th);
                th = runtimeException;
            }
            X8.a.f(th, interfaceC2194h);
        }
    }

    public static final F r(W w10, boolean z6, Z z10) {
        if (w10 instanceof d0) {
            return ((d0) w10).R(z6, z10);
        }
        return w10.m(z10.k(), z6, new C0223q(1, z10, Z.class, "invoke", "invoke(Ljava/lang/Throwable;)V", 0, 26));
    }

    public static final boolean s(InterfaceC0725w interfaceC0725w) {
        W w10 = (W) interfaceC0725w.getCoroutineContext().F(C0722t.f8137m);
        if (w10 != null) {
            return w10.b();
        }
        return true;
    }

    public static final boolean t(InterfaceC2194h interfaceC2194h) {
        W w10 = (W) interfaceC2194h.F(C0722t.f8137m);
        if (w10 != null) {
            return w10.b();
        }
        return true;
    }

    public static final boolean u(int i7) {
        return i7 == 1 || i7 == 2;
    }

    public static final k0 v(InterfaceC0725w interfaceC0725w, InterfaceC2194h interfaceC2194h, EnumC0726x enumC0726x, InterfaceC2785n interfaceC2785n) {
        InterfaceC2194h interfaceC2194hX = x(interfaceC0725w, interfaceC2194h);
        enumC0726x.getClass();
        k0 e0Var = enumC0726x == EnumC0726x.f8141m ? new e0(interfaceC2194hX, interfaceC2785n) : new k0(interfaceC2194hX, true);
        e0Var.i0(enumC0726x, e0Var, interfaceC2785n);
        return e0Var;
    }

    public static /* synthetic */ k0 w(InterfaceC0725w interfaceC0725w, AbstractC2187a abstractC2187a, EnumC0726x enumC0726x, InterfaceC2785n interfaceC2785n, int i7) {
        InterfaceC2194h interfaceC2194h = abstractC2187a;
        if ((i7 & 1) != 0) {
            interfaceC2194h = C2195i.l;
        }
        if ((i7 & 2) != 0) {
            enumC0726x = EnumC0726x.l;
        }
        return v(interfaceC0725w, interfaceC2194h, enumC0726x, interfaceC2785n);
    }

    public static final InterfaceC2194h x(InterfaceC0725w interfaceC0725w, InterfaceC2194h interfaceC2194h) {
        InterfaceC2194h interfaceC2194hL = l(interfaceC0725w.getCoroutineContext(), interfaceC2194h, true);
        Z8.e eVar = E.f8084a;
        return (interfaceC2194hL == eVar || interfaceC2194hL.F(C2190d.l) != null) ? interfaceC2194hL : interfaceC2194hL.v(eVar);
    }

    public static final Object y(Object obj) {
        return obj instanceof C0720q ? AbstractC1386c.f(((C0720q) obj).f8130a) : obj;
    }

    public static final void z(C0712i c0712i, InterfaceC2189c interfaceC2189c, boolean z6) {
        Object obj = C0712i.f8116r.get(c0712i);
        Throwable thD = c0712i.d(obj);
        Object objF = thD != null ? AbstractC1386c.f(thD) : c0712i.e(obj);
        if (!z6) {
            interfaceC2189c.resumeWith(objF);
            return;
        }
        A7.m.d(interfaceC2189c, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTaskKt.resume>");
        X8.f fVar = (X8.f) interfaceC2189c;
        AbstractC2410c abstractC2410c = fVar.f10555p;
        InterfaceC2194h context = abstractC2410c.getContext();
        Object objN = X8.a.n(context, fVar.f10557r);
        t0 t0VarE = objN != X8.a.d ? E(abstractC2410c, context, objN) : null;
        try {
            abstractC2410c.resumeWith(objF);
        } finally {
            if (t0VarE == null || t0VarE.j0()) {
                X8.a.i(context, objN);
            }
        }
    }
}
