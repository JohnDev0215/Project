package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
final class SingleZipArray$ZipCoordinator<T, R> extends AtomicInteger implements Disposable {
    private static final long serialVersionUID = -5556924161382950569L;
    final SingleObserver<? super R> downstream;
    final SingleZipArray$ZipSingleObserver<T>[] observers;
    Object[] values;
    final io.reactivex.rxjava3.functions.o zipper;

    public SingleZipArray$ZipCoordinator(SingleObserver singleObserver, int i, io.reactivex.rxjava3.functions.o oVar) {
        super(i);
        this.downstream = singleObserver;
        this.zipper = oVar;
        SingleZipArray$ZipSingleObserver<T>[] singleZipArray$ZipSingleObserverArr = new SingleZipArray$ZipSingleObserver[i];
        for (int i2 = 0; i2 < i; i2++) {
            singleZipArray$ZipSingleObserverArr[i2] = new SingleZipArray$ZipSingleObserver<>(this, i2);
        }
        this.observers = singleZipArray$ZipSingleObserverArr;
        this.values = new Object[i];
    }

    public final void a(int i, Throwable th) {
        if (getAndSet(0) <= 0) {
            RxJavaPlugins.b(th);
            return;
        }
        SingleZipArray$ZipSingleObserver<T>[] singleZipArray$ZipSingleObserverArr = this.observers;
        int length = singleZipArray$ZipSingleObserverArr.length;
        for (int i2 = 0; i2 < i; i2++) {
            SingleZipArray$ZipSingleObserver<T> singleZipArray$ZipSingleObserver = singleZipArray$ZipSingleObserverArr[i2];
            singleZipArray$ZipSingleObserver.getClass();
            DisposableHelper.a(singleZipArray$ZipSingleObserver);
        }
        while (true) {
            i++;
            if (i >= length) {
                this.values = null;
                this.downstream.onError(th);
                return;
            } else {
                SingleZipArray$ZipSingleObserver<T> singleZipArray$ZipSingleObserver2 = singleZipArray$ZipSingleObserverArr[i];
                singleZipArray$ZipSingleObserver2.getClass();
                DisposableHelper.a(singleZipArray$ZipSingleObserver2);
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (getAndSet(0) > 0) {
            for (SingleZipArray$ZipSingleObserver<T> singleZipArray$ZipSingleObserver : this.observers) {
                singleZipArray$ZipSingleObserver.getClass();
                DisposableHelper.a(singleZipArray$ZipSingleObserver);
            }
            this.values = null;
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return get() <= 0;
    }
}
