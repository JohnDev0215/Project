package S8;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import n7.C1995j;
import q7.InterfaceC2194h;

/* JADX INFO: loaded from: classes.dex */
public abstract class M extends N implements B {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f8090r = AtomicReferenceFieldUpdater.newUpdater(M.class, Object.class, "_queue$volatile");

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f8091s = AtomicReferenceFieldUpdater.newUpdater(M.class, Object.class, "_delayed$volatile");

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f8092t = AtomicIntegerFieldUpdater.newUpdater(M.class, "_isCompleted$volatile");
    private volatile /* synthetic */ Object _delayed$volatile;
    private volatile /* synthetic */ int _isCompleted$volatile = 0;
    private volatile /* synthetic */ Object _queue$volatile;

    @Override // S8.B
    public F I(long j3, q0 q0Var, InterfaceC2194h interfaceC2194h) {
        return A.f8081a.I(j3, q0Var, interfaceC2194h);
    }

    @Override // S8.AbstractC0721s
    public final void L(InterfaceC2194h interfaceC2194h, Runnable runnable) {
        X(runnable);
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0017, code lost:
    
        r7 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0099  */
    @Override // S8.N
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long U() {
        /*
            r10 = this;
            boolean r0 = r10.V()
            r1 = 0
            if (r0 == 0) goto L9
            return r1
        L9:
            r10.Y()
        Lc:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = S8.M.f8090r
            java.lang.Object r3 = r0.get(r10)
            B2.q r4 = S8.AbstractC0727y.c
            r5 = 0
            if (r3 != 0) goto L19
        L17:
            r7 = r5
            goto L49
        L19:
            boolean r6 = r3 instanceof X8.l
            if (r6 == 0) goto L3d
            r6 = r3
            X8.l r6 = (X8.l) r6
            java.lang.Object r7 = r6.d()
            B2.q r8 = X8.l.f10570g
            if (r7 == r8) goto L2b
            java.lang.Runnable r7 = (java.lang.Runnable) r7
            goto L49
        L2b:
            X8.l r6 = r6.c()
        L2f:
            boolean r4 = r0.compareAndSet(r10, r3, r6)
            if (r4 == 0) goto L36
            goto Lc
        L36:
            java.lang.Object r4 = r0.get(r10)
            if (r4 == r3) goto L2f
            goto Lc
        L3d:
            if (r3 != r4) goto L40
            goto L17
        L40:
            boolean r6 = r0.compareAndSet(r10, r3, r5)
            if (r6 == 0) goto Lb7
            r7 = r3
            java.lang.Runnable r7 = (java.lang.Runnable) r7
        L49:
            if (r7 == 0) goto L4f
            r7.run()
            return r1
        L4f:
            n7.j r3 = r10.f8095p
            r6 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            if (r3 != 0) goto L5a
        L58:
            r8 = r6
            goto L62
        L5a:
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L61
            goto L58
        L61:
            r8 = r1
        L62:
            int r3 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r3 != 0) goto L67
            goto Lb6
        L67:
            java.lang.Object r0 = r0.get(r10)
            if (r0 == 0) goto L8f
            boolean r3 = r0 instanceof X8.l
            if (r3 == 0) goto L8b
            X8.l r0 = (X8.l) r0
            java.util.concurrent.atomic.AtomicLongFieldUpdater r3 = X8.l.f10569f
            long r3 = r3.get(r0)
            r8 = 1073741823(0x3fffffff, double:5.304989472E-315)
            long r8 = r8 & r3
            int r0 = (int) r8
            r8 = 1152921503533105152(0xfffffffc0000000, double:1.2882296003504729E-231)
            long r3 = r3 & r8
            r8 = 30
            long r3 = r3 >> r8
            int r3 = (int) r3
            if (r0 != r3) goto Lb6
            goto L8f
        L8b:
            if (r0 != r4) goto Lb6
        L8d:
            r1 = r6
            goto Lb6
        L8f:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = S8.M.f8091s
            java.lang.Object r10 = r0.get(r10)
            S8.L r10 = (S8.L) r10
            if (r10 == 0) goto L8d
            monitor-enter(r10)
            S8.K[] r0 = r10.f10581a     // Catch: java.lang.Throwable -> Lb3
            if (r0 == 0) goto La1
            r3 = 0
            r5 = r0[r3]     // Catch: java.lang.Throwable -> Lb3
        La1:
            monitor-exit(r10)
            if (r5 != 0) goto La5
            goto L8d
        La5:
            long r3 = r5.l
            long r5 = java.lang.System.nanoTime()
            long r3 = r3 - r5
            int r10 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r10 >= 0) goto Lb1
            goto Lb6
        Lb1:
            r1 = r3
            goto Lb6
        Lb3:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        Lb6:
            return r1
        Lb7:
            java.lang.Object r6 = r0.get(r10)
            if (r6 == r3) goto L40
            goto Lc
        */
        throw new UnsupportedOperationException("Method not decompiled: S8.M.U():long");
    }

    public void X(Runnable runnable) {
        Y();
        if (!Z(runnable)) {
            RunnableC0728z.f8152u.X(runnable);
            return;
        }
        Thread threadR = R();
        if (Thread.currentThread() != threadR) {
            LockSupport.unpark(threadR);
        }
    }

    public final void Y() {
        K kB;
        L l = (L) f8091s.get(this);
        if (l == null || X8.u.f10580b.get(l) == 0) {
            return;
        }
        long jNanoTime = System.nanoTime();
        do {
            synchronized (l) {
                try {
                    K[] kArr = l.f10581a;
                    K k10 = kArr != null ? kArr[0] : null;
                    if (k10 != null) {
                        kB = ((jNanoTime - k10.l) > 0L ? 1 : ((jNanoTime - k10.l) == 0L ? 0 : -1)) >= 0 ? Z(k10) : false ? l.b(0) : null;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } while (kB != null);
    }

    public final boolean Z(Runnable runnable) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8090r;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (f8092t.get(this) != 0) {
                return false;
            }
            if (obj == null) {
                while (!atomicReferenceFieldUpdater.compareAndSet(this, null, runnable)) {
                    if (atomicReferenceFieldUpdater.get(this) != null) {
                        break;
                    }
                }
                return true;
            }
            if (!(obj instanceof X8.l)) {
                if (obj == AbstractC0727y.c) {
                    return false;
                }
                X8.l lVar = new X8.l(8, true);
                lVar.a((Runnable) obj);
                lVar.a(runnable);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, lVar)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                        break;
                    }
                }
                return true;
            }
            X8.l lVar2 = (X8.l) obj;
            int iA = lVar2.a(runnable);
            if (iA == 0) {
                return true;
            }
            if (iA == 1) {
                X8.l lVarC = lVar2.c();
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, lVarC) && atomicReferenceFieldUpdater.get(this) == obj) {
                }
            } else if (iA == 2) {
                return false;
            }
        }
    }

    public final boolean a0() {
        C1995j c1995j = this.f8095p;
        if (!(c1995j != null ? c1995j.isEmpty() : true)) {
            return false;
        }
        L l = (L) f8091s.get(this);
        if (l != null && X8.u.f10580b.get(l) != 0) {
            return false;
        }
        Object obj = f8090r.get(this);
        if (obj == null) {
            return true;
        }
        if (obj instanceof X8.l) {
            long j3 = X8.l.f10569f.get((X8.l) obj);
            if (((int) (1073741823 & j3)) == ((int) ((j3 & 1152921503533105152L) >> 30))) {
                return true;
            }
        } else if (obj == AbstractC0727y.c) {
            return true;
        }
        return false;
    }

    public final void b0(long j3, K k10) {
        int iB;
        Thread threadR;
        boolean z6 = f8092t.get(this) != 0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8091s;
        if (z6) {
            iB = 1;
        } else {
            L l = (L) atomicReferenceFieldUpdater.get(this);
            if (l == null) {
                L l10 = new L();
                l10.c = j3;
                while (!atomicReferenceFieldUpdater.compareAndSet(this, null, l10) && atomicReferenceFieldUpdater.get(this) == null) {
                }
                Object obj = atomicReferenceFieldUpdater.get(this);
                A7.m.c(obj);
                l = (L) obj;
            }
            iB = k10.b(j3, l, this);
        }
        if (iB != 0) {
            if (iB == 1) {
                W(j3, k10);
                return;
            } else {
                if (iB != 2) {
                    throw new IllegalStateException("unexpected result");
                }
                return;
            }
        }
        L l11 = (L) atomicReferenceFieldUpdater.get(this);
        if (l11 != null) {
            synchronized (l11) {
                K[] kArr = l11.f10581a;
                k = kArr != null ? kArr[0] : null;
            }
        }
        if (k != k10 || Thread.currentThread() == (threadR = R())) {
            return;
        }
        LockSupport.unpark(threadR);
    }

    @Override // S8.B
    public final void i(long j3, C0712i c0712i) {
        long j10 = j3 > 0 ? j3 >= 9223372036854L ? Long.MAX_VALUE : 1000000 * j3 : 0L;
        if (j10 < 4611686018427387903L) {
            long jNanoTime = System.nanoTime();
            I i7 = new I(this, j10 + jNanoTime, c0712i);
            b0(jNanoTime, i7);
            c0712i.v(new C0708e(i7, 1));
        }
    }

    @Override // S8.N
    public void shutdown() {
        K kB;
        n0.f8122a.set(null);
        f8092t.set(this, 1);
        loop0: while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8090r;
            Object obj = atomicReferenceFieldUpdater.get(this);
            B2.q qVar = AbstractC0727y.c;
            if (obj != null) {
                if (!(obj instanceof X8.l)) {
                    if (obj != qVar) {
                        X8.l lVar = new X8.l(8, true);
                        lVar.a((Runnable) obj);
                        while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, lVar)) {
                            if (atomicReferenceFieldUpdater.get(this) != obj) {
                                break;
                            }
                        }
                        break loop0;
                    }
                    break;
                }
                ((X8.l) obj).b();
                break;
            }
            while (!atomicReferenceFieldUpdater.compareAndSet(this, null, qVar)) {
                if (atomicReferenceFieldUpdater.get(this) != null) {
                    break;
                }
            }
            break loop0;
        }
        while (U() <= 0) {
        }
        long jNanoTime = System.nanoTime();
        while (true) {
            L l = (L) f8091s.get(this);
            if (l == null) {
                return;
            }
            synchronized (l) {
                kB = X8.u.f10580b.get(l) > 0 ? l.b(0) : null;
            }
            if (kB == null) {
                return;
            } else {
                W(jNanoTime, kB);
            }
        }
    }
}
