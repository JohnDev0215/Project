package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.internal.UndeliveredElementException;
import p.bk0;
import p.br;
import p.c00;
import p.cb2;
import p.dr;
import p.er;
import p.go;
import p.he1;
import p.ho;
import p.k12;
import p.lq2;
import p.mf2;
import p.ow;
import p.qv2;
import p.rv2;
import p.ry0;
import p.tz2;
import p.u4;
import p.ux2;
import p.v03;
import p.vr2;
import p.y10;
import p.yp;
import p.yq;
import p.zp;

/* JADX INFO: loaded from: classes.dex */
public class a implements yq {
    public static final AtomicLongFieldUpdater k = AtomicLongFieldUpdater.newUpdater(a.class, "sendersAndCloseStatus");
    public static final AtomicLongFieldUpdater l = AtomicLongFieldUpdater.newUpdater(a.class, "receivers");
    public static final AtomicLongFieldUpdater m = AtomicLongFieldUpdater.newUpdater(a.class, "bufferEnd");
    public static final AtomicLongFieldUpdater n = AtomicLongFieldUpdater.newUpdater(a.class, "completedExpandBuffersAndPauseFlag");
    public static final AtomicReferenceFieldUpdater o = AtomicReferenceFieldUpdater.newUpdater(a.class, Object.class, "sendSegment");

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f88p = AtomicReferenceFieldUpdater.newUpdater(a.class, Object.class, "receiveSegment");
    public static final AtomicReferenceFieldUpdater q = AtomicReferenceFieldUpdater.newUpdater(a.class, Object.class, "bufferEndSegment");
    public static final AtomicReferenceFieldUpdater r = AtomicReferenceFieldUpdater.newUpdater(a.class, Object.class, "_closeCause");
    public static final AtomicReferenceFieldUpdater s = AtomicReferenceFieldUpdater.newUpdater(a.class, Object.class, "closeHandler");
    private volatile Object _closeCause;
    private volatile long bufferEnd;
    private volatile Object bufferEndSegment;
    public final int c;
    private volatile Object closeHandler;
    private volatile long completedExpandBuffersAndPauseFlag;
    public final ry0 e;
    private volatile Object receiveSegment;
    private volatile long receivers;
    private volatile Object sendSegment;
    private volatile long sendersAndCloseStatus;

