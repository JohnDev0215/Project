package Z8;

import B2.q;
import S8.AbstractC0727y;
import X8.p;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Executor, Closeable {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f11001s = AtomicLongFieldUpdater.newUpdater(c.class, "parkedWorkersStack$volatile");

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f11002t = AtomicLongFieldUpdater.newUpdater(c.class, "controlState$volatile");

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f11003u = AtomicIntegerFieldUpdater.newUpdater(c.class, "_isTerminated$volatile");

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final q f11004v = new q("NOT_IN_STACK", 3);
    private volatile /* synthetic */ int _isTerminated$volatile;
    private volatile /* synthetic */ long controlState$volatile;
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f11005m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f11006n;
    public final String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final f f11007p;
    private volatile /* synthetic */ long parkedWorkersStack$volatile;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final f f11008q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final p f11009r;

    public c(int i7, int i10, long j3, String str) {
        this.l = i7;
        this.f11005m = i10;
        this.f11006n = j3;
        this.o = str;
        if (i7 < 1) {
            throw new IllegalArgumentException(A7.k.g("Core pool size ", i7, " should be at least 1").toString());
        }
        if (i10 < i7) {
            throw new IllegalArgumentException(A7.k.i("Max pool size ", " should be greater than or equals to core pool size ", i10, i7).toString());
        }
        if (i10 > 2097150) {
            throw new IllegalArgumentException(A7.k.g("Max pool size ", i10, " should not exceed maximal supported number of threads 2097150").toString());
        }
        if (j3 <= 0) {
            throw new IllegalArgumentException(("Idle worker keep alive time " + j3 + " must be positive").toString());
        }
        this.f11007p = new f();
        this.f11008q = new f();
        this.f11009r = new p((i7 + 1) * 2);
        this.controlState$volatile = ((long) i7) << 42;
        this._isTerminated$volatile = 0;
    }

    public static /* synthetic */ void i(c cVar, Runnable runnable, boolean z6, int i7) {
        if ((i7 & 4) != 0) {
            z6 = false;
        }
        cVar.g(runnable, false, z6);
    }

    public final int b() {
        synchronized (this.f11009r) {
            try {
                if (f11003u.get(this) != 0) {
                    return -1;
                }
                AtomicLongFieldUpdater atomicLongFieldUpdater = f11002t;
                long j3 = atomicLongFieldUpdater.get(this);
                int i7 = (int) (j3 & 2097151);
                int i10 = i7 - ((int) ((j3 & 4398044413952L) >> 21));
                if (i10 < 0) {
                    i10 = 0;
                }
                if (i10 >= this.l) {
                    return 0;
                }
                if (i7 >= this.f11005m) {
                    return 0;
                }
                int i11 = ((int) (atomicLongFieldUpdater.get(this) & 2097151)) + 1;
                if (i11 <= 0 || this.f11009r.b(i11) != null) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                a aVar = new a(this, i11);
                this.f11009r.c(i11, aVar);
                if (i11 != ((int) (2097151 & atomicLongFieldUpdater.incrementAndGet(this)))) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                int i12 = i10 + 1;
                aVar.start();
                return i12;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x008b  */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void close() throws java.lang.InterruptedException {
        /*
            r8 = this;
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = Z8.c.f11003u
            r1 = 0
            r2 = 1
            boolean r0 = r0.compareAndSet(r8, r1, r2)
            if (r0 != 0) goto Lc
            goto Lb2
        Lc:
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            boolean r1 = r0 instanceof Z8.a
            r3 = 0
            if (r1 == 0) goto L18
            Z8.a r0 = (Z8.a) r0
            goto L19
        L18:
            r0 = r3
        L19:
            if (r0 == 0) goto L24
            Z8.c r1 = r0.f10996s
            boolean r1 = A7.m.a(r1, r8)
            if (r1 == 0) goto L24
            goto L25
        L24:
            r0 = r3
        L25:
            X8.p r1 = r8.f11009r
            monitor-enter(r1)
            java.util.concurrent.atomic.AtomicLongFieldUpdater r4 = Z8.c.f11002t     // Catch: java.lang.Throwable -> Lc4
            long r4 = r4.get(r8)     // Catch: java.lang.Throwable -> Lc4
            r6 = 2097151(0x1fffff, double:1.0361303E-317)
            long r4 = r4 & r6
            int r4 = (int) r4
            monitor-exit(r1)
            if (r2 > r4) goto L79
            r1 = r2
        L37:
            X8.p r5 = r8.f11009r
            java.lang.Object r5 = r5.b(r1)
            A7.m.c(r5)
            Z8.a r5 = (Z8.a) r5
            if (r5 == r0) goto L74
        L44:
            java.lang.Thread$State r6 = r5.getState()
            java.lang.Thread$State r7 = java.lang.Thread.State.TERMINATED
            if (r6 == r7) goto L55
            java.util.concurrent.locks.LockSupport.unpark(r5)
            r6 = 10000(0x2710, double:4.9407E-320)
            r5.join(r6)
            goto L44
        L55:
            Z8.m r5 = r5.l
            Z8.f r6 = r8.f11008q
            r5.getClass()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r7 = Z8.m.f11020b
            java.lang.Object r7 = r7.getAndSet(r5, r3)
            Z8.i r7 = (Z8.i) r7
            if (r7 == 0) goto L69
            r6.a(r7)
        L69:
            Z8.i r7 = r5.b()
            if (r7 != 0) goto L70
            goto L74
        L70:
            r6.a(r7)
            goto L69
        L74:
            if (r1 == r4) goto L79
            int r1 = r1 + 1
            goto L37
        L79:
            Z8.f r1 = r8.f11008q
            r1.b()
            Z8.f r1 = r8.f11007p
            r1.b()
        L83:
            if (r0 == 0) goto L8b
            Z8.i r1 = r0.a(r2)
            if (r1 != 0) goto Lb3
        L8b:
            Z8.f r1 = r8.f11007p
            java.lang.Object r1 = r1.d()
            Z8.i r1 = (Z8.i) r1
            if (r1 != 0) goto Lb3
            Z8.f r1 = r8.f11008q
            java.lang.Object r1 = r1.d()
            Z8.i r1 = (Z8.i) r1
            if (r1 != 0) goto Lb3
            if (r0 == 0) goto La6
            Z8.b r1 = Z8.b.f10999p
            r0.h(r1)
        La6:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = Z8.c.f11001s
            r1 = 0
            r0.set(r8, r1)
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = Z8.c.f11002t
            r0.set(r8, r1)
        Lb2:
            return
        Lb3:
            r1.run()     // Catch: java.lang.Throwable -> Lb7
            goto L83
        Lb7:
            r1 = move-exception
            java.lang.Thread r3 = java.lang.Thread.currentThread()
            java.lang.Thread$UncaughtExceptionHandler r4 = r3.getUncaughtExceptionHandler()
            r4.uncaughtException(r3, r1)
            goto L83
        Lc4:
            r8 = move-exception
            monitor-exit(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: Z8.c.close():void");
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        i(this, runnable, false, 6);
    }

    public final void g(Runnable runnable, boolean z6, boolean z10) {
        i jVar;
        b bVar;
        k.f11018f.getClass();
        long jNanoTime = System.nanoTime();
        if (runnable instanceof i) {
            jVar = (i) runnable;
            jVar.l = jNanoTime;
            jVar.f11013m = z6;
        } else {
            jVar = new j(runnable, jNanoTime, z6);
        }
        boolean z11 = jVar.f11013m;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f11002t;
        long jAddAndGet = z11 ? atomicLongFieldUpdater.addAndGet(this, 2097152L) : 0L;
        Thread threadCurrentThread = Thread.currentThread();
        a aVar = threadCurrentThread instanceof a ? (a) threadCurrentThread : null;
        if (aVar == null || !A7.m.a(aVar.f10996s, this)) {
            aVar = null;
        }
        if (aVar != null && (bVar = aVar.f10992n) != b.f10999p && (jVar.f11013m || bVar != b.f10997m)) {
            aVar.f10995r = true;
            m mVar = aVar.l;
            if (z10) {
                jVar = mVar.a(jVar);
            } else {
                mVar.getClass();
                i iVar = (i) m.f11020b.getAndSet(mVar, jVar);
                jVar = iVar == null ? null : mVar.a(iVar);
            }
        }
        if (jVar != null) {
            if (!(jVar.f11013m ? this.f11008q.a(jVar) : this.f11007p.a(jVar))) {
                throw new RejectedExecutionException(android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder(), this.o, " was terminated"));
            }
        }
        boolean z12 = z10 && aVar != null;
        if (z11) {
            if (z12 || m() || k(jAddAndGet)) {
                return;
            }
            m();
            return;
        }
        if (z12 || m() || k(atomicLongFieldUpdater.get(this))) {
            return;
        }
        m();
    }

    public final void j(a aVar, int i7, int i10) {
        while (true) {
            long j3 = f11001s.get(this);
            int i11 = (int) (2097151 & j3);
            long j10 = (2097152 + j3) & (-2097152);
            if (i11 == i7) {
                if (i10 == 0) {
                    Object objC = aVar.c();
                    while (true) {
                        if (objC == f11004v) {
                            i11 = -1;
                            break;
                        }
                        if (objC == null) {
                            i11 = 0;
                            break;
                        }
                        a aVar2 = (a) objC;
                        int iB = aVar2.b();
                        if (iB != 0) {
                            i11 = iB;
                            break;
                        }
                        objC = aVar2.c();
                    }
                } else {
                    i11 = i10;
                }
            }
            if (i11 >= 0) {
                if (f11001s.compareAndSet(this, j3, ((long) i11) | j10)) {
                    return;
                }
            }
        }
    }

    public final boolean k(long j3) {
        int i7 = ((int) (2097151 & j3)) - ((int) ((j3 & 4398044413952L) >> 21));
        if (i7 < 0) {
            i7 = 0;
        }
        int i10 = this.l;
        if (i7 < i10) {
            int iB = b();
            if (iB == 1 && i10 > 1) {
                b();
            }
            if (iB > 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean m() {
        q qVar;
        int iB;
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f11001s;
            long j3 = atomicLongFieldUpdater.get(this);
            a aVar = (a) this.f11009r.b((int) (2097151 & j3));
            if (aVar == null) {
                aVar = null;
            } else {
                long j10 = (2097152 + j3) & (-2097152);
                Object objC = aVar.c();
                while (true) {
                    qVar = f11004v;
                    if (objC == qVar) {
                        iB = -1;
                        break;
                    }
                    if (objC == null) {
                        iB = 0;
                        break;
                    }
                    a aVar2 = (a) objC;
                    iB = aVar2.b();
                    if (iB != 0) {
                        break;
                    }
                    objC = aVar2.c();
                }
                if (iB >= 0 && atomicLongFieldUpdater.compareAndSet(this, j3, j10 | ((long) iB))) {
                    aVar.g(qVar);
                }
            }
            if (aVar == null) {
                return false;
            }
            if (a.f10990t.compareAndSet(aVar, -1, 0)) {
                LockSupport.unpark(aVar);
                return true;
            }
        }
    }

    public final String toString() {
        ArrayList arrayList = new ArrayList();
        p pVar = this.f11009r;
        int iA = pVar.a();
        int i7 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        for (int i14 = 1; i14 < iA; i14++) {
            a aVar = (a) pVar.b(i14);
            if (aVar != null) {
                m mVar = aVar.l;
                mVar.getClass();
                int i15 = m.f11020b.get(mVar) != null ? (m.c.get(mVar) - m.d.get(mVar)) + 1 : m.c.get(mVar) - m.d.get(mVar);
                int iOrdinal = aVar.f10992n.ordinal();
                if (iOrdinal == 0) {
                    i7++;
                    StringBuilder sb = new StringBuilder();
                    sb.append(i15);
                    sb.append('c');
                    arrayList.add(sb.toString());
                } else if (iOrdinal == 1) {
                    i10++;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(i15);
                    sb2.append('b');
                    arrayList.add(sb2.toString());
                } else if (iOrdinal == 2) {
                    i11++;
                } else if (iOrdinal == 3) {
                    i12++;
                    if (i15 > 0) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(i15);
                        sb3.append('d');
                        arrayList.add(sb3.toString());
                    }
                } else {
                    if (iOrdinal != 4) {
                        throw new A9.k();
                    }
                    i13++;
                }
            }
        }
        long j3 = f11002t.get(this);
        StringBuilder sb4 = new StringBuilder();
        sb4.append(this.o);
        sb4.append('@');
        sb4.append(AbstractC0727y.n(this));
        sb4.append("[Pool Size {core = ");
        int i16 = this.l;
        sb4.append(i16);
        sb4.append(", max = ");
        sb4.append(this.f11005m);
        sb4.append("}, Worker States {CPU = ");
        sb4.append(i7);
        sb4.append(", blocking = ");
        sb4.append(i10);
        sb4.append(", parked = ");
        sb4.append(i11);
        sb4.append(", dormant = ");
        sb4.append(i12);
        sb4.append(", terminated = ");
        sb4.append(i13);
        sb4.append("}, running workers queues = ");
        sb4.append(arrayList);
        sb4.append(", global CPU queue size = ");
        sb4.append(this.f11007p.c());
        sb4.append(", global blocking queue size = ");
        sb4.append(this.f11008q.c());
        sb4.append(", Control State {created workers= ");
        sb4.append((int) (2097151 & j3));
        sb4.append(", blocking tasks = ");
        sb4.append((int) ((4398044413952L & j3) >> 21));
        sb4.append(", CPUs acquired = ");
        sb4.append(i16 - ((int) ((j3 & 9223367638808264704L) >> 42)));
        sb4.append("}]");
        return sb4.toString();
    }
}
