package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p.f60;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class y extends io.reactivex.rxjava3.internal.observers.k implements Runnable, Disposable {
    public final io.reactivex.rxjava3.functions.q n;
    public final long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final TimeUnit f82p;
    public final Scheduler q;
    public Disposable r;
    public Collection s;
    public final AtomicReference t;

    public y(io.reactivex.rxjava3.observers.d dVar, io.reactivex.rxjava3.functions.q qVar, long j, TimeUnit timeUnit, Scheduler scheduler) {
        super(dVar, new io.reactivex.rxjava3.internal.queue.a());
        this.t = new AtomicReference();
        this.n = qVar;
        this.o = j;
        this.f82p = timeUnit;
        this.q = scheduler;
    }

    @Override // io.reactivex.rxjava3.internal.observers.k
    public final void d(Observer observer, Object obj) {
        this.e.onNext((Collection) obj);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this.t);
        this.r.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.t.get() == DisposableHelper.c;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        Collection collection;
        synchronized (this) {
            collection = this.s;
            this.s = null;
        }
        if (collection != null) {
            this.k.offer(collection);
            this.m = true;
            if (f()) {
                f60.o(this.k, this.e, null, this);
            }
        }
        DisposableHelper.a(this.t);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        synchronized (this) {
            this.s = null;
        }
        this.e.onError(th);
        DisposableHelper.a(this.t);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        synchronized (this) {
            try {
                Collection collection = this.s;
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
        Observer observer = this.e;
        if (DisposableHelper.g(this.r, disposable)) {
            this.r = disposable;
            try {
                Object obj = this.n.get();
                Objects.requireNonNull(obj, "The buffer supplied is null");
                this.s = (Collection) obj;
                observer.onSubscribe(this);
                AtomicReference atomicReference = this.t;
                if (DisposableHelper.b((Disposable) atomicReference.get())) {
                    return;
                }
                Scheduler scheduler = this.q;
                long j = this.o;
                DisposableHelper.d(atomicReference, scheduler.e(this, j, j, this.f82p));
            } catch (Throwable th) {
                he1.R(th);
                dispose();
                EmptyDisposable.a(th, observer);
            }
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        Collection collection;
        try {
            Object obj = this.n.get();
            Objects.requireNonNull(obj, "The bufferSupplier returned a null buffer");
            Collection collection2 = (Collection) obj;
            synchronized (this) {
                try {
                    collection = this.s;
                    if (collection != null) {
                        this.s = collection2;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (collection == null) {
                DisposableHelper.a(this.t);
            } else {
                g(collection, this);
            }
        } catch (Throwable th2) {
            he1.R(th2);
            this.e.onError(th2);
            dispose();
        }
    }
}