    public a(int i, ry0 ry0Var) {
        this.c = i;
        this.e = ry0Var;
        if (i < 0) {
            throw new IllegalArgumentException(u4.f("Invalid channel capacity: ", i, ", should be >=0").toString());
        }
        er erVar = ho.a;
        this.bufferEnd = i != 0 ? i != Integer.MAX_VALUE ? i : Long.MAX_VALUE : 0L;
        this.completedExpandBuffersAndPauseFlag = m.get(this);
        er erVar2 = new er(0L, null, this, 3);
        this.sendSegment = erVar2;
        this.receiveSegment = erVar2;
        if (A()) {
            erVar2 = ho.a;
            v03.g(erVar2, "null cannot be cast to non-null type kotlinx.coroutines.channels.ChannelSegment<E of kotlinx.coroutines.channels.BufferedChannel>");
        }
        this.bufferEndSegment = erVar2;
        this._closeCause = ho.s;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object D(kotlinx.coroutines.channels.a r14, p.c00 r15) {
        /*
            boolean r0 = r15 instanceof kotlinx.coroutines.channels.BufferedChannel$receiveCatching$1
            if (r0 == 0) goto L14
            r0 = r15
            kotlinx.coroutines.channels.BufferedChannel$receiveCatching$1 r0 = (kotlinx.coroutines.channels.BufferedChannel$receiveCatching$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r6 = r0
            goto L1a
        L14:
            kotlinx.coroutines.channels.BufferedChannel$receiveCatching$1 r0 = new kotlinx.coroutines.channels.BufferedChannel$receiveCatching$1
            r0.<init>(r14, r15)
            goto L12
        L1a:
            java.lang.Object r15 = r6.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L36
            if (r1 != r2) goto L2e
            kotlin.a.d(r15)
            p.dr r15 = (p.dr) r15
            java.lang.Object r14 = r15.a
            goto L9e
        L2e:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r15)
            throw r14
        L36:
            kotlin.a.d(r15)
            r15 = 0
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = kotlinx.coroutines.channels.a.f88p
            java.lang.Object r1 = r1.get(r14)
            p.er r1 = (p.er) r1
        L42:
            boolean r3 = r14.y()
            if (r3 == 0) goto L52
            java.lang.Throwable r14 = r14.s()
            p.br r15 = new p.br
            r15.<init>(r14)
            goto La4
        L52:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r3 = kotlinx.coroutines.channels.a.l
            long r4 = r3.getAndIncrement(r14)
            int r3 = p.ho.b
            long r7 = (long) r3
            long r7 = r4 / r7
            long r9 = (long) r3
            long r9 = r4 % r9
            int r3 = (int) r9
            long r9 = r1.k
            int r9 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r9 == 0) goto L70
            p.er r7 = r14.r(r7, r1)
            if (r7 != 0) goto L6e
            goto L42
        L6e:
            r13 = r7
            goto L71
        L70:
            r13 = r1
        L71:
            r7 = r14
            r8 = r13
            r9 = r3
            r10 = r4
            r12 = r15
            java.lang.Object r1 = r7.I(r8, r9, r10, r12)
            p.tz2 r7 = p.ho.m
            if (r1 == r7) goto La5
            p.tz2 r7 = p.ho.o
            if (r1 != r7) goto L8f
            long r7 = r14.v()
            int r1 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r1 >= 0) goto L8d
            r13.a()
        L8d:
            r1 = r13
            goto L42
        L8f:
            p.tz2 r15 = p.ho.n
            if (r1 != r15) goto La0
            r6.label = r2
            r1 = r14
            r2 = r13
            java.lang.Object r14 = r1.E(r2, r3, r4, r6)
            if (r14 != r0) goto L9e
            return r0
        L9e:
            r15 = r14
            goto La4
        La0:
            r13.a()
            r15 = r1
        La4:
            return r15
        La5:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "unexpected"
            java.lang.String r15 = r15.toString()
            r14.<init>(r15)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.a.D(kotlinx.coroutines.channels.a, p.c00):java.lang.Object");
    }

    public static boolean H(Object obj) {
        if (obj instanceof yp) {
            v03.g(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            return ho.a((yp) obj, lq2.a, null);
        }
        throw new IllegalStateException(("Unexpected waiter: " + obj).toString());
    }

    public static final er a(a aVar, long j, er erVar) {
        Object objA;
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j2;
        long j3;
        aVar.getClass();
        er erVar2 = ho.a;
        BufferedChannelKt$createSegmentFunction$1 bufferedChannelKt$createSegmentFunction$1 = BufferedChannelKt$createSegmentFunction$1.k;
        loop0: while (true) {
            objA = ow.a(erVar, j, bufferedChannelKt$createSegmentFunction$1);
            if (!bk0.H(objA)) {
                cb2 cb2VarY = bk0.y(objA);
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = o;
                    cb2 cb2Var = (cb2) atomicReferenceFieldUpdater.get(aVar);
                    if (cb2Var.k >= cb2VarY.k) {
                        break loop0;
                    }
                    if (!cb2VarY.i()) {
                        break;
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(aVar, cb2Var, cb2VarY)) {
                        if (atomicReferenceFieldUpdater.get(aVar) != cb2Var) {
                            if (cb2VarY.e()) {
                                cb2VarY.d();
                            }
                        }
                    }
                    if (cb2Var.e()) {
                        cb2Var.d();
                    }
                }
            } else {
                break;
            }
        }
        boolean zH = bk0.H(objA);
        AtomicLongFieldUpdater atomicLongFieldUpdater2 = l;
        if (zH) {
            aVar.o();
            if (erVar.k * ((long) ho.b) >= atomicLongFieldUpdater2.get(aVar)) {
                return null;
            }
            erVar.a();
            return null;
        }
        er erVar3 = (er) bk0.y(objA);
        long j4 = erVar3.k;
        if (j4 <= j) {
            return erVar3;
        }
        long j5 = ((long) ho.b) * j4;
        do {
            atomicLongFieldUpdater = k;
            j2 = atomicLongFieldUpdater.get(aVar);
            j3 = 1152921504606846975L & j2;
            if (j3 >= j5) {
                break;
            }
        } while (!atomicLongFieldUpdater.compareAndSet(aVar, j2, j3 + (((long) ((int) (j2 >> 60))) << 60)));
        if (j4 * ((long) ho.b) >= atomicLongFieldUpdater2.get(aVar)) {
            return null;
        }
        erVar3.a();
        return null;
    }

    public static final void b(a aVar, Object obj, zp zpVar) {
        UndeliveredElementException undeliveredElementExceptionB;
        ry0 ry0Var = aVar.e;
        if (ry0Var != null && (undeliveredElementExceptionB = kotlinx.coroutines.internal.b.b(ry0Var, obj, null)) != null) {
            ux2.m(zpVar.m, undeliveredElementExceptionB);
        }
        zpVar.k(kotlin.a.b(aVar.u()));
    }

    public static final int c(a aVar, er erVar, int i, Object obj, long j, Object obj2, boolean z) {
        aVar.getClass();
        erVar.m(i, obj);
        if (z) {
            return aVar.J(erVar, i, obj, j, obj2, z);
        }
        Object objK = erVar.k(i);
        if (objK == null) {
            if (aVar.f(j)) {
                if (erVar.j(null, i, ho.d)) {
                    return 1;
                }
            } else {
                if (obj2 == null) {
                    return 3;
                }
                if (erVar.j(null, i, obj2)) {
                    return 2;
                }
            }
        } else if (objK instanceof qv2) {
            erVar.m(i, null);
            if (aVar.G(objK, obj)) {
                erVar.n(i, ho.i);
                return 0;
            }
            tz2 tz2Var = ho.k;
            if (erVar.n.getAndSet((i * 2) + 1, tz2Var) != tz2Var) {
                erVar.l(i, true);
            }
            return 5;
        }
        return aVar.J(erVar, i, obj, j, obj2, z);
    }

    public static void w(a aVar) {
        aVar.getClass();
        AtomicLongFieldUpdater atomicLongFieldUpdater = n;
        if ((atomicLongFieldUpdater.addAndGet(aVar, 1L) & 4611686018427387904L) != 0) {
            while ((atomicLongFieldUpdater.get(aVar) & 4611686018427387904L) != 0) {
            }
        }
    }

    public final boolean A() {
        long j = m.get(this);
        return j == 0 || j == Long.MAX_VALUE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x0011, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void B(long r5, p.er r7) {
        /*
            r4 = this;
        L0:
            long r0 = r7.k
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 >= 0) goto L11
            p.pw r0 = r7.b()
            p.er r0 = (p.er) r0
            if (r0 != 0) goto Lf
            goto L11
        Lf:
            r7 = r0
            goto L0
        L11:
            boolean r5 = r7.c()
            if (r5 == 0) goto L22
            p.pw r5 = r7.b()
            p.er r5 = (p.er) r5
            if (r5 != 0) goto L20
            goto L22
        L20:
            r7 = r5
            goto L11
        L22:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = kotlinx.coroutines.channels.a.q
            java.lang.Object r6 = r5.get(r4)
            p.cb2 r6 = (p.cb2) r6
            long r0 = r6.k
            long r2 = r7.k
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L33
            goto L49
        L33:
            boolean r0 = r7.i()
            if (r0 != 0) goto L3a
            goto L11
        L3a:
            boolean r0 = r5.compareAndSet(r4, r6, r7)
            if (r0 == 0) goto L4a
            boolean r5 = r6.e()
            if (r5 == 0) goto L49
            r6.d()
        L49:
            return
        L4a:
            java.lang.Object r0 = r5.get(r4)
            if (r0 == r6) goto L3a
            boolean r5 = r7.e()
            if (r5 == 0) goto L22
            r7.d()
            goto L22
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.a.B(long, p.er):void");
    }

    public final Object C(Object obj, c00 c00Var) {
        UndeliveredElementException undeliveredElementExceptionB;
        zp zpVar = new zp(1, vr2.k(c00Var));
        zpVar.r();
        ry0 ry0Var = this.e;
        if (ry0Var == null || (undeliveredElementExceptionB = kotlinx.coroutines.internal.b.b(ry0Var, obj, null)) == null) {
            zpVar.k(kotlin.a.b(u()));
        } else {
            kotlin.a.a(undeliveredElementExceptionB, u());
            zpVar.k(kotlin.a.b(undeliveredElementExceptionB));
        }
        Object objQ = zpVar.q();
        return objQ == CoroutineSingletons.c ? objQ : lq2.a;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object E(p.er r17, int r18, long r19, p.c00 r21) {
        /*
            Method dump skipped, instruction units count: 303
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.a.E(p.er, int, long, p.c00):java.lang.Object");
    }

    public final void F(qv2 qv2Var, boolean z) {
        if (qv2Var instanceof yp) {
            ((c00) qv2Var).k(kotlin.a.b(z ? t() : u()));
            return;
        }
        if (qv2Var instanceof k12) {
            ((k12) qv2Var).c.k(new dr(new br(s())));
            return;
        }
        if (!(qv2Var instanceof go)) {
            throw new IllegalStateException(("Unexpected waiter: " + qv2Var).toString());
        }
        go goVar = (go) qv2Var;
        zp zpVar = goVar.e;
        v03.h(zpVar);
        goVar.e = null;
        goVar.c = ho.l;
        Throwable thS = goVar.k.s();
        if (thS == null) {
            zpVar.k(Boolean.FALSE);
        } else {
            zpVar.k(kotlin.a.b(thS));
        }
    }

    public final boolean G(Object obj, Object obj2) {
        boolean z = obj instanceof k12;
        ry0 ry0Var = this.e;
        if (z) {
            v03.g(obj, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveCatching<E of kotlinx.coroutines.channels.BufferedChannel>");
            dr drVar = new dr(obj2);
            zp zpVar = ((k12) obj).c;
            return ho.a(zpVar, drVar, ry0Var != null ? kotlinx.coroutines.internal.b.a(ry0Var, obj2, zpVar.m) : null);
        }
        if (!(obj instanceof go)) {
            if (obj instanceof yp) {
                v03.g(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<E of kotlinx.coroutines.channels.BufferedChannel>");
                yp ypVar = (yp) obj;
                return ho.a(ypVar, obj2, ry0Var != null ? kotlinx.coroutines.internal.b.a(ry0Var, obj2, ((zp) ypVar).m) : null);
            }
            throw new IllegalStateException(("Unexpected receiver type: " + obj).toString());
        }
        v03.g(obj, "null cannot be cast to non-null type kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator<E of kotlinx.coroutines.channels.BufferedChannel>");
        go goVar = (go) obj;
        zp zpVar2 = goVar.e;
        v03.h(zpVar2);
        goVar.e = null;
        goVar.c = obj2;
        Boolean bool = Boolean.TRUE;
        ry0 ry0Var2 = goVar.k.e;
        return ho.a(zpVar2, bool, ry0Var2 != null ? kotlinx.coroutines.internal.b.a(ry0Var2, obj2, zpVar2.m) : null);
    }

    public final Object I(er erVar, int i, long j, Object obj) {
        Object objK = erVar.k(i);
        AtomicReferenceArray atomicReferenceArray = erVar.n;
        AtomicLongFieldUpdater atomicLongFieldUpdater = k;
        if (objK == null) {
            if (j >= (atomicLongFieldUpdater.get(this) & 1152921504606846975L)) {
                if (obj == null) {
                    return ho.n;
                }
                if (erVar.j(objK, i, obj)) {
                    q();
                    return ho.m;
                }
            }
        } else if (objK == ho.d && erVar.j(objK, i, ho.i)) {
            q();
            Object obj2 = atomicReferenceArray.get(i * 2);
            erVar.m(i, null);
            return obj2;
        }
        while (true) {
            Object objK2 = erVar.k(i);
            if (objK2 == null || objK2 == ho.e) {
                if (j < (atomicLongFieldUpdater.get(this) & 1152921504606846975L)) {
                    if (erVar.j(objK2, i, ho.h)) {
                        q();
                        return ho.o;
                    }
                } else {
                    if (obj == null) {
                        return ho.n;
                    }
                    if (erVar.j(objK2, i, obj)) {
                        q();
                        return ho.m;
                    }
                }
            } else {
                if (objK2 != ho.d) {
                    tz2 tz2Var = ho.j;
                    if (objK2 != tz2Var && objK2 != ho.h) {
                        if (objK2 == ho.l) {
                            q();
                            return ho.o;
                        }
                        if (objK2 != ho.g && erVar.j(objK2, i, ho.f)) {
                            boolean z = objK2 instanceof rv2;
                            if (z) {
                                objK2 = ((rv2) objK2).a;
                            }
                            if (H(objK2)) {
                                erVar.n(i, ho.i);
                                q();
                                Object obj3 = atomicReferenceArray.get(i * 2);
                                erVar.m(i, null);
                                return obj3;
                            }
                            erVar.n(i, tz2Var);
                            erVar.l(i, false);
                            if (z) {
                                q();
                            }
                            return ho.o;
                        }
                    }
                    return ho.o;
                }
                if (erVar.j(objK2, i, ho.i)) {
                    q();
                    Object obj4 = atomicReferenceArray.get(i * 2);
                    erVar.m(i, null);
                    return obj4;
                }
            }
        }
    }

    public final int J(er erVar, int i, Object obj, long j, Object obj2, boolean z) {
        while (true) {
            Object objK = erVar.k(i);
            if (objK == null) {
                if (!f(j) || z) {
                    if (z) {
                        if (erVar.j(null, i, ho.j)) {
                            erVar.l(i, false);
                            return 4;
                        }
                    } else {
                        if (obj2 == null) {
                            return 3;
                        }
                        if (erVar.j(null, i, obj2)) {
                            return 2;
                        }
                    }
                } else if (erVar.j(null, i, ho.d)) {
                    return 1;
                }
            } else {
                if (objK != ho.e) {
                    tz2 tz2Var = ho.k;
                    if (objK == tz2Var) {
                        erVar.m(i, null);
                        return 5;
                    }
                    if (objK == ho.h) {
                        erVar.m(i, null);
                        return 5;
                    }
                    if (objK == ho.l) {
                        erVar.m(i, null);
                        o();
                        return 4;
                    }
                    erVar.m(i, null);
                    if (objK instanceof rv2) {
                        objK = ((rv2) objK).a;
                    }
                    if (G(objK, obj)) {
                        erVar.n(i, ho.i);
                        return 0;
                    }
                    if (erVar.n.getAndSet((i * 2) + 1, tz2Var) != tz2Var) {
                        erVar.l(i, true);
                    }
                    return 5;
                }
                if (erVar.j(objK, i, ho.d)) {
                    return 1;
                }
            }
        }
    }

    public final void K(long j) {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j2;
        long j3;
        if (A()) {
            return;
        }
        do {
            atomicLongFieldUpdater = m;
        } while (atomicLongFieldUpdater.get(this) <= j);
        int i = ho.c;
        int i2 = 0;
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater2 = n;
            if (i2 >= i) {
                do {
                    j2 = atomicLongFieldUpdater2.get(this);
                } while (!atomicLongFieldUpdater2.compareAndSet(this, j2, 4611686018427387904L + (j2 & 4611686018427387903L)));
                while (true) {
                    long j4 = atomicLongFieldUpdater.get(this);
                    long j5 = atomicLongFieldUpdater2.get(this);
                    long j6 = j5 & 4611686018427387903L;
                    boolean z = (j5 & 4611686018427387904L) != 0;
                    if (j4 == j6 && j4 == atomicLongFieldUpdater.get(this)) {
                        break;
                    } else if (!z) {
                        atomicLongFieldUpdater2.compareAndSet(this, j5, j6 + 4611686018427387904L);
                    }
                }
                do {
                    j3 = atomicLongFieldUpdater2.get(this);
                } while (!atomicLongFieldUpdater2.compareAndSet(this, j3, j3 & 4611686018427387903L));
                return;
            }
            long j7 = atomicLongFieldUpdater.get(this);
            if (j7 == (atomicLongFieldUpdater2.get(this) & 4611686018427387903L) && j7 == atomicLongFieldUpdater.get(this)) {
                return;
            } else {
                i2++;
            }
        }
    }

    @Override // p.hb2
    public final void d(ry0 ry0Var) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = s;
            if (atomicReferenceFieldUpdater.compareAndSet(this, null, ry0Var)) {
                return;
            }
        } while (atomicReferenceFieldUpdater.get(this) == null);
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            tz2 tz2Var = ho.q;
            if (obj != tz2Var) {
                if (obj == ho.r) {
                    throw new IllegalStateException("Another handler was already registered and successfully invoked".toString());
                }
                throw new IllegalStateException(("Another handler is already registered: " + obj).toString());
            }
            tz2 tz2Var2 = ho.r;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, tz2Var, tz2Var2)) {
                if (atomicReferenceFieldUpdater.get(this) != tz2Var) {
                    break;
                }
            }
            ((ProduceKt$awaitClose$4$1) ry0Var).invoke(s());
            return;
        }
    }

    @Override // p.l12
    public final Object e(c00 c00Var) {
        return D(this, c00Var);
    }

    public final boolean f(long j) {
        return j < m.get(this) || j < l.get(this) + ((long) this.c);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v2, types: [p.zp] */
    /* JADX WARN: Type inference failed for: r14v6 */
    /* JADX WARN: Type inference failed for: r16v0, types: [java.lang.Object, kotlinx.coroutines.channels.a] */
    @Override // p.l12
    public final Object g(ContinuationImpl continuationImpl) throws Throwable {
        er erVar;
        ?? r14;
        zp zpVar;
        ry0 ry0VarA;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f88p;
        er erVar2 = (er) atomicReferenceFieldUpdater.get(this);
        while (!y()) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = l;
            long andIncrement = atomicLongFieldUpdater.getAndIncrement(this);
            long j = ho.b;
            long j2 = andIncrement / j;
            int i = (int) (andIncrement % j);
            if (erVar2.k != j2) {
                er erVarR = r(j2, erVar2);
                if (erVarR == null) {
                    continue;
                } else {
                    erVar = erVarR;
                }
            } else {
                erVar = erVar2;
            }
            Object objI = I(erVar, i, andIncrement, null);
            tz2 tz2Var = ho.m;
            if (objI == tz2Var) {
                throw new IllegalStateException("unexpected".toString());
            }
            tz2 tz2Var2 = ho.o;
            if (objI == tz2Var2) {
                if (andIncrement < v()) {
                    erVar.a();
                }
                erVar2 = erVar;
            } else {
                if (objI != ho.n) {
                    erVar.a();
                    return objI;
                }
                zp zpVarS = v03.s(vr2.k(continuationImpl));
                try {
                    Object objI2 = I(erVar, i, andIncrement, zpVarS);
                    try {
                        if (objI2 == tz2Var) {
                            zpVar = zpVarS;
                            zpVar.a(erVar, i);
                        } else {
                            zpVar = zpVarS;
                            ry0 ry0Var = this.e;
                            y10 y10Var = zpVar.m;
                            if (objI2 == tz2Var2) {
                                if (andIncrement < v()) {
                                    erVar.a();
                                }
                                er erVar3 = (er) atomicReferenceFieldUpdater.get(this);
                                while (true) {
                                    if (y()) {
                                        zpVar.k(kotlin.a.b(t()));
                                        break;
                                    }
                                    long andIncrement2 = atomicLongFieldUpdater.getAndIncrement(this);
                                    long j3 = ho.b;
                                    long j4 = andIncrement2 / j3;
                                    int i2 = (int) (andIncrement2 % j3);
                                    if (erVar3.k != j4) {
                                        er erVarR2 = r(j4, erVar3);
                                        if (erVarR2 != null) {
                                            erVar3 = erVarR2;
                                        }
                                    }
                                    y10 y10Var2 = y10Var;
                                    ry0 ry0Var2 = ry0Var;
                                    objI2 = I(erVar3, i2, andIncrement2, zpVar);
                                    if (objI2 == ho.m) {
                                        zpVar.a(erVar3, i2);
                                        break;
                                    }
                                    if (objI2 == ho.o) {
                                        if (andIncrement2 < v()) {
                                            erVar3.a();
                                        }
                                        y10Var = y10Var2;
                                        ry0Var = ry0Var2;
                                    } else {
                                        if (objI2 == ho.n) {
                                            throw new IllegalStateException("unexpected".toString());
                                        }
                                        erVar3.a();
                                        ry0VarA = ry0Var2 != null ? kotlinx.coroutines.internal.b.a(ry0Var2, objI2, y10Var2) : null;
                                    }
                                }
                            } else {
                                erVar.a();
                                ry0VarA = ry0Var != null ? kotlinx.coroutines.internal.b.a(ry0Var, objI2, y10Var) : null;
                            }
                            zpVar.z(objI2, ry0VarA);
                        }
                        return zpVar.q();
                    } catch (Throwable th) {
                        th = th;
                        r14 = tz2Var;
                        r14.y();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    r14 = zpVarS;
                }
            }
        }
        Throwable thT = t();
        int i3 = mf2.a;
        throw thT;
    }

    @Override // p.hb2
    public final boolean h(Throwable th) {
        return j(th, false);
    }

    @Override // p.l12
    public final void i(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new CancellationException("Channel was cancelled");
        }
        j(cancellationException, true);
    }

    @Override // p.l12
    public final go iterator() {
        return new go(this);
    }

    public final boolean j(Throwable th, boolean z) {
        boolean z2;
        long j;
        long j2;
        long j3;
        Object obj;
        long j4;
        long j5;
        AtomicLongFieldUpdater atomicLongFieldUpdater = k;
        if (z) {
            do {
                j5 = atomicLongFieldUpdater.get(this);
                if (((int) (j5 >> 60)) != 0) {
                    break;
                }
                er erVar = ho.a;
            } while (!atomicLongFieldUpdater.compareAndSet(this, j5, (((long) 1) << 60) + (j5 & 1152921504606846975L)));
        }
        tz2 tz2Var = ho.s;
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = r;
            if (atomicReferenceFieldUpdater.compareAndSet(this, tz2Var, th)) {
                z2 = true;
                break;
            }
            if (atomicReferenceFieldUpdater.get(this) != tz2Var) {
                z2 = false;
                break;
            }
        }
        if (z) {
            do {
                j4 = atomicLongFieldUpdater.get(this);
            } while (!atomicLongFieldUpdater.compareAndSet(this, j4, (((long) 3) << 60) + (j4 & 1152921504606846975L)));
        } else {
            do {
                j = atomicLongFieldUpdater.get(this);
                int i = (int) (j >> 60);
                if (i == 0) {
                    j2 = j & 1152921504606846975L;
                    j3 = 2;
                } else {
                    if (i != 1) {
                        break;
                    }
                    j2 = j & 1152921504606846975L;
                    j3 = 3;
                }
            } while (!atomicLongFieldUpdater.compareAndSet(this, j, (j3 << 60) + j2));
        }
        o();
        if (z2) {
            loop3: while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = s;
                obj = atomicReferenceFieldUpdater2.get(this);
                tz2 tz2Var2 = obj == null ? ho.q : ho.r;
                while (!atomicReferenceFieldUpdater2.compareAndSet(this, obj, tz2Var2)) {
                    if (atomicReferenceFieldUpdater2.get(this) != obj) {
                        break;
                    }
                }
            }
            if (obj != null) {
                he1.d(1, obj);
                ((ry0) obj).invoke(s());
            }
        }
        return z2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0091, code lost:
    
