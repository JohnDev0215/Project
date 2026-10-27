package io.reactivex.internal.operators.observable;

import e.a.h;
import e.a.i;
import e.a.j;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class ObservableSubscribeOn<T> extends io.reactivex.internal.operators.observable.a<T, T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final j f5006c;

    static final class SubscribeOnObserver<T> extends AtomicReference<io.reactivex.disposables.b> implements i<T>, io.reactivex.disposables.b {
        private static final long serialVersionUID = 8094547886072529208L;
        final i<? super T> downstream;
        final AtomicReference<io.reactivex.disposables.b> upstream = new AtomicReference<>();

        SubscribeOnObserver(i<? super T> iVar) {
            this.downstream = iVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // e.a.i
        public void onComplete() {
            this.downstream.onComplete();
        }

        @Override // e.a.i
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // e.a.i
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // e.a.i
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            DisposableHelper.setOnce(this.upstream, bVar);
        }

        void setDisposable(io.reactivex.disposables.b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }
    }

    final class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final SubscribeOnObserver<T> f5007b;

        a(SubscribeOnObserver<T> subscribeOnObserver) {
            this.f5007b = subscribeOnObserver;
        }

        @Override // java.lang.Runnable
        public void run() {
            ObservableSubscribeOn.this.f5009b.a(this.f5007b);
        }
    }

    public ObservableSubscribeOn(h<T> hVar, j jVar) {
        super(hVar);
        this.f5006c = jVar;
    }

    @Override // e.a.e
    public void m(i<? super T> iVar) {
        SubscribeOnObserver subscribeOnObserver = new SubscribeOnObserver(iVar);
        iVar.onSubscribe(subscribeOnObserver);
        subscribeOnObserver.setDisposable(this.f5006c.b(new a(subscribeOnObserver)));
    }
}
