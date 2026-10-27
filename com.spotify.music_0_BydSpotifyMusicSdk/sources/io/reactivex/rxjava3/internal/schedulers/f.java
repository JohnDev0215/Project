package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;

/* JADX INFO: loaded from: classes.dex */
public final class f implements Runnable {
    public final /* synthetic */ int c;
    public final Object e;
    public final /* synthetic */ Object k;

    public /* synthetic */ f(Object obj, int i, Object obj2) {
        this.c = i;
        this.k = obj;
        this.e = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.c) {
            case 0:
                ExecutorScheduler$DelayedRunnable executorScheduler$DelayedRunnable = (ExecutorScheduler$DelayedRunnable) this.e;
                SequentialDisposable sequentialDisposable = executorScheduler$DelayedRunnable.direct;
                Disposable disposableC = ((i) this.k).c(executorScheduler$DelayedRunnable);
                sequentialDisposable.getClass();
                DisposableHelper.c(sequentialDisposable, disposableC);
                break;
            default:
                ((v) this.e).l = true;
                ((w) this.k).c.remove((v) this.e);
                break;
        }
    }
}
