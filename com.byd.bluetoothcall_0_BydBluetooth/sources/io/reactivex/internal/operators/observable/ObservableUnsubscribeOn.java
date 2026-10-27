package io.reactivex.internal.operators.observable;

import d.a.h;
import d.a.i;
import d.a.j;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class ObservableUnsubscribeOn<T> extends a<T, T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final j f4290c;

    static final class UnsubscribeObserver<T> extends AtomicBoolean implements i<T>, io.reactivex.disposables.b {
        private static final long serialVersionUID = 1015244841293359600L;
        final i<? super T> downstream;
        final j scheduler;
        io.reactivex.disposables.b upstream;

        final class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                UnsubscribeObserver.this.upstream.dispose();
            }
        }

        UnsubscribeObserver(i<? super T> iVar, j jVar) {
            this.downstream = iVar;
            this.scheduler = jVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (compareAndSet(false, true)) {
                this.scheduler.b(new a());
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get();
        }

        @Override // d.a.i
        public void onComplete() {
            if (get()) {
                return;
            }
            this.downstream.onComplete();
        }

        @Override // d.a.i
        public void onError(Throwable th) {
            if (get()) {
                d.a.p.a.l(th);
            } else {
                this.downstream.onError(th);
            }
        }

        @Override // d.a.i
        public void onNext(T t) {
            if (get()) {
                return;
            }
            this.downstream.onNext(t);
        }

        @Override // d.a.i
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }
    }

    public ObservableUnsubscribeOn(h<T> hVar, j jVar) {
        super(hVar);
        this.f4290c = jVar;
    }

    @Override // d.a.e
    public void o(i<? super T> iVar) {
        this.f4292b.a(new UnsubscribeObserver(iVar, this.f4290c));
    }
}
