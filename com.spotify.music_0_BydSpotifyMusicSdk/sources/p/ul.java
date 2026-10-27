package p;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes.dex */
public final class ul extends x22 {
    public static final Object[] n = null;
    public static final tl[] o = new tl[0];
    public final AtomicReference c;
    public final AtomicReference e;
    public final Lock k;
    public final Lock l;
    public long m;

    public ul() {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.k = reentrantReadWriteLock.readLock();
        this.l = reentrantReadWriteLock.writeLock();
        this.e = new AtomicReference(o);
        this.c = new AtomicReference();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(tl tlVar) {
        tl[] tlVarArr;
        while (true) {
            AtomicReference atomicReference = this.e;
            tl[] tlVarArr2 = (tl[]) atomicReference.get();
            int length = tlVarArr2.length;
            if (length == 0) {
                return;
            }
            int i = 0;
            while (true) {
                if (i >= length) {
                    i = -1;
                    break;
                } else if (tlVarArr2[i] == tlVar) {
                    break;
                } else {
                    i++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                tlVarArr = o;
            } else {
                tl[] tlVarArr3 = new tl[length - 1];
                System.arraycopy(tlVarArr2, 0, tlVarArr3, 0, i);
                System.arraycopy(tlVarArr2, i + 1, tlVarArr3, i, (length - i) - 1);
                tlVarArr = tlVarArr3;
            }
            while (!atomicReference.compareAndSet(tlVarArr2, tlVarArr)) {
                if (atomicReference.get() != tlVarArr2) {
                    break;
                }
            }
            return;
        }
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final void accept(Object obj) {
        if (obj == null) {
            throw new NullPointerException("value == null");
        }
        Lock lock = this.l;
        lock.lock();
        this.m++;
        this.c.lazySet(obj);
        lock.unlock();
        for (tl tlVar : (tl[]) this.e.get()) {
            long j = this.m;
            if (!tlVar.o) {
                if (tlVar.n) {
                    tlVar.test(obj);
                } else {
                    synchronized (tlVar) {
                        try {
                            if (!tlVar.o) {
                                if (tlVar.f190p != j) {
                                    if (tlVar.l) {
                                        ab abVar = tlVar.m;
                                        if (abVar == null) {
                                            abVar = new ab(0);
                                            tlVar.m = abVar;
                                        }
                                        abVar.b(obj);
                                    } else {
                                        tlVar.k = true;
                                        tlVar.n = true;
                                        tlVar.test(obj);
                                    }
                                }
                            }
                        } finally {
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x006d, code lost:
    
        r0.l = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0077, code lost:
    
        r1 = r7.c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0079, code lost:
    
        if (r1 == null) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x007b, code lost:
    
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x007c, code lost:
    
        r3 = r7.b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x007e, code lost:
    
        if (r2 >= r3) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0080, code lost:
    
        r5 = r1[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0082, code lost:
    
        if (r5 != null) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0085, code lost:
    
        r0.test(r5);
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x008b, code lost:
    
        r1 = r1[r3];
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.reactivex.rxjava3.core.Observable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void subscribeActual(io.reactivex.rxjava3.core.Observer r7) {
        /*
            r6 = this;
            p.tl r0 = new p.tl
            r0.<init>(r7, r6)
            r7.onSubscribe(r0)
        L8:
            java.util.concurrent.atomic.AtomicReference r7 = r6.e
            java.lang.Object r1 = r7.get()
            p.tl[] r1 = (p.tl[]) r1
            int r2 = r1.length
            int r3 = r2 + 1
            p.tl[] r3 = new p.tl[r3]
            r4 = 0
            java.lang.System.arraycopy(r1, r4, r3, r4, r2)
            r3[r2] = r0
        L1b:
            boolean r2 = r7.compareAndSet(r1, r3)
            if (r2 == 0) goto L95
            boolean r7 = r0.o
            if (r7 == 0) goto L2a
            r6.a(r0)
            goto L92
        L2a:
            boolean r7 = r0.o
            if (r7 == 0) goto L30
            goto L92
        L30:
            monitor-enter(r0)
            boolean r7 = r0.o     // Catch: java.lang.Throwable -> L37
            if (r7 == 0) goto L39
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L37
            goto L92
        L37:
            r7 = move-exception
            goto L93
        L39:
            boolean r7 = r0.k     // Catch: java.lang.Throwable -> L37
            if (r7 == 0) goto L3f
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L37
            goto L92
        L3f:
            p.ul r7 = r0.e     // Catch: java.lang.Throwable -> L37
            java.util.concurrent.locks.Lock r1 = r7.k     // Catch: java.lang.Throwable -> L37
            r1.lock()     // Catch: java.lang.Throwable -> L37
            long r2 = r7.m     // Catch: java.lang.Throwable -> L37
            r0.f190p = r2     // Catch: java.lang.Throwable -> L37
            java.util.concurrent.atomic.AtomicReference r7 = r7.c     // Catch: java.lang.Throwable -> L37
            java.lang.Object r7 = r7.get()     // Catch: java.lang.Throwable -> L37
            r1.unlock()     // Catch: java.lang.Throwable -> L37
            r1 = 1
            if (r7 == 0) goto L58
            r2 = r1
            goto L59
        L58:
            r2 = r4
        L59:
            r0.l = r2     // Catch: java.lang.Throwable -> L37
            r0.k = r1     // Catch: java.lang.Throwable -> L37
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L37
            if (r7 == 0) goto L92
            r0.test(r7)
        L63:
            boolean r7 = r0.o
            if (r7 == 0) goto L68
            goto L92
        L68:
            monitor-enter(r0)
            p.ab r7 = r0.m     // Catch: java.lang.Throwable -> L71
            if (r7 != 0) goto L73
            r0.l = r4     // Catch: java.lang.Throwable -> L71
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L71
            goto L92
        L71:
            r7 = move-exception
            goto L90
        L73:
            r1 = 0
            r0.m = r1     // Catch: java.lang.Throwable -> L71
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L71
            java.lang.Object[] r1 = r7.c
        L79:
            if (r1 == 0) goto L63
            r2 = r4
        L7c:
            int r3 = r7.b
            if (r2 >= r3) goto L8b
            r5 = r1[r2]
            if (r5 != 0) goto L85
            goto L8b
        L85:
            r0.test(r5)
            int r2 = r2 + 1
            goto L7c
        L8b:
            r1 = r1[r3]
            java.lang.Object[] r1 = (java.lang.Object[]) r1
            goto L79
        L90:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L71
            throw r7
        L92:
            return
        L93:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L37
            throw r7
        L95:
            java.lang.Object r2 = r7.get()
            if (r2 == r1) goto L1b
            goto L8
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ul.subscribeActual(io.reactivex.rxjava3.core.Observer):void");
    }
}
