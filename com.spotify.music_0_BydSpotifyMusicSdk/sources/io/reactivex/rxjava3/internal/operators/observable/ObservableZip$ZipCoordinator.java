package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableZip$ZipCoordinator<T, R> extends AtomicInteger implements Disposable {
    private static final long serialVersionUID = 2983708048395377667L;
    volatile boolean cancelled;
    final boolean delayError;
    final Observer<? super R> downstream;
    final t5[] observers;
    final T[] row;
    final io.reactivex.rxjava3.functions.o zipper;

    public ObservableZip$ZipCoordinator(Observer observer, io.reactivex.rxjava3.functions.o oVar, int i, boolean z) {
        this.downstream = observer;
        this.zipper = oVar;
        this.observers = new t5[i];
        this.row = (T[]) new Object[i];
        this.delayError = z;
    }

    public final void a() {
        for (t5 t5Var : this.observers) {
            t5Var.e.clear();
        }
        for (t5 t5Var2 : this.observers) {
            DisposableHelper.a(t5Var2.m);
        }
    }

    public final void b() {
        Throwable th;
        if (getAndIncrement() != 0) {
            return;
        }
        t5[] t5VarArr = this.observers;
        Observer<? super R> observer = this.downstream;
        T[] tArr = this.row;
        boolean z = this.delayError;
        int iAddAndGet = 1;
        while (true) {
            int i = 0;
            int i2 = 0;
            for (t5 t5Var : t5VarArr) {
                if (tArr[i2] == null) {
                    boolean z2 = t5Var.k;
                    Object objPoll = t5Var.e.poll();
                    boolean z3 = objPoll == null;
                    if (this.cancelled) {
                        a();
                        return;
                    }
                    if (z2) {
                        if (!z) {
                            Throwable th2 = t5Var.l;
                            if (th2 != null) {
                                this.cancelled = true;
                                a();
                                observer.onError(th2);
                                return;
                            } else if (z3) {
                                this.cancelled = true;
                                a();
                                observer.onComplete();
                                return;
                            }
                        } else if (z3) {
                            Throwable th3 = t5Var.l;
                            this.cancelled = true;
                            a();
                            if (th3 != null) {
                                observer.onError(th3);
                                return;
                            } else {
                                observer.onComplete();
                                return;
                            }
                        }
                    }
                    if (z3) {
                        i++;
                    } else {
                        tArr[i2] = objPoll;
                    }
                } else if (t5Var.k && !z && (th = t5Var.l) != null) {
                    this.cancelled = true;
                    a();
                    observer.onError(th);
                    return;
                }
                i2++;
            }
            if (i != 0) {
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            } else {
                try {
                    Object objApply = this.zipper.apply(tArr.clone());
                    Objects.requireNonNull(objApply, "The zipper returned a null value");
                    observer.onNext(objApply);
                    Arrays.fill(tArr, (Object) null);
                } catch (Throwable th4) {
                    he1.R(th4);
                    a();
                    observer.onError(th4);
                    return;
                }
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        for (t5 t5Var : this.observers) {
            DisposableHelper.a(t5Var.m);
        }
        if (getAndIncrement() == 0) {
            for (t5 t5Var2 : this.observers) {
                t5Var2.e.clear();
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.cancelled;
    }

    public void subscribe(ObservableSource<? extends T>[] observableSourceArr, int i) {
        t5[] t5VarArr = this.observers;
        int length = t5VarArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            t5VarArr[i2] = new t5(this, i);
        }
        lazySet(0);
        this.downstream.onSubscribe(this);
        for (int i3 = 0; i3 < length && !this.cancelled; i3++) {
            observableSourceArr[i3].subscribe(t5VarArr[i3]);
        }
    }
}
