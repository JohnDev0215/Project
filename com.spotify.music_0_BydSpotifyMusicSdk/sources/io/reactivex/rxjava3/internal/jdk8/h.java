package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class h implements io.reactivex.rxjava3.operators.b {
    public final Observer c;
    public Iterator e;
    public AutoCloseable k;
    public volatile boolean l;
    public boolean m;
    public boolean n;

    public h(Observer observer, Iterator it, AutoCloseable autoCloseable) {
        this.c = observer;
        this.e = it;
        this.k = autoCloseable;
    }

    public final void a() {
        if (this.n) {
            return;
        }
        Iterator it = this.e;
        Observer observer = this.c;
        while (!this.l) {
            try {
                Object next = it.next();
                Objects.requireNonNull(next, "The Stream's Iterator.next returned a null value");
                if (!this.l) {
                    observer.onNext(next);
                    if (!this.l) {
                        try {
                            if (!it.hasNext()) {
                                observer.onComplete();
                                this.l = true;
                            }
                        } catch (Throwable th) {
                            he1.R(th);
                            observer.onError(th);
                            this.l = true;
                        }
                    }
                }
            } catch (Throwable th2) {
                he1.R(th2);
                observer.onError(th2);
                this.l = true;
            }
        }
        clear();
    }

    @Override // io.reactivex.rxjava3.operators.c
    public final int b(int i) {
        if ((i & 1) == 0) {
            return 0;
        }
        this.n = true;
        return 1;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
        this.e = null;
        AutoCloseable autoCloseable = this.k;
        this.k = null;
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Throwable th) {
                he1.R(th);
                RxJavaPlugins.b(th);
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.l = true;
        a();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.l;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        Iterator it = this.e;
        if (it == null) {
            return true;
        }
        if (!this.m || it.hasNext()) {
            return false;
        }
        clear();
        return true;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean offer(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        Iterator it = this.e;
        if (it == null) {
            return null;
        }
        if (!this.m) {
            this.m = true;
        } else if (!it.hasNext()) {
            clear();
            return null;
        }
        Object next = this.e.next();
        Objects.requireNonNull(next, "The Stream's Iterator.next() returned a null value");
        return next;
    }
}
