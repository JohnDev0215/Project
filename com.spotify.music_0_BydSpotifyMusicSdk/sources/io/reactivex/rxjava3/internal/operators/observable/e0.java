package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class e0 extends a implements Observer {
    public static final ObservableCache$CacheDisposable[] s = new ObservableCache$CacheDisposable[0];
    public static final ObservableCache$CacheDisposable[] t = new ObservableCache$CacheDisposable[0];
    public final AtomicBoolean e;
    public final int k;
    public final AtomicReference l;
    public volatile long m;
    public final d0 n;
    public d0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f76p;
    public Throwable q;
    public volatile boolean r;

    public e0(Observable observable, int i) {
        super(observable);
        this.k = i;
        this.e = new AtomicBoolean();
        d0 d0Var = new d0(i);
        this.n = d0Var;
        this.o = d0Var;
        this.l = new AtomicReference(s);
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final void a(ObservableCache$CacheDisposable observableCache$CacheDisposable) {
        if (observableCache$CacheDisposable.getAndIncrement() != 0) {
            return;
        }
        long j = observableCache$CacheDisposable.index;
        int i = observableCache$CacheDisposable.offset;
        d0 d0Var = observableCache$CacheDisposable.node;
        Observer<? super T> observer = observableCache$CacheDisposable.downstream;
        int i2 = this.k;
        int iAddAndGet = 1;
        while (!observableCache$CacheDisposable.disposed) {
            boolean z = this.r;
            boolean z2 = this.m == j;
            if (z && z2) {
                observableCache$CacheDisposable.node = null;
                Throwable th = this.q;
                if (th != null) {
                    observer.onError(th);
                    return;
                } else {
                    observer.onComplete();
                    return;
                }
            }
            if (z2) {
                observableCache$CacheDisposable.index = j;
                observableCache$CacheDisposable.offset = i;
                observableCache$CacheDisposable.node = d0Var;
                iAddAndGet = observableCache$CacheDisposable.addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            } else {
                if (i == i2) {
                    d0Var = d0Var.b;
                    i = 0;
                }
                observer.onNext((Object) d0Var.a[i]);
                i++;
                j++;
            }
        }
        observableCache$CacheDisposable.node = null;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.r = true;
        for (ObservableCache$CacheDisposable observableCache$CacheDisposable : (ObservableCache$CacheDisposable[]) this.l.getAndSet(t)) {
            a(observableCache$CacheDisposable);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.q = th;
        this.r = true;
        for (ObservableCache$CacheDisposable observableCache$CacheDisposable : (ObservableCache$CacheDisposable[]) this.l.getAndSet(t)) {
            a(observableCache$CacheDisposable);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        int i = this.f76p;
        if (i == this.k) {
            d0 d0Var = new d0(i);
            d0Var.a[0] = obj;
            this.f76p = 1;
            this.o.b = d0Var;
            this.o = d0Var;
        } else {
            this.o.a[i] = obj;
            this.f76p = i + 1;
        }
        this.m++;
        for (ObservableCache$CacheDisposable observableCache$CacheDisposable : (ObservableCache$CacheDisposable[]) this.l.get()) {
            a(observableCache$CacheDisposable);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        ObservableCache$CacheDisposable observableCache$CacheDisposable = new ObservableCache$CacheDisposable(observer, this);
        observer.onSubscribe(observableCache$CacheDisposable);
        loop0: while (true) {
            AtomicReference atomicReference = this.l;
            ObservableCache$CacheDisposable[] observableCache$CacheDisposableArr = (ObservableCache$CacheDisposable[]) atomicReference.get();
            if (observableCache$CacheDisposableArr != t) {
                int length = observableCache$CacheDisposableArr.length;
                ObservableCache$CacheDisposable[] observableCache$CacheDisposableArr2 = new ObservableCache$CacheDisposable[length + 1];
                System.arraycopy(observableCache$CacheDisposableArr, 0, observableCache$CacheDisposableArr2, 0, length);
                observableCache$CacheDisposableArr2[length] = observableCache$CacheDisposable;
                while (!atomicReference.compareAndSet(observableCache$CacheDisposableArr, observableCache$CacheDisposableArr2)) {
                    if (atomicReference.get() != observableCache$CacheDisposableArr) {
                        break;
                    }
                }
                break loop0;
            }
            break;
        }
        AtomicBoolean atomicBoolean = this.e;
        if (atomicBoolean.get() || !atomicBoolean.compareAndSet(false, true)) {
            a(observableCache$CacheDisposable);
        } else {
            this.c.subscribe(this);
        }
    }
}
