package io.reactivex.rxjava3.subjects;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes.dex */
public final class b extends e {
    public static final a[] o = new a[0];

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final a[] f86p = new a[0];
    public final AtomicReference c;
    public final AtomicReference e;
    public final Lock k;
    public final Lock l;
    public final AtomicReference m;
    public long n;

    public b(Object obj) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.k = reentrantReadWriteLock.readLock();
        this.l = reentrantReadWriteLock.writeLock();
        this.e = new AtomicReference(o);
        this.c = new AtomicReference(obj);
        this.m = new AtomicReference();
    }

    public static b a() {
        return new b(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void b(a aVar) {
        a[] aVarArr;
        while (true) {
            AtomicReference atomicReference = this.e;
            a[] aVarArr2 = (a[]) atomicReference.get();
            int length = aVarArr2.length;
            if (length == 0) {
                return;
            }
            int i = 0;
            while (true) {
                if (i >= length) {
                    i = -1;
                    break;
                } else if (aVarArr2[i] == aVar) {
                    break;
                } else {
                    i++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                aVarArr = o;
            } else {
                a[] aVarArr3 = new a[length - 1];
                System.arraycopy(aVarArr2, 0, aVarArr3, 0, i);
                System.arraycopy(aVarArr2, i + 1, aVarArr3, i, (length - i) - 1);
                aVarArr = aVarArr3;
            }
            while (!atomicReference.compareAndSet(aVarArr2, aVarArr)) {
                if (atomicReference.get() != aVarArr2) {
                    break;
                }
            }
            return;
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        AtomicReference atomicReference = this.m;
        Throwable th = io.reactivex.rxjava3.internal.util.c.a;
        while (!atomicReference.compareAndSet(null, th)) {
            if (atomicReference.get() != null) {
                return;
            }
        }
        NotificationLite notificationLite = NotificationLite.c;
        Lock lock = this.l;
        lock.lock();
        this.n++;
        this.c.lazySet(notificationLite);
        lock.unlock();
        for (a aVar : (a[]) this.e.getAndSet(f86p)) {
            aVar.a(this.n, notificationLite);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        io.reactivex.rxjava3.internal.util.c.c(th, "onError called with a null Throwable.");
        AtomicReference atomicReference = this.m;
        while (!atomicReference.compareAndSet(null, th)) {
            if (atomicReference.get() != null) {
                RxJavaPlugins.b(th);
                return;
            }
        }
        Object objF = NotificationLite.f(th);
        Lock lock = this.l;
        lock.lock();
        this.n++;
        this.c.lazySet((Serializable) objF);
        lock.unlock();
        for (a aVar : (a[]) this.e.getAndSet(f86p)) {
            aVar.a(this.n, objF);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        io.reactivex.rxjava3.internal.util.c.c(obj, "onNext called with a null value.");
        if (this.m.get() != null) {
            return;
        }
        Lock lock = this.l;
        lock.lock();
        this.n++;
        this.c.lazySet(obj);
        lock.unlock();
        for (a aVar : (a[]) this.e.get()) {
            aVar.a(this.n, obj);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (this.m.get() != null) {
            disposable.dispose();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0089, code lost:
    
        r0.l = false;
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
            io.reactivex.rxjava3.subjects.a r0 = new io.reactivex.rxjava3.subjects.a
            r0.<init>(r7, r6)
            r7.onSubscribe(r0)
        L8:
            java.util.concurrent.atomic.AtomicReference r1 = r6.e
            java.lang.Object r2 = r1.get()
            io.reactivex.rxjava3.subjects.a[] r2 = (io.reactivex.rxjava3.subjects.a[]) r2
            io.reactivex.rxjava3.subjects.a[] r3 = io.reactivex.rxjava3.subjects.b.f86p
            if (r2 != r3) goto L2a
            java.util.concurrent.atomic.AtomicReference r0 = r6.m
            java.lang.Object r0 = r0.get()
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            java.lang.Throwable r1 = io.reactivex.rxjava3.internal.util.c.a
            if (r0 != r1) goto L25
            r7.onComplete()
            goto L99
        L25:
            r7.onError(r0)
            goto L99
        L2a:
            int r3 = r2.length
            int r4 = r3 + 1
            io.reactivex.rxjava3.subjects.a[] r4 = new io.reactivex.rxjava3.subjects.a[r4]
            r5 = 0
            java.lang.System.arraycopy(r2, r5, r4, r5, r3)
            r4[r3] = r0
        L35:
            boolean r3 = r1.compareAndSet(r2, r4)
            if (r3 == 0) goto L9c
            boolean r7 = r0.o
            if (r7 == 0) goto L43
            r6.b(r0)
            goto L99
        L43:
            boolean r7 = r0.o
            if (r7 == 0) goto L48
            goto L99
        L48:
            monitor-enter(r0)
            boolean r7 = r0.o     // Catch: java.lang.Throwable -> L4f
            if (r7 == 0) goto L51
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4f
            goto L99
        L4f:
            r7 = move-exception
            goto L9a
        L51:
            boolean r7 = r0.k     // Catch: java.lang.Throwable -> L4f
            if (r7 == 0) goto L57
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4f
            goto L99
        L57:
            io.reactivex.rxjava3.subjects.b r7 = r0.e     // Catch: java.lang.Throwable -> L4f
            java.util.concurrent.locks.Lock r1 = r7.k     // Catch: java.lang.Throwable -> L4f
            r1.lock()     // Catch: java.lang.Throwable -> L4f
            long r2 = r7.n     // Catch: java.lang.Throwable -> L4f
            r0.f85p = r2     // Catch: java.lang.Throwable -> L4f
            java.util.concurrent.atomic.AtomicReference r7 = r7.c     // Catch: java.lang.Throwable -> L4f
            java.lang.Object r7 = r7.get()     // Catch: java.lang.Throwable -> L4f
            r1.unlock()     // Catch: java.lang.Throwable -> L4f
            r1 = 1
            if (r7 == 0) goto L70
            r2 = r1
            goto L71
        L70:
            r2 = r5
        L71:
            r0.l = r2     // Catch: java.lang.Throwable -> L4f
            r0.k = r1     // Catch: java.lang.Throwable -> L4f
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4f
            if (r7 == 0) goto L99
            boolean r7 = r0.test(r7)
            if (r7 == 0) goto L7f
            goto L99
        L7f:
            boolean r7 = r0.o
            if (r7 == 0) goto L84
            goto L99
        L84:
            monitor-enter(r0)
            p.ab r7 = r0.m     // Catch: java.lang.Throwable -> L8d
            if (r7 != 0) goto L8f
            r0.l = r5     // Catch: java.lang.Throwable -> L8d
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L8d
            goto L99
        L8d:
            r7 = move-exception
            goto L97
        L8f:
            r1 = 0
            r0.m = r1     // Catch: java.lang.Throwable -> L8d
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L8d
            r7.c(r0)
            goto L7f
        L97:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L8d
            throw r7
        L99:
            return
        L9a:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4f
            throw r7
        L9c:
            java.lang.Object r3 = r1.get()
            if (r3 == r2) goto L35
            goto L8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.subjects.b.subscribeActual(io.reactivex.rxjava3.core.Observer):void");
    }
}
