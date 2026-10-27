package io.reactivex.internal.operators.observable;

import d.a.h;
import d.a.i;
import d.a.j;
import d.a.m.b;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class ObservableSubscribeOn<T> extends d.a.p.e.b.a<T, T> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final j f5910d;

    public static final class SubscribeOnObserver<T> extends AtomicReference<b> implements i<T>, b {
        private static final long serialVersionUID = 8094547886072529208L;
        public final i<? super T> actual;
        public final AtomicReference<b> s = new AtomicReference<>();

        public SubscribeOnObserver(i<? super T> iVar) {
            this.actual = iVar;
        }

        @Override // d.a.m.b
        public void dispose() {
            DisposableHelper.dispose(this.s);
            DisposableHelper.dispose(this);
        }

        @Override // d.a.m.b
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
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.s, bVar);
        }

        public void setDisposable(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }
    }

    public class a implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ SubscribeOnObserver f5911c;

        public a(SubscribeOnObserver subscribeOnObserver) {
            this.f5911c = subscribeOnObserver;
        }

        @Override // java.lang.Runnable
        public void run() {
            ObservableSubscribeOn.this.f3190c.a(this.f5911c);
        }
    }

    public ObservableSubscribeOn(h<T> hVar, j jVar) {
        super(hVar);
        this.f5910d = jVar;
    }

    @Override // d.a.g
    public void t(i<? super T> iVar) {
        SubscribeOnObserver subscribeOnObserver = new SubscribeOnObserver(iVar);
        iVar.onSubscribe(subscribeOnObserver);
        subscribeOnObserver.setDisposable(this.f5910d.b(new a(subscribeOnObserver)));
    }
}
