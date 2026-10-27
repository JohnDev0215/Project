package io.reactivex.internal.observers;

import d.a.i;
import d.a.m.e;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;

/* JADX INFO: compiled from: DisposableLambdaObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> implements i<T>, b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final i<? super T> f4555b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final e<? super b> f4556c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final d.a.m.a f4557d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    b f4558e;

    public a(i<? super T> iVar, e<? super b> eVar, d.a.m.a aVar) {
        this.f4555b = iVar;
        this.f4556c = eVar;
        this.f4557d = aVar;
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        try {
            this.f4557d.run();
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            d.a.o.a.l(th);
        }
        this.f4558e.dispose();
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return this.f4558e.isDisposed();
    }

    @Override // d.a.i
    public void onComplete() {
        if (this.f4558e != DisposableHelper.DISPOSED) {
            this.f4555b.onComplete();
        }
    }

    @Override // d.a.i
    public void onError(Throwable th) {
        if (this.f4558e != DisposableHelper.DISPOSED) {
            this.f4555b.onError(th);
        } else {
            d.a.o.a.l(th);
        }
    }

    @Override // d.a.i
    public void onNext(T t) {
        this.f4555b.onNext(t);
    }

    @Override // d.a.i
    public void onSubscribe(b bVar) {
        try {
            this.f4556c.accept(bVar);
            if (DisposableHelper.validate(this.f4558e, bVar)) {
                this.f4558e = bVar;
                this.f4555b.onSubscribe(this);
            }
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            bVar.dispose();
            this.f4558e = DisposableHelper.DISPOSED;
            EmptyDisposable.error(th, this.f4555b);
        }
    }
}
