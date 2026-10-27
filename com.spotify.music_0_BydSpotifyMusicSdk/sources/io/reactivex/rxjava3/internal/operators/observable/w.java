package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.Collection;
import java.util.Objects;
import p.f60;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class w extends io.reactivex.rxjava3.internal.observers.k implements Disposable {
    public final io.reactivex.rxjava3.functions.q n;
    public final ObservableSource o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Disposable f80p;
    public v q;
    public Collection r;

    public w(io.reactivex.rxjava3.observers.d dVar, io.reactivex.rxjava3.functions.q qVar, ObservableSource observableSource) {
        super(dVar, new io.reactivex.rxjava3.internal.queue.a());
        this.n = qVar;
        this.o = observableSource;
    }

    @Override // io.reactivex.rxjava3.internal.observers.k
    public final void d(Observer observer, Object obj) {
        this.e.onNext((Collection) obj);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.l) {
            return;
        }
        this.l = true;
        this.q.dispose();
        this.f80p.dispose();
        if (f()) {
            this.k.clear();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.l;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        synchronized (this) {
            try {
                Collection collection = this.r;
                if (collection == null) {
                    return;
                }
                this.r = null;
                this.k.offer(collection);
                this.m = true;
                if (f()) {
                    f60.o(this.k, this.e, this, this);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        dispose();
        this.e.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        synchronized (this) {
            try {
                Collection collection = this.r;
                if (collection == null) {
                    return;
                }
                collection.add(obj);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.f80p, disposable)) {
            this.f80p = disposable;
            try {
                Object obj = this.n.get();
                Objects.requireNonNull(obj, "The buffer supplied is null");
                this.r = (Collection) obj;
                v vVar = new v(this);
                this.q = vVar;
                this.e.onSubscribe(this);
                if (this.l) {
                    return;
                }
                this.o.subscribe(vVar);
            } catch (Throwable th) {
                he1.R(th);
                this.l = true;
                disposable.dispose();
                EmptyDisposable.a(th, this.e);
            }
        }
    }
}
