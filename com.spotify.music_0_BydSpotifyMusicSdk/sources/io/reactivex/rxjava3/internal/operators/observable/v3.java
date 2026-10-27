package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class v3 extends io.reactivex.rxjava3.observables.a {
    public static final u3 m = new u3();
    public final ObservableSource c;
    public final AtomicReference e;
    public final p3 k;
    public final ObservableSource l;

    public v3(s3 s3Var, ObservableSource observableSource, AtomicReference atomicReference, p3 p3Var) {
        this.l = s3Var;
        this.c = observableSource;
        this.e = atomicReference;
        this.k = p3Var;
    }

    public static v3 c(ObservableSource observableSource, p3 p3Var) {
        AtomicReference atomicReference = new AtomicReference();
        return new v3(new s3(atomicReference, p3Var), observableSource, atomicReference, p3Var);
    }

    public static o d(io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.q qVar) {
        return new o(qVar, 2, oVar);
    }

    @Override // io.reactivex.rxjava3.observables.a
    public final void a(io.reactivex.rxjava3.functions.g gVar) {
        ObservableReplay$ReplayObserver observableReplay$ReplayObserver;
        loop0: while (true) {
            AtomicReference atomicReference = this.e;
            observableReplay$ReplayObserver = (ObservableReplay$ReplayObserver) atomicReference.get();
            if (observableReplay$ReplayObserver != null && !observableReplay$ReplayObserver.isDisposed()) {
                break;
            }
            ObservableReplay$ReplayObserver observableReplay$ReplayObserver2 = new ObservableReplay$ReplayObserver(this.k.call(), atomicReference);
            while (!atomicReference.compareAndSet(observableReplay$ReplayObserver, observableReplay$ReplayObserver2)) {
                if (atomicReference.get() != observableReplay$ReplayObserver) {
                    break;
                }
            }
            observableReplay$ReplayObserver = observableReplay$ReplayObserver2;
            break loop0;
        }
        boolean z = !observableReplay$ReplayObserver.shouldConnect.get() && observableReplay$ReplayObserver.shouldConnect.compareAndSet(false, true);
        try {
            gVar.accept(observableReplay$ReplayObserver);
            if (z) {
                this.c.subscribe(observableReplay$ReplayObserver);
            }
        } catch (Throwable th) {
            he1.R(th);
            if (z) {
                observableReplay$ReplayObserver.shouldConnect.compareAndSet(true, false);
            }
            he1.R(th);
            throw io.reactivex.rxjava3.internal.util.c.f(th);
        }
    }

    @Override // io.reactivex.rxjava3.observables.a
    public final void b() {
        AtomicReference atomicReference = this.e;
        ObservableReplay$ReplayObserver observableReplay$ReplayObserver = (ObservableReplay$ReplayObserver) atomicReference.get();
        if (observableReplay$ReplayObserver == null || !observableReplay$ReplayObserver.isDisposed()) {
            return;
        }
        while (!atomicReference.compareAndSet(observableReplay$ReplayObserver, null) && atomicReference.get() == observableReplay$ReplayObserver) {
        }
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        this.l.subscribe(observer);
    }
}
