package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.observers.DeferredScalarDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class t1 extends Observable implements io.reactivex.rxjava3.functions.q {
    public final /* synthetic */ int c;
    public final Object e;

    public /* synthetic */ t1(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    @Override // io.reactivex.rxjava3.functions.q
    public final Object get() throws Exception {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                ((io.reactivex.rxjava3.functions.a) obj).run();
                return null;
            case 1:
                Object objCall = ((Callable) obj).call();
                if (objCall == null) {
                    throw io.reactivex.rxjava3.internal.util.c.b("The Callable returned a null value.");
                }
                Throwable th = io.reactivex.rxjava3.internal.util.c.a;
                return objCall;
            case 2:
                ((Runnable) obj).run();
                return null;
            default:
                Object obj2 = ((io.reactivex.rxjava3.functions.q) obj).get();
                if (obj2 == null) {
                    throw io.reactivex.rxjava3.internal.util.c.b("The supplier returned a null value.");
                }
                Throwable th2 = io.reactivex.rxjava3.internal.util.c.a;
                return obj2;
        }
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                Disposable bVar = new io.reactivex.rxjava3.internal.fuseable.b();
                observer.onSubscribe(bVar);
                if (bVar.isDisposed()) {
                    return;
                }
                try {
                    ((io.reactivex.rxjava3.functions.a) obj).run();
                    if (bVar.isDisposed()) {
                        return;
                    }
                    observer.onComplete();
                    return;
                } catch (Throwable th) {
                    he1.R(th);
                    if (bVar.isDisposed()) {
                        RxJavaPlugins.b(th);
                        return;
                    } else {
                        observer.onError(th);
                        return;
                    }
                }
            case 1:
                DeferredScalarDisposable deferredScalarDisposable = new DeferredScalarDisposable(observer);
                observer.onSubscribe(deferredScalarDisposable);
                if (deferredScalarDisposable.isDisposed()) {
                    return;
                }
                try {
                    Object objCall = ((Callable) obj).call();
                    if (objCall == null) {
                        throw io.reactivex.rxjava3.internal.util.c.b("Callable returned a null value.");
                    }
                    Throwable th2 = io.reactivex.rxjava3.internal.util.c.a;
                    deferredScalarDisposable.c(objCall);
                    return;
                } catch (Throwable th3) {
                    he1.R(th3);
                    if (deferredScalarDisposable.isDisposed()) {
                        RxJavaPlugins.b(th3);
                        return;
                    } else {
                        observer.onError(th3);
                        return;
                    }
                }
            case 2:
                Disposable bVar2 = new io.reactivex.rxjava3.internal.fuseable.b();
                observer.onSubscribe(bVar2);
                if (bVar2.isDisposed()) {
                    return;
                }
                try {
                    ((Runnable) obj).run();
                    if (bVar2.isDisposed()) {
                        return;
                    }
                    observer.onComplete();
                    return;
                } catch (Throwable th4) {
                    he1.R(th4);
                    if (bVar2.isDisposed()) {
                        RxJavaPlugins.b(th4);
                        return;
                    } else {
                        observer.onError(th4);
                        return;
                    }
                }
            default:
                DeferredScalarDisposable deferredScalarDisposable2 = new DeferredScalarDisposable(observer);
                observer.onSubscribe(deferredScalarDisposable2);
                if (deferredScalarDisposable2.isDisposed()) {
                    return;
                }
                try {
                    Object obj2 = ((io.reactivex.rxjava3.functions.q) obj).get();
                    if (obj2 == null) {
                        throw io.reactivex.rxjava3.internal.util.c.b("Supplier returned a null value.");
                    }
                    Throwable th5 = io.reactivex.rxjava3.internal.util.c.a;
                    deferredScalarDisposable2.c(obj2);
                    return;
                } catch (Throwable th6) {
                    he1.R(th6);
                    if (deferredScalarDisposable2.isDisposed()) {
                        RxJavaPlugins.b(th6);
                        return;
                    } else {
                        observer.onError(th6);
                        return;
                    }
                }
        }
    }
}
