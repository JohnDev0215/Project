package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.ArrayCompositeDisposable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import p.he1;
import p.u11;

/* JADX INFO: loaded from: classes.dex */
final class ObservableSequenceEqualSingle$EqualCoordinator<T> extends AtomicInteger implements Disposable {
    private static final long serialVersionUID = -6178010334400373240L;
    volatile boolean cancelled;
    final io.reactivex.rxjava3.functions.d comparer;
    final SingleObserver<? super Boolean> downstream;
    final ObservableSource<? extends T> first;
    final e4[] observers;
    final ArrayCompositeDisposable resources = new ArrayCompositeDisposable(2);
    final ObservableSource<? extends T> second;
    T v1;
    T v2;

    public ObservableSequenceEqualSingle$EqualCoordinator(SingleObserver singleObserver, int i, ObservableSource observableSource, ObservableSource observableSource2, io.reactivex.rxjava3.functions.d dVar) {
        this.downstream = singleObserver;
        this.first = observableSource;
        this.second = observableSource2;
        this.comparer = dVar;
        this.observers = new e4[]{new e4(this, 0, i), new e4(this, 1, i)};
    }

    public final void a() {
        Throwable th;
        Throwable th2;
        if (getAndIncrement() != 0) {
            return;
        }
        e4[] e4VarArr = this.observers;
        e4 e4Var = e4VarArr[0];
        io.reactivex.rxjava3.operators.h hVar = e4Var.e;
        e4 e4Var2 = e4VarArr[1];
        io.reactivex.rxjava3.operators.h hVar2 = e4Var2.e;
        int iAddAndGet = 1;
        while (!this.cancelled) {
            boolean z = e4Var.l;
            if (z && (th2 = e4Var.m) != null) {
                this.cancelled = true;
                hVar.clear();
                hVar2.clear();
                this.downstream.onError(th2);
                return;
            }
            boolean z2 = e4Var2.l;
            if (z2 && (th = e4Var2.m) != null) {
                this.cancelled = true;
                hVar.clear();
                hVar2.clear();
                this.downstream.onError(th);
                return;
            }
            if (this.v1 == null) {
                this.v1 = (T) hVar.poll();
            }
            boolean z3 = this.v1 == null;
            if (this.v2 == null) {
                this.v2 = (T) hVar2.poll();
            }
            T t = this.v2;
            boolean z4 = t == null;
            if (z && z2 && z3 && z4) {
                this.downstream.onSuccess(Boolean.TRUE);
                return;
            }
            if (z && z2 && z3 != z4) {
                this.cancelled = true;
                hVar.clear();
                hVar2.clear();
                this.downstream.onSuccess(Boolean.FALSE);
                return;
            }
            if (!z3 && !z4) {
                try {
                    io.reactivex.rxjava3.functions.d dVar = this.comparer;
                    T t2 = this.v1;
                    ((u11) dVar).getClass();
                    if (!Objects.equals(t2, t)) {
                        this.cancelled = true;
                        hVar.clear();
                        hVar2.clear();
                        this.downstream.onSuccess(Boolean.FALSE);
                        return;
                    }
                    this.v1 = null;
                    this.v2 = null;
                } catch (Throwable th3) {
                    he1.R(th3);
                    this.cancelled = true;
                    hVar.clear();
                    hVar2.clear();
                    this.downstream.onError(th3);
                    return;
                }
            }
            if (z3 || z4) {
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            }
        }
        hVar.clear();
        hVar2.clear();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        this.resources.dispose();
        if (getAndIncrement() == 0) {
            e4[] e4VarArr = this.observers;
            e4VarArr[0].e.clear();
            e4VarArr[1].e.clear();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.cancelled;
    }

    public void subscribe() {
        e4[] e4VarArr = this.observers;
        this.first.subscribe(e4VarArr[0]);
        this.second.subscribe(e4VarArr[1]);
    }
}
