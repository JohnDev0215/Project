package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class SingleFlatMapNotification$FlatMapSingleObserver<T, R> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable {
    private static final long serialVersionUID = 4375739915521278546L;
    final SingleObserver<? super R> downstream;
    final io.reactivex.rxjava3.functions.o onErrorMapper;
    final io.reactivex.rxjava3.functions.o onSuccessMapper;
    Disposable upstream;

    public SingleFlatMapNotification$FlatMapSingleObserver(SingleObserver singleObserver, io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.o oVar2) {
        this.downstream = singleObserver;
        this.onSuccessMapper = oVar;
        this.onErrorMapper = oVar2;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this);
        this.upstream.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(get());
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        try {
            Object objApply = this.onErrorMapper.apply(th);
            Objects.requireNonNull(objApply, "The onErrorMapper returned a null SingleSource");
            SingleSource singleSource = (SingleSource) objApply;
            if (isDisposed()) {
                return;
            }
            singleSource.subscribe(new b0(this));
        } catch (Throwable th2) {
            he1.R(th2);
            this.downstream.onError(new CompositeException(th, th2));
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        try {
            Object objApply = this.onSuccessMapper.apply(obj);
            Objects.requireNonNull(objApply, "The onSuccessMapper returned a null SingleSource");
            SingleSource singleSource = (SingleSource) objApply;
            if (isDisposed()) {
                return;
            }
            singleSource.subscribe(new b0(this));
        } catch (Throwable th) {
            he1.R(th);
            this.downstream.onError(th);
        }
    }
}
