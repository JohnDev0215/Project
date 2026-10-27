package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import p.f60;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class b0 extends io.reactivex.rxjava3.internal.observers.k implements Runnable, Disposable {
    public final io.reactivex.rxjava3.functions.q n;
    public final long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final long f74p;
    public final TimeUnit q;
    public final Scheduler.Worker r;
    public final LinkedList s;
    public Disposable t;

    public b0(io.reactivex.rxjava3.observers.d dVar, io.reactivex.rxjava3.functions.q qVar, long j, long j2, TimeUnit timeUnit, Scheduler.Worker worker) {
        super(dVar, new io.reactivex.rxjava3.internal.queue.a());
        this.n = qVar;
        this.o = j;
        this.f74p = j2;
        this.q = timeUnit;
        this.r = worker;
        this.s = new LinkedList();
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
        synchronized (this) {
            this.s.clear();
        }
        this.t.dispose();
        this.r.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.l;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList(this.s);
            this.s.clear();
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.k.offer((Collection) it.next());
        }
        this.m = true;
        if (f()) {
            f60.o(this.k, this.e, this.r, this);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.m = true;
        synchronized (this) {
            this.s.clear();
        }
        this.e.onError(th);
        this.r.dispose();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        synchronized (this) {
            try {
                Iterator it = this.s.iterator();
                while (it.hasNext()) {
                    ((Collection) it.next()).add(obj);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        Scheduler.Worker worker = this.r;
        Observer observer = this.e;
        if (DisposableHelper.g(this.t, disposable)) {
            this.t = disposable;
            try {
                Object obj = this.n.get();
                Objects.requireNonNull(obj, "The buffer supplied is null");
                Collection collection = (Collection) obj;
                this.s.add(collection);
                observer.onSubscribe(this);
                Scheduler.Worker worker2 = this.r;
                long j = this.f74p;
                worker2.c(this, j, j, this.q);
                worker.b(new a0(this, collection), this.o, this.q);
            } catch (Throwable th) {
                he1.R(th);
                disposable.dispose();
                EmptyDisposable.a(th, observer);
                worker.dispose();
            }
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.l) {
            return;
        }
        try {
            Object obj = this.n.get();
            Objects.requireNonNull(obj, "The bufferSupplier returned a null buffer");
            Collection collection = (Collection) obj;
            synchronized (this) {
                try {
                    if (this.l) {
                        return;
                    }
                    this.s.add(collection);
                    this.r.b(new z(this, collection), this.o, this.q);
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (Throwable th2) {
            he1.R(th2);
            this.e.onError(th2);
            dispose();
        }
    }
}
