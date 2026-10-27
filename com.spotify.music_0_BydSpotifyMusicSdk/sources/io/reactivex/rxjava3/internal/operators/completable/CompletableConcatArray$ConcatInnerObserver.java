package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
final class CompletableConcatArray$ConcatInnerObserver extends AtomicInteger implements CompletableObserver {
    private static final long serialVersionUID = -7965400327305809232L;
    final CompletableObserver downstream;
    int index;
    final SequentialDisposable sd = new SequentialDisposable();
    final CompletableSource[] sources;

    public CompletableConcatArray$ConcatInnerObserver(CompletableObserver completableObserver, CompletableSource[] completableSourceArr) {
        this.downstream = completableObserver;
        this.sources = completableSourceArr;
    }

    public final void a() {
        if (!this.sd.isDisposed() && getAndIncrement() == 0) {
            CompletableSource[] completableSourceArr = this.sources;
            while (!this.sd.isDisposed()) {
                int i = this.index;
                this.index = i + 1;
                if (i == completableSourceArr.length) {
                    this.downstream.onComplete();
                    return;
                } else {
                    completableSourceArr[i].subscribe(this);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onComplete() {
        a();
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onSubscribe(Disposable disposable) {
        SequentialDisposable sequentialDisposable = this.sd;
        sequentialDisposable.getClass();
        DisposableHelper.c(sequentialDisposable, disposable);
    }
}
