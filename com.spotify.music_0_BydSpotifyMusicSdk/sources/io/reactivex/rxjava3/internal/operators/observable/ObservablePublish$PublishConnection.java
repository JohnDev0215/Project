package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservablePublish$PublishConnection<T> extends AtomicReference<ObservablePublish$InnerDisposable<T>[]> implements Observer<T>, Disposable {
    public static final ObservablePublish$InnerDisposable[] c = new ObservablePublish$InnerDisposable[0];
    public static final ObservablePublish$InnerDisposable[] e = new ObservablePublish$InnerDisposable[0];
    private static final long serialVersionUID = -3251430252873581268L;
    final AtomicReference<ObservablePublish$PublishConnection<T>> current;
    Throwable error;
    final AtomicBoolean connect = new AtomicBoolean();
    final AtomicReference<Disposable> upstream = new AtomicReference<>();

    public ObservablePublish$PublishConnection(AtomicReference atomicReference) {
        this.current = atomicReference;
        lazySet(c);
    }

    public final void a(ObservablePublish$InnerDisposable observablePublish$InnerDisposable) {
        ObservablePublish$InnerDisposable<T>[] observablePublish$InnerDisposableArr;
        ObservablePublish$InnerDisposable[] observablePublish$InnerDisposableArr2;
        do {
            observablePublish$InnerDisposableArr = get();
            int length = observablePublish$InnerDisposableArr.length;
            if (length == 0) {
                return;
            }
            int i = 0;
            while (true) {
                if (i >= length) {
                    i = -1;
                    break;
                } else if (observablePublish$InnerDisposableArr[i] == observablePublish$InnerDisposable) {
                    break;
                } else {
                    i++;
                }
            }
            if (i < 0) {
                return;
            }
            observablePublish$InnerDisposableArr2 = c;
            if (length != 1) {
                observablePublish$InnerDisposableArr2 = new ObservablePublish$InnerDisposable[length - 1];
                System.arraycopy(observablePublish$InnerDisposableArr, 0, observablePublish$InnerDisposableArr2, 0, i);
                System.arraycopy(observablePublish$InnerDisposableArr, i + 1, observablePublish$InnerDisposableArr2, i, (length - i) - 1);
            }
        } while (!compareAndSet(observablePublish$InnerDisposableArr, observablePublish$InnerDisposableArr2));
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        getAndSet(e);
        AtomicReference<ObservablePublish$PublishConnection<T>> atomicReference = this.current;
        while (!atomicReference.compareAndSet(this, null) && atomicReference.get() == this) {
        }
        DisposableHelper.a(this.upstream);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return get() == e;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.upstream.lazySet(DisposableHelper.c);
        for (ObservablePublish$InnerDisposable<T> observablePublish$InnerDisposable : getAndSet(e)) {
            observablePublish$InnerDisposable.downstream.onComplete();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        Disposable disposable = this.upstream.get();
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable == disposableHelper) {
            RxJavaPlugins.b(th);
            return;
        }
        this.error = th;
        this.upstream.lazySet(disposableHelper);
        for (ObservablePublish$InnerDisposable<T> observablePublish$InnerDisposable : getAndSet(e)) {
            observablePublish$InnerDisposable.downstream.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        for (ObservablePublish$InnerDisposable<T> observablePublish$InnerDisposable : get()) {
            observablePublish$InnerDisposable.downstream.onNext(obj);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this.upstream, disposable);
    }
}
