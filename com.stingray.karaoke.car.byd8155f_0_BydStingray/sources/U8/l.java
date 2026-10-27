package U8;

import A7.E;
import Q7.C0668v;
import S8.AbstractC0727y;
import S8.C0711h;
import S8.C0712i;
import S8.InterfaceC0710g;
import S8.v0;
import a9.C0933c;
import a9.EnumC0936f;
import a9.InterfaceC0934d;
import h8.AbstractC1386c;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public class l implements p {
    public static final /* synthetic */ AtomicLongFieldUpdater o = AtomicLongFieldUpdater.newUpdater(l.class, "sendersAndCloseStatus$volatile");

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f9240p = AtomicLongFieldUpdater.newUpdater(l.class, "receivers$volatile");

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f9241q = AtomicLongFieldUpdater.newUpdater(l.class, "bufferEnd$volatile");

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f9242r = AtomicLongFieldUpdater.newUpdater(l.class, "completedExpandBuffersAndPauseFlag$volatile");

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f9243s = AtomicReferenceFieldUpdater.newUpdater(l.class, Object.class, "sendSegment$volatile");

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f9244t = AtomicReferenceFieldUpdater.newUpdater(l.class, Object.class, "receiveSegment$volatile");

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f9245u = AtomicReferenceFieldUpdater.newUpdater(l.class, Object.class, "bufferEndSegment$volatile");

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f9246v = AtomicReferenceFieldUpdater.newUpdater(l.class, Object.class, "_closeCause$volatile");

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f9247w = AtomicReferenceFieldUpdater.newUpdater(l.class, Object.class, "closeHandler$volatile");
    private volatile /* synthetic */ Object _closeCause$volatile;
    private volatile /* synthetic */ long bufferEnd$volatile;
    private volatile /* synthetic */ Object bufferEndSegment$volatile;
    private volatile /* synthetic */ Object closeHandler$volatile;
    private volatile /* synthetic */ long completedExpandBuffersAndPauseFlag$volatile;
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final InterfaceC2782k f9248m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C0711h f9249n;
    private volatile /* synthetic */ Object receiveSegment$volatile;
    private volatile /* synthetic */ long receivers$volatile;
    private volatile /* synthetic */ Object sendSegment$volatile;
    private volatile /* synthetic */ long sendersAndCloseStatus$volatile;

    public l(int i7, InterfaceC2782k interfaceC2782k) {
        this.l = i7;
        this.f9248m = interfaceC2782k;
        if (i7 < 0) {
            throw new IllegalArgumentException(A7.k.g("Invalid channel capacity: ", i7, ", should be >=0").toString());
        }
        t tVar = n.f9251a;
        this.bufferEnd$volatile = i7 != 0 ? i7 != Integer.MAX_VALUE ? i7 : Long.MAX_VALUE : 0L;
        this.completedExpandBuffersAndPauseFlag$volatile = f9241q.get(this);
        t tVar2 = new t(0L, null, this, 3);
        this.sendSegment$volatile = tVar2;
        this.receiveSegment$volatile = tVar2;
        if (D()) {
            tVar2 = n.f9251a;
            A7.m.d(tVar2, "null cannot be cast to non-null type kotlinx.coroutines.channels.ChannelSegment<E of kotlinx.coroutines.channels.BufferedChannel>");
        }
        this.bufferEndSegment$volatile = tVar2;
        this.f9249n = interfaceC2782k != null ? new C0711h(this, 1) : null;
        this._closeCause$volatile = n.f9265s;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object G(U8.l r13, s7.AbstractC2410c r14) throws java.lang.Throwable {
        /*
            boolean r0 = r14 instanceof U8.j
            if (r0 == 0) goto L14
            r0 = r14
            U8.j r0 = (U8.j) r0
            int r1 = r0.f9237n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.f9237n = r1
        L12:
            r6 = r0
            goto L1a
        L14:
            U8.j r0 = new U8.j
            r0.<init>(r13, r14)
            goto L12
        L1a:
            java.lang.Object r14 = r6.l
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r6.f9237n
            r2 = 1
            if (r1 == 0) goto L36
            if (r1 != r2) goto L2e
            h8.AbstractC1386c.u(r14)
            U8.s r14 = (U8.s) r14
            java.lang.Object r13 = r14.f9271a
            goto L99
        L2e:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L36:
            h8.AbstractC1386c.u(r14)
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r14 = U8.l.f9244t
            java.lang.Object r14 = r14.get(r13)
            U8.t r14 = (U8.t) r14
        L41:
            boolean r1 = r13.B()
            if (r1 == 0) goto L51
            java.lang.Throwable r13 = r13.v()
            U8.q r14 = new U8.q
            r14.<init>(r13)
            goto L9f
        L51:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r1 = U8.l.f9240p
            long r4 = r1.getAndIncrement(r13)
            int r1 = U8.n.f9252b
            long r7 = (long) r1
            long r9 = r4 / r7
            long r7 = r4 % r7
            int r3 = (int) r7
            long r7 = r14.f10577n
            int r1 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r1 == 0) goto L6d
            U8.t r1 = r13.u(r9, r14)
            if (r1 != 0) goto L6c
            goto L41
        L6c:
            r14 = r1
        L6d:
            r12 = 0
            r7 = r13
            r8 = r14
            r9 = r3
            r10 = r4
            java.lang.Object r1 = r7.L(r8, r9, r10, r12)
            B2.q r7 = U8.n.f9260m
            if (r1 == r7) goto La0
            B2.q r7 = U8.n.o
            if (r1 != r7) goto L8a
            long r7 = r13.y()
            int r1 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r1 >= 0) goto L41
            r14.b()
            goto L41
        L8a:
            B2.q r7 = U8.n.f9261n
            if (r1 != r7) goto L9b
            r6.f9237n = r2
            r1 = r13
            r2 = r14
            java.lang.Object r13 = r1.H(r2, r3, r4, r6)
            if (r13 != r0) goto L99
            return r0
        L99:
            r14 = r13
            goto L9f
        L9b:
            r14.b()
            r14 = r1
        L9f:
            return r14
        La0:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "unexpected"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: U8.l.G(U8.l, s7.c):java.lang.Object");
    }

    public static final t b(l lVar, long j3, t tVar) throws IllegalAccessException, InvocationTargetException {
        Object objD;
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j10;
        long j11;
        lVar.getClass();
        t tVar2 = n.f9251a;
        m mVar = m.f9250t;
        loop0: while (true) {
            objD = X8.a.d(tVar, j3, mVar);
            if (!X8.a.g(objD)) {
                X8.r rVarE = X8.a.e(objD);
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f9243s;
                    X8.r rVar = (X8.r) atomicReferenceFieldUpdater.get(lVar);
                    if (rVar.f10577n >= rVarE.f10577n) {
                        break loop0;
                    }
                    if (!rVarE.j()) {
                        break;
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(lVar, rVar, rVarE)) {
                        if (atomicReferenceFieldUpdater.get(lVar) != rVar) {
                            if (rVarE.f()) {
                                rVarE.e();
                            }
                        }
                    }
                    if (rVar.f()) {
                        rVar.e();
                    }
                }
            } else {
                break;
            }
        }
        boolean zG = X8.a.g(objD);
        AtomicLongFieldUpdater atomicLongFieldUpdater2 = f9240p;
        if (zG) {
            lVar.r();
            if (tVar.f10577n * ((long) n.f9252b) >= atomicLongFieldUpdater2.get(lVar)) {
                return null;
            }
            tVar.b();
            return null;
        }
        t tVar3 = (t) X8.a.e(objD);
        long j12 = tVar3.f10577n;
        if (j12 <= j3) {
            return tVar3;
        }
        long j13 = ((long) n.f9252b) * j12;
        do {
            atomicLongFieldUpdater = o;
            j10 = atomicLongFieldUpdater.get(lVar);
            j11 = 1152921504606846975L & j10;
            if (j11 >= j13) {
                break;
            }
        } while (!atomicLongFieldUpdater.compareAndSet(lVar, j10, j11 + (((long) ((int) (j10 >> 60))) << 60)));
        if (j12 * ((long) n.f9252b) >= atomicLongFieldUpdater2.get(lVar)) {
            return null;
        }
        tVar3.b();
        return null;
    }

    public static final void i(l lVar, Object obj, C0712i c0712i) throws IllegalAccessException, InvocationTargetException {
        InterfaceC2782k interfaceC2782k = lVar.f9248m;
        if (interfaceC2782k != null) {
            X8.a.a(interfaceC2782k, obj, c0712i.f8118p);
        }
        c0712i.resumeWith(AbstractC1386c.f(lVar.x()));
    }

    public static final void j(l lVar, InterfaceC0934d interfaceC0934d) {
        lVar.getClass();
        t tVar = (t) f9244t.get(lVar);
        while (!lVar.B()) {
            long andIncrement = f9240p.getAndIncrement(lVar);
            long j3 = n.f9252b;
            long j10 = andIncrement / j3;
            int i7 = (int) (andIncrement % j3);
            if (tVar.f10577n != j10) {
                t tVarU = lVar.u(j10, tVar);
                if (tVarU == null) {
                    continue;
                } else {
                    tVar = tVarU;
                }
            }
            Object objL = lVar.L(tVar, i7, andIncrement, interfaceC0934d);
            if (objL == n.f9260m) {
                v0 v0Var = interfaceC0934d instanceof v0 ? (v0) interfaceC0934d : null;
                if (v0Var != null) {
                    v0Var.b(tVar, i7);
                    return;
                }
                return;
            }
            if (objL != n.o) {
                if (objL == n.f9261n) {
                    throw new IllegalStateException("unexpected");
                }
                tVar.b();
                ((C0933c) interfaceC0934d).f11230p = objL;
                return;
            }
            if (andIncrement < lVar.y()) {
                tVar.b();
            }
        }
        ((C0933c) interfaceC0934d).f11230p = n.l;
    }

    public static final int k(l lVar, t tVar, int i7, Object obj, long j3, Object obj2, boolean z6) {
        lVar.getClass();
        tVar.n(i7, obj);
        if (z6) {
            return lVar.M(tVar, i7, obj, j3, obj2, z6);
        }
        Object objL = tVar.l(i7);
        if (objL == null) {
            if (lVar.n(j3)) {
                if (tVar.k(null, i7, n.d)) {
                    return 1;
                }
            } else {
                if (obj2 == null) {
                    return 3;
                }
                if (tVar.k(null, i7, obj2)) {
                    return 2;
                }
            }
        } else if (objL instanceof v0) {
            tVar.n(i7, null);
            if (lVar.J(objL, obj)) {
                tVar.o(i7, n.f9257i);
                return 0;
            }
            B2.q qVar = n.f9259k;
            if (tVar.f9273q.getAndSet((i7 * 2) + 1, qVar) != qVar) {
                tVar.m(i7, true);
            }
            return 5;
        }
        return lVar.M(tVar, i7, obj, j3, obj2, z6);
    }

    public static void z(l lVar) {
        lVar.getClass();
        AtomicLongFieldUpdater atomicLongFieldUpdater = f9242r;
        if ((atomicLongFieldUpdater.addAndGet(lVar, 1L) & 4611686018427387904L) != 0) {
            while ((atomicLongFieldUpdater.get(lVar) & 4611686018427387904L) != 0) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x00c6, code lost:
    
        r0 = (U8.t) ((X8.b) X8.b.f10552m.get(r0));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean A(long r19, boolean r21) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instruction units count: 410
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: U8.l.A(long, boolean):boolean");
    }

    public final boolean B() {
        return A(o.get(this), true);
    }

    public boolean C() {
        return false;
    }

    public final boolean D() {
        long j3 = f9241q.get(this);
        return j3 == 0 || j3 == Long.MAX_VALUE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x0011, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void E(long r5, U8.t r7) {
        /*
            r4 = this;
        L0:
            long r0 = r7.f10577n
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 >= 0) goto L11
            X8.b r0 = r7.c()
            U8.t r0 = (U8.t) r0
            if (r0 != 0) goto Lf
            goto L11
        Lf:
            r7 = r0
            goto L0
        L11:
            boolean r5 = r7.d()
            if (r5 == 0) goto L22
            X8.b r5 = r7.c()
            U8.t r5 = (U8.t) r5
            if (r5 != 0) goto L20
            goto L22
        L20:
            r7 = r5
            goto L11
        L22:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = U8.l.f9245u
            java.lang.Object r6 = r5.get(r4)
            X8.r r6 = (X8.r) r6
            long r0 = r6.f10577n
            long r2 = r7.f10577n
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L33
            goto L49
        L33:
            boolean r0 = r7.j()
            if (r0 != 0) goto L3a
            goto L11
        L3a:
            boolean r0 = r5.compareAndSet(r4, r6, r7)
            if (r0 == 0) goto L4a
            boolean r4 = r6.f()
            if (r4 == 0) goto L49
            r6.e()
        L49:
            return
        L4a:
            java.lang.Object r0 = r5.get(r4)
            if (r0 == r6) goto L3a
            boolean r5 = r7.f()
            if (r5 == 0) goto L22
            r7.e()
            goto L22
        */
        throw new UnsupportedOperationException("Method not decompiled: U8.l.E(long, U8.t):void");
    }

    public final Object F(Object obj, InterfaceC2189c interfaceC2189c) throws IllegalAccessException, InvocationTargetException {
        A9.k kVarB;
        C0712i c0712i = new C0712i(1, h6.e.k(interfaceC2189c));
        c0712i.s();
        InterfaceC2782k interfaceC2782k = this.f9248m;
        if (interfaceC2782k == null || (kVarB = X8.a.b(interfaceC2782k, obj, null)) == null) {
            c0712i.resumeWith(AbstractC1386c.f(x()));
        } else {
            h6.q.l(kVarB, x());
            c0712i.resumeWith(AbstractC1386c.f(kVarB));
        }
        Object objQ = c0712i.q();
        return objQ == EnumC2303a.l ? objQ : m7.q.f19267a;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object H(U8.t r11, int r12, long r13, s7.AbstractC2410c r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 263
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: U8.l.H(U8.t, int, long, s7.c):java.lang.Object");
    }

    public final void I(v0 v0Var, boolean z6) {
        if (v0Var instanceof InterfaceC0710g) {
            ((InterfaceC2189c) v0Var).resumeWith(AbstractC1386c.f(z6 ? w() : x()));
            return;
        }
        if (v0Var instanceof y) {
            ((y) v0Var).l.resumeWith(new s(new q(v())));
            return;
        }
        if (!(v0Var instanceof d)) {
            if (v0Var instanceof InterfaceC0934d) {
                ((C0933c) ((InterfaceC0934d) v0Var)).g(this, n.l);
                return;
            } else {
                throw new IllegalStateException(("Unexpected waiter: " + v0Var).toString());
            }
        }
        d dVar = (d) v0Var;
        C0712i c0712i = dVar.f9229m;
        A7.m.c(c0712i);
        dVar.f9229m = null;
        dVar.l = n.l;
        Throwable thV = dVar.f9230n.v();
        if (thV == null) {
            c0712i.resumeWith(Boolean.FALSE);
        } else {
            c0712i.resumeWith(AbstractC1386c.f(thV));
        }
    }

    public final boolean J(Object obj, Object obj2) {
        if (obj instanceof InterfaceC0934d) {
            return ((C0933c) ((InterfaceC0934d) obj)).g(this, obj2) == 0;
        }
        boolean z6 = obj instanceof y;
        InterfaceC2782k interfaceC2782k = this.f9248m;
        if (z6) {
            A7.m.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveCatching<E of kotlinx.coroutines.channels.BufferedChannel>");
            return n.a(((y) obj).l, new s(obj2), interfaceC2782k != null ? m() : null);
        }
        if (!(obj instanceof d)) {
            if (obj instanceof InterfaceC0710g) {
                A7.m.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<E of kotlinx.coroutines.channels.BufferedChannel>");
                return n.a((InterfaceC0710g) obj, obj2, interfaceC2782k != null ? l() : null);
            }
            throw new IllegalStateException(("Unexpected receiver type: " + obj).toString());
        }
        A7.m.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator<E of kotlinx.coroutines.channels.BufferedChannel>");
        d dVar = (d) obj;
        C0712i c0712i = dVar.f9229m;
        A7.m.c(c0712i);
        dVar.f9229m = null;
        dVar.l = obj2;
        Boolean bool = Boolean.TRUE;
        InterfaceC2782k interfaceC2782k2 = dVar.f9230n.f9248m;
        return n.a(c0712i, bool, interfaceC2782k2 != null ? new C0811b(interfaceC2782k2, 0, obj2) : null);
    }

    public final boolean K(Object obj, t tVar, int i7) {
        EnumC0936f enumC0936f;
        boolean z6 = obj instanceof InterfaceC0710g;
        m7.q qVar = m7.q.f19267a;
        if (z6) {
            A7.m.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            return n.a((InterfaceC0710g) obj, qVar, null);
        }
        if (!(obj instanceof InterfaceC0934d)) {
            throw new IllegalStateException(("Unexpected waiter: " + obj).toString());
        }
        A7.m.d(obj, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectImplementation<*>");
        int iG = ((C0933c) obj).g(this, qVar);
        if (iG == 0) {
            enumC0936f = EnumC0936f.l;
        } else if (iG == 1) {
            enumC0936f = EnumC0936f.f11233m;
        } else if (iG == 2) {
            enumC0936f = EnumC0936f.f11234n;
        } else {
            if (iG != 3) {
                throw new IllegalStateException(("Unexpected internal result: " + iG).toString());
            }
            enumC0936f = EnumC0936f.o;
        }
        if (enumC0936f == EnumC0936f.f11233m) {
            tVar.n(i7, null);
        }
        return enumC0936f == EnumC0936f.l;
    }

    public final Object L(t tVar, int i7, long j3, Object obj) {
        Object objL = tVar.l(i7);
        AtomicReferenceArray atomicReferenceArray = tVar.f9273q;
        AtomicLongFieldUpdater atomicLongFieldUpdater = o;
        if (objL == null) {
            if (j3 >= (atomicLongFieldUpdater.get(this) & 1152921504606846975L)) {
                if (obj == null) {
                    return n.f9261n;
                }
                if (tVar.k(objL, i7, obj)) {
                    t();
                    return n.f9260m;
                }
            }
        } else if (objL == n.d && tVar.k(objL, i7, n.f9257i)) {
            t();
            Object obj2 = atomicReferenceArray.get(i7 * 2);
            tVar.n(i7, null);
            return obj2;
        }
        while (true) {
            Object objL2 = tVar.l(i7);
            if (objL2 == null || objL2 == n.f9253e) {
                if (j3 < (atomicLongFieldUpdater.get(this) & 1152921504606846975L)) {
                    if (tVar.k(objL2, i7, n.f9256h)) {
                        t();
                        return n.o;
                    }
                } else {
                    if (obj == null) {
                        return n.f9261n;
                    }
                    if (tVar.k(objL2, i7, obj)) {
                        t();
                        return n.f9260m;
                    }
                }
            } else {
                if (objL2 != n.d) {
                    B2.q qVar = n.f9258j;
                    if (objL2 != qVar && objL2 != n.f9256h) {
                        if (objL2 == n.l) {
                            t();
                            return n.o;
                        }
                        if (objL2 != n.f9255g && tVar.k(objL2, i7, n.f9254f)) {
                            boolean z6 = objL2 instanceof B;
                            if (z6) {
                                objL2 = ((B) objL2).f9222a;
                            }
                            if (K(objL2, tVar, i7)) {
                                tVar.o(i7, n.f9257i);
                                t();
                                Object obj3 = atomicReferenceArray.get(i7 * 2);
                                tVar.n(i7, null);
                                return obj3;
                            }
                            tVar.o(i7, qVar);
                            tVar.i();
                            if (z6) {
                                t();
                            }
                            return n.o;
                        }
                    }
                    return n.o;
                }
                if (tVar.k(objL2, i7, n.f9257i)) {
                    t();
                    Object obj4 = atomicReferenceArray.get(i7 * 2);
                    tVar.n(i7, null);
                    return obj4;
                }
            }
        }
    }

    public final int M(t tVar, int i7, Object obj, long j3, Object obj2, boolean z6) throws IllegalAccessException, InvocationTargetException {
        while (true) {
            Object objL = tVar.l(i7);
            if (objL == null) {
                if (!n(j3) || z6) {
                    if (z6) {
                        if (tVar.k(null, i7, n.f9258j)) {
                            tVar.i();
                            return 4;
                        }
                    } else {
                        if (obj2 == null) {
                            return 3;
                        }
                        if (tVar.k(null, i7, obj2)) {
                            return 2;
                        }
                    }
                } else if (tVar.k(null, i7, n.d)) {
                    return 1;
                }
            } else {
                if (objL != n.f9253e) {
                    B2.q qVar = n.f9259k;
                    if (objL == qVar) {
                        tVar.n(i7, null);
                        return 5;
                    }
                    if (objL == n.f9256h) {
                        tVar.n(i7, null);
                        return 5;
                    }
                    if (objL == n.l) {
                        tVar.n(i7, null);
                        r();
                        return 4;
                    }
                    tVar.n(i7, null);
                    if (objL instanceof B) {
                        objL = ((B) objL).f9222a;
                    }
                    if (J(objL, obj)) {
                        tVar.o(i7, n.f9257i);
                        return 0;
                    }
                    if (tVar.f9273q.getAndSet((i7 * 2) + 1, qVar) == qVar) {
                        return 5;
                    }
                    tVar.m(i7, true);
                    return 5;
                }
                if (tVar.k(objL, i7, n.d)) {
                    return 1;
                }
            }
        }
    }

    public final void N(long j3) {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j10;
        long j11;
        if (D()) {
            return;
        }
        do {
            atomicLongFieldUpdater = f9241q;
        } while (atomicLongFieldUpdater.get(this) <= j3);
        int i7 = n.c;
        int i10 = 0;
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater2 = f9242r;
            if (i10 >= i7) {
                do {
                    j10 = atomicLongFieldUpdater2.get(this);
                } while (!atomicLongFieldUpdater2.compareAndSet(this, j10, 4611686018427387904L + (j10 & 4611686018427387903L)));
                while (true) {
                    long j12 = atomicLongFieldUpdater.get(this);
                    long j13 = atomicLongFieldUpdater2.get(this);
                    long j14 = j13 & 4611686018427387903L;
                    boolean z6 = (j13 & 4611686018427387904L) != 0;
                    if (j12 == j14 && j12 == atomicLongFieldUpdater.get(this)) {
                        break;
                    } else if (!z6) {
                        atomicLongFieldUpdater2.compareAndSet(this, j13, j14 + 4611686018427387904L);
                    }
                }
                do {
                    j11 = atomicLongFieldUpdater2.get(this);
                } while (!atomicLongFieldUpdater2.compareAndSet(this, j11, j11 & 4611686018427387903L));
                return;
            }
            long j15 = atomicLongFieldUpdater.get(this);
            if (j15 == (atomicLongFieldUpdater2.get(this) & 4611686018427387903L) && j15 == atomicLongFieldUpdater.get(this)) {
                return;
            } else {
                i10++;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:148:?, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:?, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00f0, code lost:
    
        r5 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00f2, code lost:
    
        i(r26, r27, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00f5, code lost:
    
        r1 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00f6, code lost:
    
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00fa, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00fb, code lost:
    
        r1 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0142, code lost:
    
        if (r23 >= r5.get(r26)) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0144, code lost:
    
        r21.b();
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0147, code lost:
    
        r1 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0149, code lost:
    
        i(r26, r27, r1);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:150:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v10, types: [S8.i] */
    /* JADX WARN: Type inference failed for: r1v35 */
    /* JADX WARN: Type inference failed for: r1v41 */
    /* JADX WARN: Type inference failed for: r1v9 */
    @Override // U8.A
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object a(java.lang.Object r27, q7.InterfaceC2189c r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 475
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: U8.l.a(java.lang.Object, q7.c):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v2, types: [S8.i] */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r16v0, types: [U8.l, java.lang.Object] */
    @Override // U8.z
    public final Object c(s7.i iVar) throws Throwable {
        t tVar;
        ?? r13;
        C0712i c0712i;
        t tVar2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f9244t;
        t tVar3 = (t) atomicReferenceFieldUpdater.get(this);
        while (!B()) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f9240p;
            long andIncrement = atomicLongFieldUpdater.getAndIncrement(this);
            long j3 = n.f9252b;
            long j10 = andIncrement / j3;
            int i7 = (int) (andIncrement % j3);
            if (tVar3.f10577n != j10) {
                t tVarU = u(j10, tVar3);
                if (tVarU == null) {
                    continue;
                } else {
                    tVar = tVarU;
                }
            } else {
                tVar = tVar3;
            }
            Object objL = L(tVar, i7, andIncrement, null);
            B2.q qVar = n.f9260m;
            if (objL == qVar) {
                throw new IllegalStateException("unexpected");
            }
            B2.q qVar2 = n.o;
            if (objL == qVar2) {
                if (andIncrement < y()) {
                    tVar.b();
                }
                tVar3 = tVar;
            } else {
                if (objL != n.f9261n) {
                    tVar.b();
                    return objL;
                }
                C0712i c0712iP = AbstractC0727y.p(h6.e.k(iVar));
                try {
                    Object objL2 = L(tVar, i7, andIncrement, c0712iP);
                    try {
                        if (objL2 == qVar) {
                            c0712i = c0712iP;
                            c0712i.b(tVar, i7);
                        } else {
                            c0712i = c0712iP;
                            e eVarL = null;
                            InterfaceC2782k interfaceC2782k = this.f9248m;
                            if (objL2 == qVar2) {
                                if (andIncrement < y()) {
                                    tVar.b();
                                }
                                t tVar4 = (t) atomicReferenceFieldUpdater.get(this);
                                while (true) {
                                    if (B()) {
                                        c0712i.resumeWith(AbstractC1386c.f(w()));
                                        break;
                                    }
                                    long andIncrement2 = atomicLongFieldUpdater.getAndIncrement(this);
                                    long j11 = n.f9252b;
                                    long j12 = andIncrement2 / j11;
                                    int i10 = (int) (andIncrement2 % j11);
                                    if (tVar4.f10577n != j12) {
                                        t tVarU2 = u(j12, tVar4);
                                        if (tVarU2 != null) {
                                            tVar2 = tVarU2;
                                        }
                                    } else {
                                        tVar2 = tVar4;
                                    }
                                    InterfaceC2782k interfaceC2782k2 = interfaceC2782k;
                                    objL2 = L(tVar2, i10, andIncrement2, c0712i);
                                    if (objL2 == n.f9260m) {
                                        c0712i.b(tVar2, i10);
                                        break;
                                    }
                                    if (objL2 == n.o) {
                                        if (andIncrement2 < y()) {
                                            tVar2.b();
                                        }
                                        tVar4 = tVar2;
                                        interfaceC2782k = interfaceC2782k2;
                                    } else {
                                        if (objL2 == n.f9261n) {
                                            throw new IllegalStateException("unexpected");
                                        }
                                        tVar2.b();
                                        if (interfaceC2782k2 != null) {
                                            eVarL = l();
                                        }
                                    }
                                }
                            } else {
                                tVar.b();
                                if (interfaceC2782k != null) {
                                    eVarL = l();
                                }
                            }
                            c0712i.n(objL2, eVarL);
                        }
                        Object objQ = c0712i.q();
                        EnumC2303a enumC2303a = EnumC2303a.l;
                        return objQ;
                    } catch (Throwable th) {
                        th = th;
                        r13 = qVar;
                        r13.A();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    r13 = c0712iP;
                }
            }
        }
        Throwable thW = w();
        int i11 = X8.s.f10578a;
        throw thW;
    }

    @Override // U8.z
    public final Object d(W8.s sVar) {
        return G(this, sVar);
    }

    @Override // U8.z
    public final B8.t e() {
        f fVar = f.f9232t;
        E.e(3, fVar);
        g gVar = g.f9233t;
        E.e(3, gVar);
        return new B8.t(this, fVar, gVar, this.f9249n);
    }

    @Override // U8.z
    public final B8.t f() {
        h hVar = h.f9234t;
        E.e(3, hVar);
        i iVar = i.f9235t;
        E.e(3, iVar);
        return new B8.t(this, hVar, iVar, this.f9249n);
    }

    @Override // U8.z
    public final void g(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new CancellationException("Channel was cancelled");
        }
        o(cancellationException, true);
    }

    @Override // U8.z
    public final Object h() {
        t tVar;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f9240p;
        long j3 = atomicLongFieldUpdater.get(this);
        long j10 = o.get(this);
        if (A(j10, true)) {
            return new q(v());
        }
        long j11 = j10 & 1152921504606846975L;
        Object obj = s.f9270b;
        if (j3 >= j11) {
            return obj;
        }
        Object obj2 = n.f9259k;
        t tVar2 = (t) f9244t.get(this);
        while (!B()) {
            long andIncrement = atomicLongFieldUpdater.getAndIncrement(this);
            long j12 = n.f9252b;
            long j13 = andIncrement / j12;
            int i7 = (int) (andIncrement % j12);
            if (tVar2.f10577n != j13) {
                t tVarU = u(j13, tVar2);
                if (tVarU == null) {
                    continue;
                } else {
                    tVar = tVarU;
                }
            } else {
                tVar = tVar2;
            }
            Object objL = L(tVar, i7, andIncrement, obj2);
            if (objL == n.f9260m) {
                v0 v0Var = obj2 instanceof v0 ? (v0) obj2 : null;
                if (v0Var != null) {
                    v0Var.b(tVar, i7);
                }
                N(andIncrement);
                tVar.i();
            } else if (objL == n.o) {
                if (andIncrement < y()) {
                    tVar.b();
                }
                tVar2 = tVar;
            } else {
                if (objL == n.f9261n) {
                    throw new IllegalStateException("unexpected");
                }
                tVar.b();
                obj = objL;
            }
            return obj;
        }
        return new q(v());
    }

    @Override // U8.z
    public final d iterator() {
        return new d(this);
    }

    public final e l() {
        return new e(3, this, l.class, "onCancellationImplDoNotCall", "onCancellationImplDoNotCall(Ljava/lang/Throwable;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;)V", 0, 0);
    }

    public final e m() {
        return new e(3, this, l.class, "onCancellationChannelResultImplDoNotCall", "onCancellationChannelResultImplDoNotCall-5_sEAP8(Ljava/lang/Throwable;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;)V", 0, 1);
    }

    public final boolean n(long j3) {
        return j3 < f9241q.get(this) || j3 < f9240p.get(this) + ((long) this.l);
    }

    public final boolean o(Throwable th, boolean z6) {
        boolean z10;
        long j3;
        long j10;
        long j11;
        Object obj;
        long j12;
        long j13;
        AtomicLongFieldUpdater atomicLongFieldUpdater = o;
        if (z6) {
            do {
                j13 = atomicLongFieldUpdater.get(this);
                if (((int) (j13 >> 60)) != 0) {
                    break;
                }
                t tVar = n.f9251a;
            } while (!atomicLongFieldUpdater.compareAndSet(this, j13, (((long) 1) << 60) + (j13 & 1152921504606846975L)));
        }
        B2.q qVar = n.f9265s;
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f9246v;
            if (atomicReferenceFieldUpdater.compareAndSet(this, qVar, th)) {
                z10 = true;
                break;
            }
            if (atomicReferenceFieldUpdater.get(this) != qVar) {
                z10 = false;
                break;
            }
        }
        if (z6) {
            do {
                j12 = atomicLongFieldUpdater.get(this);
            } while (!atomicLongFieldUpdater.compareAndSet(this, j12, (((long) 3) << 60) + (j12 & 1152921504606846975L)));
        } else {
            do {
                j3 = atomicLongFieldUpdater.get(this);
                int i7 = (int) (j3 >> 60);
                if (i7 == 0) {
                    j10 = j3 & 1152921504606846975L;
                    j11 = 2;
                } else {
                    if (i7 != 1) {
                        break;
                    }
                    j10 = j3 & 1152921504606846975L;
                    j11 = 3;
                }
            } while (!atomicLongFieldUpdater.compareAndSet(this, j3, (j11 << 60) + j10));
        }
        r();
        if (z10) {
            loop3: while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f9247w;
                obj = atomicReferenceFieldUpdater2.get(this);
                B2.q qVar2 = obj == null ? n.f9263q : n.f9264r;
                while (!atomicReferenceFieldUpdater2.compareAndSet(this, obj, qVar2)) {
                    if (atomicReferenceFieldUpdater2.get(this) != obj) {
                        break;
                    }
                }
            }
            if (obj != null) {
                E.e(1, obj);
                ((InterfaceC2782k) obj).b(v());
            }
        }
        return z10;
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:?, code lost:
    
        return r1;
     */
    @Override // U8.A
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object p(java.lang.Object r23) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instruction units count: 215
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: U8.l.p(java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x008f, code lost:
    
        r1 = (U8.t) ((X8.b) X8.b.f10552m.get(r1));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final U8.t q(long r13) {
        /*
            Method dump skipped, instruction units count: 308
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: U8.l.q(long):U8.t");
    }

    public final void r() throws IllegalAccessException, InvocationTargetException {
        A(o.get(this), false);
    }

    public final void s(long j3) {
        A9.k kVarB;
        t tVar = (t) f9244t.get(this);
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f9240p;
            long j10 = atomicLongFieldUpdater.get(this);
            if (j3 < Math.max(((long) this.l) + j10, f9241q.get(this))) {
                return;
            }
            if (atomicLongFieldUpdater.compareAndSet(this, j10, j10 + 1)) {
                long j11 = n.f9252b;
                long j12 = j10 / j11;
                int i7 = (int) (j10 % j11);
                if (tVar.f10577n != j12) {
                    t tVarU = u(j12, tVar);
                    if (tVarU == null) {
                        continue;
                    } else {
                        tVar = tVarU;
                    }
                }
                Object objL = L(tVar, i7, j10, null);
                if (objL != n.o) {
                    tVar.b();
                    InterfaceC2782k interfaceC2782k = this.f9248m;
                    if (interfaceC2782k != null && (kVarB = X8.a.b(interfaceC2782k, objL, null)) != null) {
                        throw kVarB;
                    }
                } else if (j10 < y()) {
                    tVar.b();
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x0195, code lost:
    
        z(r16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0198, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void t() {
        /*
            Method dump skipped, instruction units count: 409
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: U8.l.t():void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:83:0x01ae, code lost:
    
        r3 = (U8.t) r3.c();
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01b5, code lost:
    
        if (r3 != null) goto L90;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String toString() {
        /*
            Method dump skipped, instruction units count: 478
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: U8.l.toString():java.lang.String");
    }

    public final t u(long j3, t tVar) {
        Object objD;
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j10;
        t tVar2 = n.f9251a;
        m mVar = m.f9250t;
        loop0: while (true) {
            objD = X8.a.d(tVar, j3, mVar);
            if (!X8.a.g(objD)) {
                X8.r rVarE = X8.a.e(objD);
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f9244t;
                    X8.r rVar = (X8.r) atomicReferenceFieldUpdater.get(this);
                    if (rVar.f10577n >= rVarE.f10577n) {
                        break loop0;
                    }
                    if (!rVarE.j()) {
                        break;
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, rVar, rVarE)) {
                        if (atomicReferenceFieldUpdater.get(this) != rVar) {
                            if (rVarE.f()) {
                                rVarE.e();
                            }
                        }
                    }
                    if (rVar.f()) {
                        rVar.e();
                    }
                }
            } else {
                break;
            }
        }
        if (X8.a.g(objD)) {
            r();
            if (tVar.f10577n * ((long) n.f9252b) >= y()) {
                return null;
            }
            tVar.b();
            return null;
        }
        t tVar3 = (t) X8.a.e(objD);
        boolean zD = D();
        long j11 = tVar3.f10577n;
        if (!zD && j3 <= f9241q.get(this) / ((long) n.f9252b)) {
            while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f9245u;
                X8.r rVar2 = (X8.r) atomicReferenceFieldUpdater2.get(this);
                if (rVar2.f10577n >= j11 || !tVar3.j()) {
                    break;
                }
                while (!atomicReferenceFieldUpdater2.compareAndSet(this, rVar2, tVar3)) {
                    if (atomicReferenceFieldUpdater2.get(this) != rVar2) {
                        if (tVar3.f()) {
                            tVar3.e();
                        }
                    }
                }
                if (rVar2.f()) {
                    rVar2.e();
                }
            }
        }
        if (j11 <= j3) {
            return tVar3;
        }
        long j12 = ((long) n.f9252b) * j11;
        do {
            atomicLongFieldUpdater = f9240p;
            j10 = atomicLongFieldUpdater.get(this);
            if (j10 >= j12) {
                break;
            }
        } while (!atomicLongFieldUpdater.compareAndSet(this, j10, j12));
        if (j11 * ((long) n.f9252b) >= y()) {
            return null;
        }
        tVar3.b();
        return null;
    }

    public final Throwable v() {
        return (Throwable) f9246v.get(this);
    }

    public final Throwable w() {
        Throwable thV = v();
        return thV == null ? new u("Channel was closed") : thV;
    }

    public final Throwable x() {
        Throwable thV = v();
        return thV == null ? new C0668v("Channel was closed") : thV;
    }

    public final long y() {
        return o.get(this) & 1152921504606846975L;
    }
}
