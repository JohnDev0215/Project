package io.reactivex.rxjava3.subjects;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class c extends e {
    public static final PublishSubject$PublishDisposable[] k = new PublishSubject$PublishDisposable[0];
    public static final PublishSubject$PublishDisposable[] l = new PublishSubject$PublishDisposable[0];
    public final AtomicReference c = new AtomicReference(l);
    public Throwable e;

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(PublishSubject$PublishDisposable publishSubject$PublishDisposable) {
        PublishSubject$PublishDisposable[] publishSubject$PublishDisposableArr;
        while (true) {
            AtomicReference atomicReference = this.c;
            PublishSubject$PublishDisposable[] publishSubject$PublishDisposableArr2 = (PublishSubject$PublishDisposable[]) atomicReference.get();
            if (publishSubject$PublishDisposableArr2 == k || publishSubject$PublishDisposableArr2 == (publishSubject$PublishDisposableArr = l)) {
                return;
            }
            int length = publishSubject$PublishDisposableArr2.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    i = -1;
                    break;
                } else if (publishSubject$PublishDisposableArr2[i] == publishSubject$PublishDisposable) {
                    break;
                } else {
                    i++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length != 1) {
                publishSubject$PublishDisposableArr = new PublishSubject$PublishDisposable[length - 1];
                System.arraycopy(publishSubject$PublishDisposableArr2, 0, publishSubject$PublishDisposableArr, 0, i);
                System.arraycopy(publishSubject$PublishDisposableArr2, i + 1, publishSubject$PublishDisposableArr, i, (length - i) - 1);
            }
            while (!atomicReference.compareAndSet(publishSubject$PublishDisposableArr2, publishSubject$PublishDisposableArr)) {
                if (atomicReference.get() != publishSubject$PublishDisposableArr2) {
                    break;
                }
            }
            return;
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        AtomicReference atomicReference = this.c;
        Object obj = atomicReference.get();
        Object obj2 = k;
        if (obj == obj2) {
            return;
        }
        PublishSubject$PublishDisposable[] publishSubject$PublishDisposableArr = (PublishSubject$PublishDisposable[]) atomicReference.getAndSet(obj2);
        for (PublishSubject$PublishDisposable publishSubject$PublishDisposable : publishSubject$PublishDisposableArr) {
            if (!publishSubject$PublishDisposable.get()) {
                publishSubject$PublishDisposable.downstream.onComplete();
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        io.reactivex.rxjava3.internal.util.c.c(th, "onError called with a null Throwable.");
        AtomicReference atomicReference = this.c;
        Object obj = atomicReference.get();
        Object obj2 = k;
        if (obj == obj2) {
            RxJavaPlugins.b(th);
            return;
        }
        this.e = th;
        PublishSubject$PublishDisposable[] publishSubject$PublishDisposableArr = (PublishSubject$PublishDisposable[]) atomicReference.getAndSet(obj2);
        for (PublishSubject$PublishDisposable publishSubject$PublishDisposable : publishSubject$PublishDisposableArr) {
            if (publishSubject$PublishDisposable.get()) {
                RxJavaPlugins.b(th);
            } else {
                publishSubject$PublishDisposable.downstream.onError(th);
            }
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        io.reactivex.rxjava3.internal.util.c.c(obj, "onNext called with a null value.");
        for (PublishSubject$PublishDisposable publishSubject$PublishDisposable : (PublishSubject$PublishDisposable[]) this.c.get()) {
            if (!publishSubject$PublishDisposable.get()) {
                publishSubject$PublishDisposable.downstream.onNext((Object) obj);
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (this.c.get() == k) {
            disposable.dispose();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        PublishSubject$PublishDisposable publishSubject$PublishDisposable = new PublishSubject$PublishDisposable(observer, this);
        observer.onSubscribe(publishSubject$PublishDisposable);
        while (true) {
            AtomicReference atomicReference = this.c;
            PublishSubject$PublishDisposable[] publishSubject$PublishDisposableArr = (PublishSubject$PublishDisposable[]) atomicReference.get();
            if (publishSubject$PublishDisposableArr == k) {
                Throwable th = this.e;
                if (th != null) {
                    observer.onError(th);
                    return;
                } else {
                    observer.onComplete();
                    return;
                }
            }
            int length = publishSubject$PublishDisposableArr.length;
            PublishSubject$PublishDisposable[] publishSubject$PublishDisposableArr2 = new PublishSubject$PublishDisposable[length + 1];
            System.arraycopy(publishSubject$PublishDisposableArr, 0, publishSubject$PublishDisposableArr2, 0, length);
            publishSubject$PublishDisposableArr2[length] = publishSubject$PublishDisposable;
            while (!atomicReference.compareAndSet(publishSubject$PublishDisposableArr, publishSubject$PublishDisposableArr2)) {
                if (atomicReference.get() != publishSubject$PublishDisposableArr) {
                    break;
                }
            }
            if (publishSubject$PublishDisposable.isDisposed()) {
                a(publishSubject$PublishDisposable);
                return;
            }
            return;
        }
    }
}
