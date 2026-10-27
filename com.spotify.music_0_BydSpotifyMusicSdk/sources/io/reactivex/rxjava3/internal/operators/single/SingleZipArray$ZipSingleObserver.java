package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class SingleZipArray$ZipSingleObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T> {
    private static final long serialVersionUID = 3323743579927613702L;
    final int index;
    final SingleZipArray$ZipCoordinator<T, ?> parent;

    public SingleZipArray$ZipSingleObserver(SingleZipArray$ZipCoordinator singleZipArray$ZipCoordinator, int i) {
        this.parent = singleZipArray$ZipCoordinator;
        this.index = i;
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.parent.a(this.index, th);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this, disposable);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        SingleZipArray$ZipCoordinator<T, ?> singleZipArray$ZipCoordinator = this.parent;
        int i = this.index;
        Object[] objArr = singleZipArray$ZipCoordinator.values;
        if (objArr != null) {
            objArr[i] = obj;
        }
        if (singleZipArray$ZipCoordinator.decrementAndGet() == 0) {
            try {
                Object objApply = singleZipArray$ZipCoordinator.zipper.apply(objArr);
                Objects.requireNonNull(objApply, "The zipper returned a null value");
                singleZipArray$ZipCoordinator.values = null;
                singleZipArray$ZipCoordinator.downstream.onSuccess(objApply);
            } catch (Throwable th) {
                he1.R(th);
                singleZipArray$ZipCoordinator.values = null;
                singleZipArray$ZipCoordinator.downstream.onError(th);
            }
        }
    }
}
