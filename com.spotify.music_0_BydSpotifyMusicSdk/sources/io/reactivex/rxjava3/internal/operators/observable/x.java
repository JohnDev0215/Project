package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import p.f60;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class x extends io.reactivex.rxjava3.internal.observers.k implements Runnable, Disposable {
    public final io.reactivex.rxjava3.functions.q n;
    public final long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final TimeUnit f81p;
    public final int q;
    public final boolean r;
    public final Scheduler.Worker s;
    public Collection t;
    public Disposable u;
    public Disposable v;
    public long w;
    public long x;

    public x(io.reactivex.rxjava3.observers.d dVar, io.reactivex.rxjava3.functions.q qVar, long j, TimeUnit timeUnit, int i, boolean z, Scheduler.Worker worker) {
        super(dVar, new io.reactivex.rxjava3.internal.queue.a());
        this.n = qVar;
        this.o = j;
        this.f81p = timeUnit;
        this.q = i;
        this.r = z;
        this.s = worker;
    }

    @Override // io.reactivex.rxjava3.internal.observers.k
    public final void d(Observer observer, Object obj) {
        observer.onNext((Collection) obj);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.l) {
            return;
        }
        this.l = true;
        this.v.dispose();
        this.s.dispose();
        synchronized (this) {
            this.t = null;
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.l;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        Collection collection;
        this.s.dispose();
        synchronized (this) {
            collection = this.t;
            this.t = null;
        }
        if (collection != null) {
            this.k.offer(collection);
            this.m = true;
            if (f()) {
                f60.o(this.k, this.e, this, this);
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        synchronized (this) {
            this.t = null;
        }
        this.e.onError(th);
        this.s.dispose();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        synchronized (this) {
            try {
                Collection collection = this.t;
                if (collection == null) {
                    return;
                }
                collection.add(obj);
                if (collection.size() < this.q) {
                    return;
                }
                this.t = null;
                this.w++;
                if (this.r) {
                    this.u.dispose();
                }
                h(collection, this);
                try {
                    Object obj2 = this.n.get();
                    Objects.requireNonNull(obj2, "The buffer supplied is null");
                    Collection collection2 = (Collection) obj2;
                    synchronized (this) {
                        this.t = collection2;
                        this.x++;
                    }
                    if (this.r) {
                        Scheduler.Worker worker = this.s;
                        long j = this.o;
                        this.u = worker.c(this, j, j, this.f81p);
                    }
                } catch (Throwable th) {
                    he1.R(th);
                    this.e.onError(th);
                    dispose();
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        Observer observer = this.e;
        if (DisposableHelper.g(this.v, disposable)) {
            this.v = disposable;
            try {
                Object obj = this.n.get();
                Objects.requireNonNull(obj, "The buffer supplied is null");
                this.t = (Collection) obj;
                observer.onSubscribe(this);
                Scheduler.Worker worker = this.s;
                long j = this.o;
                this.u = worker.c(this, j, j, this.f81p);
            } catch (Throwable th) {
                he1.R(th);
                disposable.dispose();
                EmptyDisposable.a(th, observer);
                this.s.dispose();
            }
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Object obj = this.n.get();
            Objects.requireNonNull(obj, "The bufferSupplier returned a null buffer");
            Collection collection = (Collection) obj;
            synchronized (this) {
                Collection collection2 = this.t;
                if (collection2 != null && this.w == this.x) {
                    this.t = collection;
                    h(collection2, this);
                }
            }
        } catch (Throwable th) {
            he1.R(th);
            dispose();
            this.e.onError(th);
        }
    }
}
