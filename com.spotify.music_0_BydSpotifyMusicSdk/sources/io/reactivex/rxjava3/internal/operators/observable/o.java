package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class o extends Observable {
    public final /* synthetic */ int c;
    public final Object e;
    public final Object k;

    public /* synthetic */ o(Object obj, int i, Object obj2) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
    }

    public final boolean a() {
        Object obj = this.k;
        return !((AtomicBoolean) obj).get() && ((AtomicBoolean) obj).compareAndSet(false, true);
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int length;
        Disposable disposable = EmptyDisposable.c;
        int i = this.c;
        Object obj = this.e;
        Object obj2 = this.k;
        switch (i) {
            case 0:
                ObservableSource<Object>[] observableSourceArr = (ObservableSource[]) obj;
                if (observableSourceArr == null) {
                    observableSourceArr = new ObservableSource[8];
                    try {
                        length = 0;
                        for (ObservableSource<Object> observableSource : (Iterable) obj2) {
                            if (observableSource == null) {
                                Throwable nullPointerException = new NullPointerException("One of the sources is null");
                                observer.onSubscribe(disposable);
                                observer.onError(nullPointerException);
                            } else {
                                if (length == observableSourceArr.length) {
                                    ObservableSource<Object>[] observableSourceArr2 = new ObservableSource[(length >> 2) + length];
                                    System.arraycopy(observableSourceArr, 0, observableSourceArr2, 0, length);
                                    observableSourceArr = observableSourceArr2;
                                }
                                int i2 = length + 1;
                                observableSourceArr[length] = observableSource;
                                length = i2;
                            }
                        }
                    } catch (Throwable th) {
                        he1.R(th);
                        observer.onSubscribe(disposable);
                        observer.onError(th);
                        return;
                    }
                } else {
                    length = observableSourceArr.length;
                }
                if (length == 0) {
                    observer.onSubscribe(disposable);
                    observer.onComplete();
                } else if (length == 1) {
                    observableSourceArr[0].subscribe(observer);
                } else {
                    new n(observer, length).subscribe(observableSourceArr);
                }
                break;
            case 1:
                SequentialDisposable sequentialDisposable = new SequentialDisposable();
                observer.onSubscribe(sequentialDisposable);
                ((ObservableSource) obj2).subscribe(new b1(this, sequentialDisposable, observer));
                break;
            case 2:
                try {
                    Object obj3 = ((io.reactivex.rxjava3.functions.q) obj).get();
                    Objects.requireNonNull(obj3, "The connectableFactory returned a null ConnectableObservable");
                    io.reactivex.rxjava3.observables.a aVar = (io.reactivex.rxjava3.observables.a) obj3;
                    Object objApply = ((io.reactivex.rxjava3.functions.o) obj2).apply(aVar);
                    Objects.requireNonNull(objApply, "The selector returned a null ObservableSource");
                    ObservableSource observableSource2 = (ObservableSource) objApply;
                    ObserverResourceWrapper observerResourceWrapper = new ObserverResourceWrapper(observer);
                    observableSource2.subscribe(observerResourceWrapper);
                    aVar.a(new m2(observerResourceWrapper, 2));
                } catch (Throwable th2) {
                    he1.R(th2);
                    observer.onSubscribe(disposable);
                    observer.onError(th2);
                    return;
                }
                break;
            case 3:
                try {
                    Object objApply2 = ((io.reactivex.rxjava3.functions.o) obj2).apply(obj);
                    Objects.requireNonNull(objApply2, "The mapper returned a null ObservableSource");
                    ObservableSource observableSource3 = (ObservableSource) objApply2;
                    if (!(observableSource3 instanceof io.reactivex.rxjava3.functions.q)) {
                        observableSource3.subscribe(observer);
                    } else {
                        try {
                            Object obj4 = ((io.reactivex.rxjava3.functions.q) observableSource3).get();
                            if (obj4 == null) {
                                observer.onSubscribe(disposable);
                                observer.onComplete();
                            } else {
                                ObservableScalarXMap$ScalarDisposable observableScalarXMap$ScalarDisposable = new ObservableScalarXMap$ScalarDisposable(observer, obj4);
                                observer.onSubscribe(observableScalarXMap$ScalarDisposable);
                                observableScalarXMap$ScalarDisposable.run();
                            }
                        } catch (Throwable th3) {
                            he1.R(th3);
                            observer.onSubscribe(disposable);
                            observer.onError(th3);
                            return;
                        }
                    }
                } catch (Throwable th4) {
                    he1.R(th4);
                    observer.onSubscribe(disposable);
                    observer.onError(th4);
                    return;
                }
                break;
            default:
                ((io.reactivex.rxjava3.subjects.e) obj).subscribe(observer);
                ((AtomicBoolean) obj2).set(true);
                break;
        }
    }

    public o(io.reactivex.rxjava3.subjects.f fVar) {
        this.c = 4;
        this.e = fVar;
        this.k = new AtomicBoolean();
    }
}
