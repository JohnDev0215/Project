package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class ObservableGroupBy$GroupByObserver<T, K, V> extends AtomicInteger implements Observer<T>, Disposable {
    public static final Object c = new Object();
    private static final long serialVersionUID = -3688291656102519502L;
    final int bufferSize;
    final boolean delayError;
    final Observer<? super z1> downstream;
    final io.reactivex.rxjava3.functions.o keySelector;
    Disposable upstream;
    final io.reactivex.rxjava3.functions.o valueSelector;
    final AtomicBoolean cancelled = new AtomicBoolean();
    final Map<Object, z1> groups = new ConcurrentHashMap();

    public ObservableGroupBy$GroupByObserver(Observer observer, io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.o oVar2, int i, boolean z) {
        this.downstream = observer;
        this.keySelector = oVar;
        this.valueSelector = oVar2;
        this.bufferSize = i;
        this.delayError = z;
        lazySet(1);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.cancelled.compareAndSet(false, true) && decrementAndGet() == 0) {
            this.upstream.dispose();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.cancelled.get();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        ArrayList arrayList = new ArrayList(this.groups.values());
        this.groups.clear();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ObservableGroupBy$State observableGroupBy$State = ((z1) it.next()).e;
            observableGroupBy$State.done = true;
            observableGroupBy$State.a();
        }
        this.downstream.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        ArrayList arrayList = new ArrayList(this.groups.values());
        this.groups.clear();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ObservableGroupBy$State observableGroupBy$State = ((z1) it.next()).e;
            observableGroupBy$State.error = th;
            observableGroupBy$State.done = true;
            observableGroupBy$State.a();
        }
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        boolean z;
        try {
            Object objApply = this.keySelector.apply(obj);
            Object obj2 = c;
            Object obj3 = objApply != null ? objApply : obj2;
            z1 z1Var = this.groups.get(obj3);
            if (z1Var != null) {
                z = false;
            } else {
                if (this.cancelled.get()) {
                    return;
                }
                z1Var = new z1(objApply, new ObservableGroupBy$State(this.bufferSize, this, objApply, this.delayError));
                this.groups.put(obj3, z1Var);
                getAndIncrement();
                z = true;
            }
            try {
                ObservableGroupBy$State observableGroupBy$State = z1Var.e;
                Object objApply2 = this.valueSelector.apply(obj);
                Objects.requireNonNull(objApply2, "The value supplied is null");
                observableGroupBy$State.queue.offer(objApply2);
                observableGroupBy$State.a();
                if (z) {
                    this.downstream.onNext(z1Var);
                    if (observableGroupBy$State.once.get() == 0 && observableGroupBy$State.once.compareAndSet(0, 2)) {
                        if (objApply == null) {
                            objApply = obj2;
                        }
                        this.groups.remove(objApply);
                        if (decrementAndGet() == 0) {
                            this.upstream.dispose();
                        }
                        ObservableGroupBy$State observableGroupBy$State2 = z1Var.e;
                        observableGroupBy$State2.done = true;
                        observableGroupBy$State2.a();
                    }
                }
            } catch (Throwable th) {
                he1.R(th);
                this.upstream.dispose();
                if (z) {
                    this.downstream.onNext(z1Var);
                }
                onError(th);
            }
        } catch (Throwable th2) {
            he1.R(th2);
            this.upstream.dispose();
            onError(th2);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }
}
