package io.reactivex.internal.operators.observable;

import d.a.h;
import d.a.i;
import d.a.j;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class ObservableSubscribeOn<T> extends io.reactivex.internal.operators.observable.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final j f4563b;

    static final class SubscribeOnObserver<T> extends AtomicReference<io.reactivex.disposables.b> implements i<T>, io.reactivex.disposables.b {
        private static final long serialVersionUID = 8094547886072529208L;
        final i<? super T> actual;
        final AtomicReference<io.reactivex.disposables.b> s = new AtomicReference<>();

        SubscribeOnObserver(i<? super T> iVar) {
            this.actual = iVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this.s);
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // d.a.i
        public void onComplete() {
            this.actual.onComplete();
        }

        @Override // d.a.i
        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        @Override // d.a.i
        public void onNext(T t) {
            this.actual.onNext(t);
        }

        @Override // d.a.i
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            DisposableHelper.setOnce(this.s, bVar);
        }

        void setDisposable(io.reactivex.disposables.b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }
    }

    final class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final SubscribeOnObserver<T> f4564b;

        a(SubscribeOnObserver<T> subscribeOnObserver) {
            this.f4564b = subscribeOnObserver;
        }

        @Override // java.lang.Runnable
        public void run() {
            ObservableSubscribeOn.this.f4566a.a(this.f4564b);
        }
    }

    public ObservableSubscribeOn(h<T> hVar, j jVar) {
        super(hVar);
        this.f4563b = jVar;
    }

    @Override // d.a.e
    public void j(i<? super T> iVar) {
        SubscribeOnObserver subscribeOnObserver = new SubscribeOnObserver(iVar);
        iVar.onSubscribe(subscribeOnObserver);
        subscribeOnObserver.setDisposable(this.f4563b.b(new a(subscribeOnObserver)));
    }
}
