package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableSwitchMap$SwitchMapInnerObserver<T, R> extends AtomicReference<Disposable> implements Observer<R> {
    private static final long serialVersionUID = 3837284832786408377L;
    final int bufferSize;
    volatile boolean done;
    final long index;
    final ObservableSwitchMap$SwitchMapObserver<T, R> parent;
    volatile io.reactivex.rxjava3.operators.g queue;

    public ObservableSwitchMap$SwitchMapInnerObserver(ObservableSwitchMap$SwitchMapObserver observableSwitchMap$SwitchMapObserver, long j, int i) {
        this.parent = observableSwitchMap$SwitchMapObserver;
        this.index = j;
        this.bufferSize = i;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.index == this.parent.unique) {
            this.done = true;
            this.parent.a();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        ObservableSwitchMap$SwitchMapObserver<T, R> observableSwitchMap$SwitchMapObserver = this.parent;
        observableSwitchMap$SwitchMapObserver.getClass();
        if (this.index == observableSwitchMap$SwitchMapObserver.unique) {
            AtomicThrowable atomicThrowable = observableSwitchMap$SwitchMapObserver.errors;
            atomicThrowable.getClass();
            if (io.reactivex.rxjava3.internal.util.c.a(atomicThrowable, th)) {
                if (!observableSwitchMap$SwitchMapObserver.delayErrors) {
                    observableSwitchMap$SwitchMapObserver.upstream.dispose();
                    observableSwitchMap$SwitchMapObserver.done = true;
                }
                this.done = true;
                observableSwitchMap$SwitchMapObserver.a();
                return;
            }
        }
        RxJavaPlugins.b(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.index == this.parent.unique) {
            if (obj != null) {
                this.queue.offer(obj);
            }
            this.parent.a();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.f(this, disposable)) {
            if (disposable instanceof io.reactivex.rxjava3.operators.b) {
                io.reactivex.rxjava3.operators.b bVar = (io.reactivex.rxjava3.operators.b) disposable;
                int iB = bVar.b(7);
                if (iB == 1) {
                    this.queue = bVar;
                    this.done = true;
                    this.parent.a();
                    return;
                } else if (iB == 2) {
                    this.queue = bVar;
                    return;
                }
            }
            this.queue = new io.reactivex.rxjava3.operators.h(this.bufferSize);
        }
    }
}
