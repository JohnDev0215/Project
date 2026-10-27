package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class g implements Observer, Disposable {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final ObservableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver f73p = new ObservableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver(null);
    public final CompletableObserver c;
    public final o e;
    public final boolean k;
    public final AtomicThrowable l = new AtomicThrowable();
    public final AtomicReference m = new AtomicReference();
    public volatile boolean n;
    public Disposable o;

    public g(CompletableObserver completableObserver, o oVar, boolean z) {
        this.c = completableObserver;
        this.e = oVar;
        this.k = z;
    }

    public final void a() {
        AtomicReference atomicReference = this.m;
        ObservableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver observableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver = f73p;
        ObservableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver observableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver2 = (ObservableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver) atomicReference.getAndSet(observableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver);
        if (observableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver2 == null || observableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver2 == observableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver) {
            return;
        }
        DisposableHelper.a(observableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver2);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.o.dispose();
        a();
        this.l.b();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.m.get() == f73p;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.n = true;
        if (this.m.get() == null) {
            this.l.c(this.c);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        AtomicThrowable atomicThrowable = this.l;
        if (atomicThrowable.a(th)) {
            if (this.k) {
                onComplete();
            } else {
                a();
                atomicThrowable.c(this.c);
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        try {
            Object objApply = this.e.apply(obj);
            Objects.requireNonNull(objApply, "The mapper returned a null CompletableSource");
            CompletableSource completableSource = (CompletableSource) objApply;
            ObservableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver observableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver = new ObservableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver(this);
            while (true) {
                AtomicReference atomicReference = this.m;
                ObservableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver observableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver2 = (ObservableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver) atomicReference.get();
                if (observableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver2 == f73p) {
                    return;
                }
                while (!atomicReference.compareAndSet(observableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver2, observableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver)) {
                    if (atomicReference.get() != observableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver2) {
                        break;
                    }
                }
                if (observableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver2 != null) {
                    DisposableHelper.a(observableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver2);
                }
                completableSource.subscribe(observableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver);
                return;
            }
        } catch (Throwable th) {
            he1.R(th);
            this.o.dispose();
            onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.o, disposable)) {
            this.o = disposable;
            this.c.onSubscribe(this);
        }
    }
}