        r1 = (p.er) ((p.pw) p.pw.e.get(r1));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p.er k(long r13) {
        /*
            Method dump skipped, instruction units count: 310
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.a.k(long):p.er");
    }

    /* JADX WARN: Code restructure failed: missing block: B:153:?, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:?, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00eb, code lost:
    
        r5 = r27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00ed, code lost:
    
        b(r25, r26, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00f0, code lost:
    
        r2 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00f1, code lost:
    
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00f5, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00f6, code lost:
    
        r2 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0142, code lost:
    
        if (r18 >= r5.get(r25)) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0144, code lost:
    
        r27.a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0147, code lost:
    
        r2 = r23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0149, code lost:
    
        b(r25, r26, r2);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:107:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:155:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v34 */
    /* JADX WARN: Type inference failed for: r2v38 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9, types: [p.zp] */
    @Override // p.hb2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object l(java.lang.Object r26, p.c00 r27) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 485
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.a.l(java.lang.Object, p.c00):java.lang.Object");
    }

    @Override // p.l12
    public final Object m() {
        er erVar;
        AtomicLongFieldUpdater atomicLongFieldUpdater = l;
        long j = atomicLongFieldUpdater.get(this);
        long j2 = k.get(this);
        if (x(j2, true)) {
            return new br(s());
        }
        long j3 = j2 & 1152921504606846975L;
        Object obj = dr.b;
        if (j >= j3) {
            return obj;
        }
        Object obj2 = ho.k;
        er erVar2 = (er) f88p.get(this);
        while (!y()) {
            long andIncrement = atomicLongFieldUpdater.getAndIncrement(this);
            long j4 = ho.b;
            long j5 = andIncrement / j4;
            int i = (int) (andIncrement % j4);
            if (erVar2.k != j5) {
                er erVarR = r(j5, erVar2);
                if (erVarR == null) {
                    continue;
                } else {
                    erVar = erVarR;
                }
            } else {
                erVar = erVar2;
            }
            Object objI = I(erVar, i, andIncrement, obj2);
            if (objI == ho.m) {
                qv2 qv2Var = obj2 instanceof qv2 ? (qv2) obj2 : null;
                if (qv2Var != null) {
                    qv2Var.a(erVar, i);
                }
                K(andIncrement);
                erVar.h();
            } else if (objI == ho.o) {
                if (andIncrement < v()) {
                    erVar.a();
                }
                erVar2 = erVar;
            } else {
                if (objI == ho.n) {
                    throw new IllegalStateException("unexpected".toString());
                }
                erVar.a();
                obj = objI;
            }
            return obj;
        }
        return new br(s());
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:?, code lost:
    
        return r1;
     */
    @Override // p.hb2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object n(java.lang.Object r23) {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.a.n(java.lang.Object):java.lang.Object");
    }

