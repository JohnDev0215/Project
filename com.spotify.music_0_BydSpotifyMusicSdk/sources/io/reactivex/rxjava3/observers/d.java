package io.reactivex.rxjava3.observers;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import p.ab;

/* JADX INFO: loaded from: classes.dex */
public final class d implements Observer, Disposable {
    public final Observer c;
    public Disposable e;
    public boolean k;
    public ab l;
    public volatile boolean m;

    public d(Observer observer) {
        this.c = observer;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.m = true;
        this.e.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.e.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.m) {
            return;
        }
        synchronized (this) {
            try {
                if (this.m) {
                    return;
                }
                if (!this.k) {
                    this.m = true;
                    this.k = true;
                    this.c.onComplete();
                } else {
                    ab abVar = this.l;
                    if (abVar == null) {
                        abVar = new ab(1);
                        this.l = abVar;
                    }
                    abVar.b(NotificationLite.c);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.m) {
            RxJavaPlugins.b(th);
            return;
        }
        synchronized (this) {
            try {
                boolean z = true;
                if (!this.m) {
                    if (this.k) {
                        this.m = true;
                        ab abVar = this.l;
                        if (abVar == null) {
                            abVar = new ab(1);
                            this.l = abVar;
                        }
                        abVar.c[0] = NotificationLite.f(th);
                        return;
                    }
                    this.m = true;
                    this.k = true;
                    z = false;
                }
                if (z) {
                    RxJavaPlugins.b(th);
                } else {
                    this.c.onError(th);
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x0066, code lost:
    
        r2 = r2[r4];
     */
    @Override // io.reactivex.rxjava3.core.Observer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onNext(java.lang.Object r7) {
        /*
            r6 = this;
            boolean r0 = r6.m
            if (r0 == 0) goto L5
            return
        L5:
            if (r7 != 0) goto L16
            io.reactivex.rxjava3.disposables.Disposable r7 = r6.e
            r7.dispose()
            java.lang.String r7 = "onNext called with a null value."
            java.lang.NullPointerException r7 = io.reactivex.rxjava3.internal.util.c.b(r7)
            r6.onError(r7)
            return
        L16:
            monitor-enter(r6)
            boolean r0 = r6.m     // Catch: java.lang.Throwable -> L1d
            if (r0 == 0) goto L1f
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L1d
            return
        L1d:
            r7 = move-exception
            goto L6d
        L1f:
            boolean r0 = r6.k     // Catch: java.lang.Throwable -> L1d
            r1 = 1
            if (r0 == 0) goto L34
            p.ab r0 = r6.l     // Catch: java.lang.Throwable -> L1d
            if (r0 != 0) goto L2f
            p.ab r0 = new p.ab     // Catch: java.lang.Throwable -> L1d
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L1d
            r6.l = r0     // Catch: java.lang.Throwable -> L1d
        L2f:
            r0.b(r7)     // Catch: java.lang.Throwable -> L1d
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L1d
            return
        L34:
            r6.k = r1     // Catch: java.lang.Throwable -> L1d
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L1d
            io.reactivex.rxjava3.core.Observer r0 = r6.c
            r0.onNext(r7)
        L3c:
            monitor-enter(r6)
            p.ab r7 = r6.l     // Catch: java.lang.Throwable -> L46
            r0 = 0
            if (r7 != 0) goto L48
            r6.k = r0     // Catch: java.lang.Throwable -> L46
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L46
            goto L62
        L46:
            r7 = move-exception
            goto L6b
        L48:
            r1 = 0
            r6.l = r1     // Catch: java.lang.Throwable -> L46
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L46
            io.reactivex.rxjava3.core.Observer r1 = r6.c
            java.lang.Object[] r2 = r7.c
        L50:
            if (r2 == 0) goto L3c
            r3 = r0
        L53:
            int r4 = r7.b
            if (r3 >= r4) goto L66
            r5 = r2[r3]
            if (r5 != 0) goto L5c
            goto L66
        L5c:
            boolean r4 = io.reactivex.rxjava3.internal.util.NotificationLite.c(r1, r5)
            if (r4 == 0) goto L63
        L62:
            return
        L63:
            int r3 = r3 + 1
            goto L53
        L66:
            r2 = r2[r4]
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            goto L50
        L6b:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L46
            throw r7
        L6d:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L1d
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.observers.d.onNext(java.lang.Object):void");
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.e, disposable)) {
            this.e = disposable;
            this.c.onSubscribe(this);
        }
    }
}
