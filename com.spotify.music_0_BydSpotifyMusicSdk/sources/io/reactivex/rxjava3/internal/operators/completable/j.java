package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;

/* JADX INFO: loaded from: classes.dex */
public final class j extends Completable {
    public final /* synthetic */ int c;
    public final CompletableSource e;
    public final Scheduler k;

    public /* synthetic */ j(CompletableSource completableSource, Scheduler scheduler, int i) {
        this.c = i;
        this.e = completableSource;
        this.k = scheduler;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public final void d(CompletableObserver completableObserver) {
        int i = this.c;
        Scheduler scheduler = this.k;
        CompletableSource completableSource = this.e;
        switch (i) {
            case 0:
                completableSource.subscribe(new CompletableObserveOn$ObserveOnCompletableObserver(completableObserver, scheduler));
                break;
            default:
                CompletableSubscribeOn$SubscribeOnObserver completableSubscribeOn$SubscribeOnObserver = new CompletableSubscribeOn$SubscribeOnObserver(completableObserver, completableSource);
                completableObserver.onSubscribe(completableSubscribeOn$SubscribeOnObserver);
                Disposable disposableC = scheduler.c(completableSubscribeOn$SubscribeOnObserver);
                SequentialDisposable sequentialDisposable = completableSubscribeOn$SubscribeOnObserver.task;
                sequentialDisposable.getClass();
                DisposableHelper.c(sequentialDisposable, disposableC);
                break;
        }
    }
}