    @Override // p.hb2
    public final boolean o() {
        return x(k.get(this), false);
    }

    public final void p(long j) {
        UndeliveredElementException undeliveredElementExceptionB;
        er erVar = (er) f88p.get(this);
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = l;
            long j2 = atomicLongFieldUpdater.get(this);
            if (j < Math.max(((long) this.c) + j2, m.get(this))) {
                return;
            }
            if (atomicLongFieldUpdater.compareAndSet(this, j2, j2 + 1)) {
                long j3 = ho.b;
                long j4 = j2 / j3;
                int i = (int) (j2 % j3);
                if (erVar.k != j4) {
                    er erVarR = r(j4, erVar);
                    if (erVarR == null) {
                        continue;
                    } else {
                        erVar = erVarR;
                    }
                }
                Object objI = I(erVar, i, j2, null);
                if (objI != ho.o) {
                    erVar.a();
                    ry0 ry0Var = this.e;
                    if (ry0Var != null && (undeliveredElementExceptionB = kotlinx.coroutines.internal.b.b(ry0Var, objI, null)) != null) {
                        throw undeliveredElementExceptionB;
                    }
                } else if (j2 < v()) {
                    erVar.a();
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x0196, code lost:
    
        w(r16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0199, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void q() {
        /*
            Method dump skipped, instruction units count: 410
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.a.q():void");
    }

    public final er r(long j, er erVar) {
        Object objA;
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j2;
        er erVar2 = ho.a;
        BufferedChannelKt$createSegmentFunction$1 bufferedChannelKt$createSegmentFunction$1 = BufferedChannelKt$createSegmentFunction$1.k;
        loop0: while (true) {
            objA = ow.a(erVar, j, bufferedChannelKt$createSegmentFunction$1);
            if (!bk0.H(objA)) {
                cb2 cb2VarY = bk0.y(objA);
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f88p;
                    cb2 cb2Var = (cb2) atomicReferenceFieldUpdater.get(this);
                    if (cb2Var.k >= cb2VarY.k) {
                        break loop0;
                    }
                    if (!cb2VarY.i()) {
                        break;
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, cb2Var, cb2VarY)) {
                        if (atomicReferenceFieldUpdater.get(this) != cb2Var) {
                            if (cb2VarY.e()) {
                                cb2VarY.d();
                            }
                        }
                    }
                    if (cb2Var.e()) {
                        cb2Var.d();
                    }
                }
            } else {
                break;
            }
        }
        if (bk0.H(objA)) {
            o();
            if (erVar.k * ((long) ho.b) >= v()) {
                return null;
            }
            erVar.a();
            return null;
        }
        er erVar3 = (er) bk0.y(objA);
        boolean zA = A();
        long j3 = erVar3.k;
        if (!zA && j <= m.get(this) / ((long) ho.b)) {
            while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = q;
                cb2 cb2Var2 = (cb2) atomicReferenceFieldUpdater2.get(this);
                if (cb2Var2.k >= j3 || !erVar3.i()) {
                    break;
                }
                while (!atomicReferenceFieldUpdater2.compareAndSet(this, cb2Var2, erVar3)) {
                    if (atomicReferenceFieldUpdater2.get(this) != cb2Var2) {
                        if (erVar3.e()) {
                            erVar3.d();
                        }
                    }
                }
                if (cb2Var2.e()) {
                    cb2Var2.d();
                }
            }
        }
        if (j3 <= j) {
            return erVar3;
        }
        long j4 = ((long) ho.b) * j3;
        do {
            atomicLongFieldUpdater = l;
            j2 = atomicLongFieldUpdater.get(this);
            if (j2 >= j4) {
                break;
            }
        } while (!atomicLongFieldUpdater.compareAndSet(this, j2, j4));
        if (j3 * ((long) ho.b) >= v()) {
            return null;
        }
        erVar3.a();
        return null;
    }

    public final Throwable s() {
        return (Throwable) r.get(this);
    }

    public final Throwable t() {
        Throwable thS = s();
        return thS == null ? new ClosedReceiveChannelException("Channel was closed") : thS;
    }

    /* JADX WARN: Code restructure failed: missing block: B:78:0x019c, code lost:
    
        r3 = (p.er) r3.b();
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01a3, code lost:
    
        if (r3 != null) goto L89;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String toString() {
        /*
            Method dump skipped, instruction units count: 480
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.a.toString():java.lang.String");
    }

    public final Throwable u() {
        Throwable thS = s();
        return thS == null ? new ClosedSendChannelException("Channel was closed") : thS;
    }

    public final long v() {
        return k.get(this) & 1152921504606846975L;
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x00c6, code lost:
    
        r0 = (p.er) ((p.pw) p.pw.e.get(r0));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean x(long r19, boolean r21) {
        /*
            Method dump skipped, instruction units count: 409
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.a.x(long, boolean):boolean");
    }

    public final boolean y() {
        return x(k.get(this), true);
    }

    public boolean z() {
        return false;
    }
}
