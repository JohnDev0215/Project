package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Collection;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class m0 extends a {
    public final /* synthetic */ int e;
    public final Object k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m0(ObservableSource observableSource, Object obj, int i) {
        super(observableSource);
        this.e = i;
        this.k = obj;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int i = this.e;
        ObservableSource observableSource = this.c;
        Object obj = this.k;
        switch (i) {
            case 0:
                observableSource.subscribe(new ObservableConcatWithCompletable$ConcatWithObserver(observer, (CompletableSource) obj));
                return;
            case 1:
                observableSource.subscribe(new ObservableConcatWithMaybe$ConcatWithObserver(observer, (MaybeSource) obj));
                return;
            case 2:
                observableSource.subscribe(new ObservableConcatWithSingle$ConcatWithObserver(observer, (SingleSource) obj));
                return;
            case 3:
                observableSource.subscribe(new ObservableDoFinally$DoFinallyObserver(observer, (io.reactivex.rxjava3.functions.a) obj));
                return;
            case 4:
                SequentialDisposable sequentialDisposable = new SequentialDisposable();
                observer.onSubscribe(sequentialDisposable);
                p.u4.z(obj);
                ObservableRepeatUntil$RepeatUntilObserver observableRepeatUntil$RepeatUntilObserver = new ObservableRepeatUntil$RepeatUntilObserver(observer, sequentialDisposable, observableSource);
                if (observableRepeatUntil$RepeatUntilObserver.getAndIncrement() == 0) {
                    int iAddAndGet = 1;
                    do {
                        observableRepeatUntil$RepeatUntilObserver.source.subscribe(observableRepeatUntil$RepeatUntilObserver);
                        iAddAndGet = observableRepeatUntil$RepeatUntilObserver.addAndGet(-iAddAndGet);
                    } while (iAddAndGet != 0);
                    return;
                }
                return;
            case 5:
                SequentialDisposable sequentialDisposable2 = new SequentialDisposable();
                observer.onSubscribe(sequentialDisposable2);
                new ObservableRetryBiPredicate$RetryBiObserver(observer, (io.reactivex.rxjava3.functions.d) obj, sequentialDisposable2, observableSource).a();
                return;
            case 6:
                observableSource.subscribe(new g1(observer, (io.reactivex.rxjava3.functions.g) obj, 0));
                return;
            case 7:
                try {
                    p.u4.z(obj);
                    throw null;
                } catch (NullPointerException e) {
                    throw e;
                } catch (Throwable th) {
                    he1.R(th);
                    RxJavaPlugins.b(th);
                    NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
                    nullPointerException.initCause(th);
                    throw nullPointerException;
                }
            case 8:
                observableSource.subscribe(new a4(observer, (io.reactivex.rxjava3.functions.c) obj));
                return;
            default:
                try {
                    Object obj2 = ((io.reactivex.rxjava3.functions.q) obj).get();
                    if (obj2 == null) {
                        throw io.reactivex.rxjava3.internal.util.c.b("The collectionSupplier returned a null Collection.");
                    }
                    Throwable th2 = io.reactivex.rxjava3.internal.util.c.a;
                    observableSource.subscribe(new f5(observer, (Collection) obj2));
                    return;
                } catch (Throwable th3) {
                    he1.R(th3);
                    observer.onSubscribe(EmptyDisposable.c);
                    observer.onError(th3);
                    return;
                }
        }
    }
}
