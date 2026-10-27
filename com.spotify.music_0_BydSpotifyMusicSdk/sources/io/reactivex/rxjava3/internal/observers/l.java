package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class l implements SingleObserver {
    public final /* synthetic */ int c;
    public final AtomicReference e;
    public final SingleObserver k;

    public /* synthetic */ l(AtomicReference atomicReference, SingleObserver singleObserver, int i) {
        this.c = i;
        this.e = atomicReference;
        this.k = singleObserver;
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        int i = this.c;
        SingleObserver singleObserver = this.k;
        switch (i) {
            case 0:
                singleObserver.onError(th);
                break;
            default:
                singleObserver.onError(th);
                break;
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        int i = this.c;
        AtomicReference atomicReference = this.e;
        switch (i) {
            case 0:
                DisposableHelper.c(atomicReference, disposable);
                break;
            default:
                DisposableHelper.c(atomicReference, disposable);
                break;
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        int i = this.c;
        SingleObserver singleObserver = this.k;
        switch (i) {
            case 0:
                singleObserver.onSuccess(obj);
                break;
            default:
                singleObserver.onSuccess(obj);
                break;
        }
    }
}
