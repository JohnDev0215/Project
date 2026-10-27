package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class SingleFlatMapMaybe$FlatMapSingleObserver<T, R> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable {
    private static final long serialVersionUID = -5843758257109742742L;
    final MaybeObserver<? super R> downstream;
    final io.reactivex.rxjava3.functions.o mapper;

    public SingleFlatMapMaybe$FlatMapSingleObserver(MaybeObserver maybeObserver, io.reactivex.rxjava3.functions.o oVar) {
        this.downstream = maybeObserver;
        this.mapper = oVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(get());
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.f(this, disposable)) {
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        try {
            Object objApply = this.mapper.apply(obj);
            Objects.requireNonNull(objApply, "The mapper returned a null MaybeSource");
            MaybeSource maybeSource = (MaybeSource) objApply;
            if (isDisposed()) {
                return;
            }
            maybeSource.subscribe(new z(this, this.downstream));
        } catch (Throwable th) {
            he1.R(th);
            onError(th);
        }
    }
}
