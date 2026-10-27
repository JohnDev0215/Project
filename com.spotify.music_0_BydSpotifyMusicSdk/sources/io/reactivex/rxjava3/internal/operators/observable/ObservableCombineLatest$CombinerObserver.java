package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableCombineLatest$CombinerObserver<T, R> extends AtomicReference<Disposable> implements Observer<T> {
    private static final long serialVersionUID = -4823716997131257941L;
    final int index;
    final ObservableCombineLatest$LatestCoordinator<T, R> parent;

    public ObservableCombineLatest$CombinerObserver(ObservableCombineLatest$LatestCoordinator observableCombineLatest$LatestCoordinator, int i) {
        this.parent = observableCombineLatest$LatestCoordinator;
        this.index = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x001f A[Catch: all -> 0x000b, TryCatch #0 {all -> 0x000b, blocks: (B:4:0x0005, B:6:0x0009, B:10:0x000d, B:15:0x0017, B:18:0x0021, B:17:0x001f), top: B:25:0x0005 }] */
    @Override // io.reactivex.rxjava3.core.Observer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onComplete() {
        /*
            r5 = this;
            io.reactivex.rxjava3.internal.operators.observable.ObservableCombineLatest$LatestCoordinator<T, R> r0 = r5.parent
            int r1 = r5.index
            monitor-enter(r0)
            java.lang.Object[] r2 = r0.latest     // Catch: java.lang.Throwable -> Lb
            if (r2 != 0) goto Ld
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb
            goto L2a
        Lb:
            r1 = move-exception
            goto L2b
        Ld:
            r1 = r2[r1]     // Catch: java.lang.Throwable -> Lb
            r3 = 1
            if (r1 != 0) goto L14
            r1 = r3
            goto L15
        L14:
            r1 = 0
        L15:
            if (r1 != 0) goto L1f
            int r4 = r0.complete     // Catch: java.lang.Throwable -> Lb
            int r4 = r4 + r3
            r0.complete = r4     // Catch: java.lang.Throwable -> Lb
            int r2 = r2.length     // Catch: java.lang.Throwable -> Lb
            if (r4 != r2) goto L21
        L1f:
            r0.done = r3     // Catch: java.lang.Throwable -> Lb
        L21:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb
            if (r1 == 0) goto L27
            r0.a()
        L27:
            r0.c()
        L2a:
            return
        L2b:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.observable.ObservableCombineLatest$CombinerObserver.onComplete():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x002b A[Catch: all -> 0x0017, TryCatch #0 {all -> 0x0017, blocks: (B:7:0x0011, B:9:0x0015, B:13:0x0019, B:18:0x0023, B:21:0x002d, B:20:0x002b), top: B:29:0x0011 }] */
    @Override // io.reactivex.rxjava3.core.Observer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onError(java.lang.Throwable r5) {
        /*
            r4 = this;
            io.reactivex.rxjava3.internal.operators.observable.ObservableCombineLatest$LatestCoordinator<T, R> r0 = r4.parent
            int r1 = r4.index
            io.reactivex.rxjava3.internal.util.AtomicThrowable r2 = r0.errors
            boolean r5 = r2.a(r5)
            if (r5 == 0) goto L39
            boolean r5 = r0.delayError
            if (r5 == 0) goto L33
            monitor-enter(r0)
            java.lang.Object[] r5 = r0.latest     // Catch: java.lang.Throwable -> L17
            if (r5 != 0) goto L19
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L17
            goto L39
        L17:
            r5 = move-exception
            goto L31
        L19:
            r1 = r5[r1]     // Catch: java.lang.Throwable -> L17
            r2 = 1
            if (r1 != 0) goto L20
            r1 = r2
            goto L21
        L20:
            r1 = 0
        L21:
            if (r1 != 0) goto L2b
            int r3 = r0.complete     // Catch: java.lang.Throwable -> L17
            int r3 = r3 + r2
            r0.complete = r3     // Catch: java.lang.Throwable -> L17
            int r5 = r5.length     // Catch: java.lang.Throwable -> L17
            if (r3 != r5) goto L2d
        L2b:
            r0.done = r2     // Catch: java.lang.Throwable -> L17
        L2d:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L17
            if (r1 == 0) goto L36
            goto L33
        L31:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L17
            throw r5
        L33:
            r0.a()
        L36:
            r0.c()
        L39:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.observable.ObservableCombineLatest$CombinerObserver.onError(java.lang.Throwable):void");
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        boolean z;
        ObservableCombineLatest$LatestCoordinator<T, R> observableCombineLatest$LatestCoordinator = this.parent;
        int i = this.index;
        synchronized (observableCombineLatest$LatestCoordinator) {
            try {
                Object[] objArr = observableCombineLatest$LatestCoordinator.latest;
                if (objArr == null) {
                    return;
                }
                Object obj2 = objArr[i];
                int i2 = observableCombineLatest$LatestCoordinator.active;
                if (obj2 == null) {
                    i2++;
                    observableCombineLatest$LatestCoordinator.active = i2;
                }
                objArr[i] = obj;
                if (i2 == objArr.length) {
                    observableCombineLatest$LatestCoordinator.queue.offer(objArr.clone());
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    observableCombineLatest$LatestCoordinator.c();
                }
            } finally {
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this, disposable);
    }
}
