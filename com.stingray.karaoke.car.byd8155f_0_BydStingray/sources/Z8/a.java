package Z8;

import A7.z;
import B2.q;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: classes.dex */
public final class a extends Thread {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f10990t = AtomicIntegerFieldUpdater.newUpdater(a.class, "workerCtl$volatile");
    private volatile int indexInArray;
    public final m l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final z f10991m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public b f10992n;
    private volatile Object nextParkedWorker;
    public long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f10993p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f10994q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f10995r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ c f10996s;
    private volatile /* synthetic */ int workerCtl$volatile;

    public a(c cVar, int i7) {
        this.f10996s = cVar;
        setDaemon(true);
        setContextClassLoader(cVar.getClass().getClassLoader());
        this.l = new m();
        this.f10991m = new z();
        this.f10992n = b.o;
        this.nextParkedWorker = c.f11004v;
        int iNanoTime = (int) System.nanoTime();
        this.f10994q = iNanoTime == 0 ? 42 : iNanoTime;
        f(i7);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0043, code lost:
    
        r13 = Z8.m.d.get(r3);
        r0 = Z8.m.c.get(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004f, code lost:
    
        if (r13 == r0) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0057, code lost:
    
        if (Z8.m.f11021e.get(r3) != 0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005a, code lost:
    
        r0 = r0 - 1;
        r1 = r3.c(r0, true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0060, code lost:
    
        if (r1 == null) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0062, code lost:
    
        r2 = r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final Z8.i a(boolean r13) {
        /*
            Method dump skipped, instruction units count: 201
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: Z8.a.a(boolean):Z8.i");
    }

    public final int b() {
        return this.indexInArray;
    }

    public final Object c() {
        return this.nextParkedWorker;
    }

    public final int d(int i7) {
        int i10 = this.f10994q;
        int i11 = i10 ^ (i10 << 13);
        int i12 = i11 ^ (i11 >> 17);
        int i13 = i12 ^ (i12 << 5);
        this.f10994q = i13;
        int i14 = i7 - 1;
        return (i14 & i7) == 0 ? i14 & i13 : (Integer.MAX_VALUE & i13) % i7;
    }

    public final i e() {
        int iD = d(2);
        c cVar = this.f10996s;
        if (iD == 0) {
            i iVar = (i) cVar.f11007p.d();
            return iVar != null ? iVar : (i) cVar.f11008q.d();
        }
        i iVar2 = (i) cVar.f11008q.d();
        return iVar2 != null ? iVar2 : (i) cVar.f11007p.d();
    }

    public final void f(int i7) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f10996s.o);
        sb.append("-worker-");
        sb.append(i7 == 0 ? "TERMINATED" : String.valueOf(i7));
        setName(sb.toString());
        this.indexInArray = i7;
    }

    public final void g(Object obj) {
        this.nextParkedWorker = obj;
    }

    public final boolean h(b bVar) {
        b bVar2 = this.f10992n;
        boolean z6 = bVar2 == b.l;
        if (z6) {
            c.f11002t.addAndGet(this.f10996s, 4398046511104L);
        }
        if (bVar2 != bVar) {
            this.f10992n = bVar;
        }
        return z6;
    }

    public final i i(int i7) {
        i iVarC;
        long j3;
        long j10;
        AtomicLongFieldUpdater atomicLongFieldUpdater = c.f11002t;
        c cVar = this.f10996s;
        int i10 = (int) (atomicLongFieldUpdater.get(cVar) & 2097151);
        if (i10 < 2) {
            return null;
        }
        int iD = d(i10);
        long jMin = Long.MAX_VALUE;
        for (int i11 = 0; i11 < i10; i11++) {
            iD++;
            if (iD > i10) {
                iD = 1;
            }
            a aVar = (a) cVar.f11009r.b(iD);
            if (aVar != null && aVar != this) {
                m mVar = aVar.l;
                if (i7 == 3) {
                    iVarC = mVar.b();
                } else {
                    mVar.getClass();
                    int i12 = m.d.get(mVar);
                    int i13 = m.c.get(mVar);
                    boolean z6 = i7 == 1;
                    while (i12 != i13 && (!z6 || m.f11021e.get(mVar) != 0)) {
                        int i14 = i12 + 1;
                        iVarC = mVar.c(i12, z6);
                        if (iVarC != null) {
                            break;
                        }
                        i12 = i14;
                    }
                    iVarC = null;
                }
                z zVar = this.f10991m;
                if (iVarC != null) {
                    zVar.l = iVarC;
                    j10 = -1;
                } else {
                    while (true) {
                        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = m.f11020b;
                        i iVar = (i) atomicReferenceFieldUpdater.get(mVar);
                        j3 = -2;
                        if (iVar == null) {
                            break;
                        }
                        if (((iVar.f11013m ? 1 : 2) & i7) != 0) {
                            k.f11018f.getClass();
                            long jNanoTime = System.nanoTime() - iVar.l;
                            long j11 = k.f11016b;
                            if (jNanoTime >= j11) {
                                while (!atomicReferenceFieldUpdater.compareAndSet(mVar, iVar, null)) {
                                    if (atomicReferenceFieldUpdater.get(mVar) != iVar) {
                                        break;
                                    }
                                }
                                zVar.l = iVar;
                                j3 = -1;
                                break;
                            }
                            j3 = j11 - jNanoTime;
                            break;
                        }
                        break;
                    }
                    j10 = j3;
                }
                if (j10 == -1) {
                    i iVar2 = (i) zVar.l;
                    zVar.l = null;
                    return iVar2;
                }
                if (j10 > 0) {
                    jMin = Math.min(jMin, j10);
                }
            }
        }
        if (jMin == Long.MAX_VALUE) {
            jMin = 0;
        }
        this.f10993p = jMin;
        return null;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        boolean z6;
        boolean z10 = false;
        loop0: while (true) {
            boolean z11 = z10;
            while (true) {
                c cVar = this.f10996s;
                cVar.getClass();
                if (c.f11003u.get(cVar) == 0) {
                    b bVar = this.f10992n;
                    b bVar2 = b.f10999p;
                    if (bVar == bVar2) {
                        break loop0;
                    }
                    i iVarA = a(this.f10995r);
                    long j3 = -2097152;
                    if (iVarA != null) {
                        this.f10993p = 0L;
                        this.o = 0L;
                        if (this.f10992n == b.f10998n) {
                            this.f10992n = b.f10997m;
                        }
                        boolean z12 = iVarA.f11013m;
                        c cVar2 = this.f10996s;
                        if (z12) {
                            if (h(b.f10997m) && !cVar2.m() && !cVar2.k(c.f11002t.get(cVar2))) {
                                cVar2.m();
                            }
                            cVar2.getClass();
                            try {
                                iVarA.run();
                            } catch (Throwable th) {
                                Thread threadCurrentThread = Thread.currentThread();
                                threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
                            }
                            c.f11002t.addAndGet(cVar2, -2097152L);
                            if (this.f10992n != bVar2) {
                                this.f10992n = b.o;
                            }
                        } else {
                            cVar2.getClass();
                            try {
                                iVarA.run();
                            } catch (Throwable th2) {
                                Thread threadCurrentThread2 = Thread.currentThread();
                                threadCurrentThread2.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread2, th2);
                            }
                        }
                    } else {
                        this.f10995r = z10;
                        if (this.f10993p == 0) {
                            Object obj = this.nextParkedWorker;
                            q qVar = c.f11004v;
                            if (obj != qVar ? true : z10) {
                                f10990t.set(this, -1);
                                while (this.nextParkedWorker != c.f11004v) {
                                    AtomicIntegerFieldUpdater atomicIntegerFieldUpdater2 = f10990t;
                                    if (atomicIntegerFieldUpdater2.get(this) != -1) {
                                        break;
                                    }
                                    c cVar3 = this.f10996s;
                                    cVar3.getClass();
                                    AtomicIntegerFieldUpdater atomicIntegerFieldUpdater3 = c.f11003u;
                                    if (atomicIntegerFieldUpdater3.get(cVar3) != 0) {
                                        break;
                                    }
                                    b bVar3 = this.f10992n;
                                    b bVar4 = b.f10999p;
                                    if (bVar3 == bVar4) {
                                        break;
                                    }
                                    h(b.f10998n);
                                    Thread.interrupted();
                                    if (this.o == 0) {
                                        atomicIntegerFieldUpdater = atomicIntegerFieldUpdater2;
                                        this.o = System.nanoTime() + this.f10996s.f11006n;
                                    } else {
                                        atomicIntegerFieldUpdater = atomicIntegerFieldUpdater2;
                                    }
                                    LockSupport.parkNanos(this.f10996s.f11006n);
                                    if (System.nanoTime() - this.o >= 0) {
                                        this.o = 0L;
                                        c cVar4 = this.f10996s;
                                        synchronized (cVar4.f11009r) {
                                            try {
                                                if (!(atomicIntegerFieldUpdater3.get(cVar4) != 0)) {
                                                    AtomicLongFieldUpdater atomicLongFieldUpdater = c.f11002t;
                                                    if (((int) (atomicLongFieldUpdater.get(cVar4) & 2097151)) > cVar4.l && atomicIntegerFieldUpdater.compareAndSet(this, -1, 1)) {
                                                        int i7 = this.indexInArray;
                                                        z6 = false;
                                                        f(0);
                                                        cVar4.j(this, i7, 0);
                                                        int andDecrement = (int) (atomicLongFieldUpdater.getAndDecrement(cVar4) & 2097151);
                                                        if (andDecrement != i7) {
                                                            Object objB = cVar4.f11009r.b(andDecrement);
                                                            A7.m.c(objB);
                                                            a aVar = (a) objB;
                                                            cVar4.f11009r.c(i7, aVar);
                                                            aVar.f(i7);
                                                            cVar4.j(aVar, andDecrement, i7);
                                                        }
                                                        cVar4.f11009r.c(andDecrement, null);
                                                        this.f10992n = bVar4;
                                                    }
                                                }
                                                z6 = false;
                                            } catch (Throwable th3) {
                                                throw th3;
                                            }
                                        }
                                    } else {
                                        z6 = false;
                                    }
                                    z10 = z6;
                                }
                            } else {
                                c cVar5 = this.f10996s;
                                cVar5.getClass();
                                if (this.nextParkedWorker == qVar) {
                                    while (true) {
                                        AtomicLongFieldUpdater atomicLongFieldUpdater2 = c.f11001s;
                                        long j10 = atomicLongFieldUpdater2.get(cVar5);
                                        int i10 = this.indexInArray;
                                        this.nextParkedWorker = cVar5.f11009r.b((int) (j10 & 2097151));
                                        if (atomicLongFieldUpdater2.compareAndSet(cVar5, j10, ((2097152 + j10) & j3) | ((long) i10))) {
                                            break;
                                        } else {
                                            j3 = -2097152;
                                        }
                                    }
                                }
                            }
                            z10 = z10;
                        } else {
                            if (z11) {
                                h(b.f10998n);
                                Thread.interrupted();
                                LockSupport.parkNanos(this.f10993p);
                                this.f10993p = 0L;
                                break;
                            }
                            z11 = true;
                        }
                    }
                } else {
                    break loop0;
                }
            }
        }
        h(b.f10999p);
    }
}
