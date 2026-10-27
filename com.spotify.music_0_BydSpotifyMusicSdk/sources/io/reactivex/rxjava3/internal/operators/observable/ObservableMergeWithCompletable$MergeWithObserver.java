package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p.ux2;

/* JADX INFO: loaded from: classes.dex */
final class ObservableMergeWithCompletable$MergeWithObserver<T> extends AtomicInteger implements Observer<T>, Disposable {
    private static final long serialVersionUID = -4592979584110982903L;
    final Observer<? super T> downstream;
    volatile boolean mainDone;
    volatile boolean otherDone;
    final AtomicReference<Disposable> mainDisposable = new AtomicReference<>();
    final OtherObserver otherObserver = new OtherObserver(this);
    final AtomicThrowable errors = new AtomicThrowable();

    public static final class OtherObserver extends AtomicReference<Disposable> implements CompletableObserver {
        private static final long serialVersionUID = -2935427570954647017L;
        final ObservableMergeWithCompletable$MergeWithObserver<?> parent;

        public OtherObserver(ObservableMergeWithCompletable$MergeWithObserver observableMergeWithCompletable$MergeWithObserver) {
            this.parent = observableMergeWithCompletable$MergeWithObserver;
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public final void onComplete() {
            ObservableMergeWithCompletable$MergeWithObserver<?> observableMergeWithCompletable$MergeWithObserver = this.parent;
            observableMergeWithCompletable$MergeWithObserver.otherDone = true;
            if (observableMergeWithCompletable$MergeWithObserver.mainDone) {
                ux2.t(observableMergeWithCompletable$MergeWithObserver.downstream, observableMergeWithCompletable$MergeWithObserver, observableMergeWithCompletable$MergeWithObserver.errors);
            }
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public final void onError(Throwable th) {
            ObservableMergeWithCompletable$MergeWithObserver<?> observableMergeWithCompletable$MergeWithObserver = this.parent;
            DisposableHelper.a(observableMergeWithCompletable$MergeWithObserver.mainDisposable);
            ux2.u(observableMergeWithCompletable$MergeWithObserver.downstream, th, observableMergeWithCompletable$MergeWithObserver, observableMergeWithCompletable$MergeWithObserver.errors);
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public final void onSubscribe(Disposable disposable) {
            DisposableHelper.f(this, disposable);
        }
    }

    public ObservableMergeWithCompletable$MergeWithObserver(Observer observer) {
        this.downstream = observer;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this.mainDisposable);
        DisposableHelper.a(this.otherObserver);
        this.errors.b();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(this.mainDisposable.get());
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.mainDone = true;
        if (this.otherDone) {
            ux2.t(this.downstream, this, this.errors);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        DisposableHelper.a(this.otherObserver);
        ux2.u(this.downstream, th, this, this.errors);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        ux2.v(this.downstream, obj, this, this.errors);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this.mainDisposable, disposable);
    }
}
