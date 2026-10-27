package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;

/* JADX INFO: loaded from: classes7.dex */
public final class CompletableFromSingle<T> extends Completable {
    final SingleSource<T> single;

    public CompletableFromSingle(SingleSource<T> single) {
        this.single = single;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    protected void subscribeActual(final CompletableObserver observer) {
        this.single.subscribe(new CompletableFromSingleObserver(observer));
    }

    static final class CompletableFromSingleObserver<T> implements SingleObserver<T> {

        /* JADX INFO: renamed from: co, reason: collision with root package name */
        final CompletableObserver f779co;

        CompletableFromSingleObserver(CompletableObserver co2) {
            this.f779co = co2;
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onError(Throwable e) {
            this.f779co.onError(e);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSubscribe(Disposable d) {
            this.f779co.onSubscribe(d);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(T value) {
            this.f779co.onComplete();
        }
    }
}
