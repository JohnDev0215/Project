package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Collection;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class e1 extends io.reactivex.rxjava3.internal.observers.a {
    public final Collection n;
    public final io.reactivex.rxjava3.functions.o o;

    public e1(Observer observer, io.reactivex.rxjava3.functions.o oVar, Collection collection) {
        super(observer);
        this.o = oVar;
        this.n = collection;
    }

    @Override // io.reactivex.rxjava3.internal.observers.a, io.reactivex.rxjava3.operators.g
    public final void clear() {
        this.n.clear();
        super.clear();
    }

    @Override // io.reactivex.rxjava3.internal.observers.a, io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.l) {
            return;
        }
        this.l = true;
        this.n.clear();
        this.c.onComplete();
    }

    @Override // io.reactivex.rxjava3.internal.observers.a, io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.l) {
            RxJavaPlugins.b(th);
            return;
        }
        this.l = true;
        this.n.clear();
        this.c.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.l) {
            return;
        }
        int i = this.m;
        Observer observer = this.c;
        if (i != 0) {
            observer.onNext(null);
            return;
        }
        try {
            Object objApply = this.o.apply(obj);
            Objects.requireNonNull(objApply, "The keySelector returned a null key");
            if (this.n.add(objApply)) {
                observer.onNext(obj);
            }
        } catch (Throwable th) {
            a(th);
        }
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        Object objPoll;
        Object objApply;
        do {
            objPoll = this.k.poll();
            if (objPoll == null) {
                break;
            }
            objApply = this.o.apply(objPoll);
            Objects.requireNonNull(objApply, "The keySelector returned a null key");
        } while (!this.n.add(objApply));
        return objPoll;
    }
}
