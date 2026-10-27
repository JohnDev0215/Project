package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import java.util.Arrays;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class u extends a {
    public final /* synthetic */ int e;
    public final Object k;
    public final io.reactivex.rxjava3.functions.o l;
    public final Object m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ u(ObservableSource observableSource, ObservableSource observableSource2, io.reactivex.rxjava3.functions.o oVar, Object obj, int i) {
        super(observableSource);
        this.e = i;
        this.k = observableSource2;
        this.l = oVar;
        this.m = obj;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int length;
        int i = this.e;
        ObservableSource observableSource = this.c;
        io.reactivex.rxjava3.functions.o oVar = this.l;
        Object obj = this.m;
        Object obj2 = this.k;
        switch (i) {
            case 0:
                ObservableBufferBoundary$BufferBoundaryObserver observableBufferBoundary$BufferBoundaryObserver = new ObservableBufferBoundary$BufferBoundaryObserver(observer, (ObservableSource) obj2, oVar, (io.reactivex.rxjava3.functions.q) obj);
                observer.onSubscribe(observableBufferBoundary$BufferBoundaryObserver);
                observableSource.subscribe(observableBufferBoundary$BufferBoundaryObserver);
                break;
            case 1:
                ObservableSource observableSource2 = (ObservableSource) obj;
                if (observableSource2 == null) {
                    ObservableTimeout$TimeoutObserver observableTimeout$TimeoutObserver = new ObservableTimeout$TimeoutObserver(observer, oVar);
                    observer.onSubscribe(observableTimeout$TimeoutObserver);
                    ObservableSource observableSource3 = (ObservableSource) obj2;
                    if (observableSource3 != null) {
                        ObservableTimeout$TimeoutConsumer observableTimeout$TimeoutConsumer = new ObservableTimeout$TimeoutConsumer(0L, observableTimeout$TimeoutObserver);
                        SequentialDisposable sequentialDisposable = observableTimeout$TimeoutObserver.task;
                        sequentialDisposable.getClass();
                        if (DisposableHelper.c(sequentialDisposable, observableTimeout$TimeoutConsumer)) {
                            observableSource3.subscribe(observableTimeout$TimeoutConsumer);
                        }
                    }
                    observableSource.subscribe(observableTimeout$TimeoutObserver);
                } else {
                    ObservableTimeout$TimeoutFallbackObserver observableTimeout$TimeoutFallbackObserver = new ObservableTimeout$TimeoutFallbackObserver(observableSource2, observer, oVar);
                    observer.onSubscribe(observableTimeout$TimeoutFallbackObserver);
                    ObservableSource observableSource4 = (ObservableSource) obj2;
                    if (observableSource4 != null) {
                        ObservableTimeout$TimeoutConsumer observableTimeout$TimeoutConsumer2 = new ObservableTimeout$TimeoutConsumer(0L, observableTimeout$TimeoutFallbackObserver);
                        SequentialDisposable sequentialDisposable2 = observableTimeout$TimeoutFallbackObserver.task;
                        sequentialDisposable2.getClass();
                        if (DisposableHelper.c(sequentialDisposable2, observableTimeout$TimeoutConsumer2)) {
                            observableSource4.subscribe(observableTimeout$TimeoutConsumer2);
                        }
                    }
                    observableSource.subscribe(observableTimeout$TimeoutFallbackObserver);
                }
                break;
            case 2:
                ObservableSource<?>[] observableSourceArr = (ObservableSource[]) obj;
                if (observableSourceArr == null) {
                    ObservableSource<?>[] observableSourceArr2 = new ObservableSource[8];
                    try {
                        length = 0;
                        for (ObservableSource<?> observableSource5 : (Iterable) obj2) {
                            if (length == observableSourceArr2.length) {
                                observableSourceArr2 = (ObservableSource[]) Arrays.copyOf(observableSourceArr2, (length >> 1) + length);
                            }
                            int i2 = length + 1;
                            observableSourceArr2[length] = observableSource5;
                            length = i2;
                            break;
                        }
                        observableSourceArr = observableSourceArr2;
                    } catch (Throwable th) {
                        he1.R(th);
                        observer.onSubscribe(EmptyDisposable.c);
                        observer.onError(th);
                        return;
                    }
                } else {
                    length = observableSourceArr.length;
                }
                if (length == 0) {
                    new h3(observableSource, new j2(2, this), 4).subscribeActual(observer);
                } else {
                    ObservableWithLatestFromMany$WithLatestFromObserver observableWithLatestFromMany$WithLatestFromObserver = new ObservableWithLatestFromMany$WithLatestFromObserver(observer, oVar, length);
                    observer.onSubscribe(observableWithLatestFromMany$WithLatestFromObserver);
                    observableWithLatestFromMany$WithLatestFromObserver.subscribe(observableSourceArr, length);
                    observableSource.subscribe(observableWithLatestFromMany$WithLatestFromObserver);
                }
                break;
            default:
                observableSource.subscribe(new w2(observer, oVar, (io.reactivex.rxjava3.functions.o) obj2, (io.reactivex.rxjava3.functions.q) obj));
                break;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(ObservableSource observableSource, io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.o oVar2, io.reactivex.rxjava3.functions.q qVar) {
        super(observableSource);
        this.e = 3;
        this.l = oVar;
        this.k = oVar2;
        this.m = qVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(ObservableSource observableSource, ObservableSource[] observableSourceArr, io.reactivex.rxjava3.functions.o oVar) {
        super(observableSource);
        this.e = 2;
        this.m = observableSourceArr;
        this.k = null;
        this.l = oVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(ObservableSource observableSource, Iterable iterable, io.reactivex.rxjava3.functions.o oVar) {
        super(observableSource);
        this.e = 2;
        this.m = null;
        this.k = iterable;
        this.l = oVar;
    }
}
