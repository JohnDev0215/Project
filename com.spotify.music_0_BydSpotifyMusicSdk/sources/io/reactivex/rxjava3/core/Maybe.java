package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.disposables.b;
import io.reactivex.rxjava3.functions.c;
import io.reactivex.rxjava3.functions.g;
import io.reactivex.rxjava3.internal.functions.k;
import io.reactivex.rxjava3.internal.observers.DisposableAutoReleaseMultiObserver;
import io.reactivex.rxjava3.internal.operators.maybe.MaybeCallbackObserver;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public abstract class Maybe<T> implements MaybeSource<T> {
    public abstract void c(MaybeObserver maybeObserver);

    public final Disposable subscribe() {
        return subscribe(k.d, k.f, k.c);
    }

    public final Disposable subscribe(g gVar) {
        return subscribe(gVar, k.f, k.c);
    }

    public final Disposable subscribe(g gVar, g gVar2) {
        return subscribe(gVar, gVar2, k.c);
    }

    public final Disposable subscribe(g gVar, g gVar2, io.reactivex.rxjava3.functions.a aVar) {
        Objects.requireNonNull(gVar, "onSuccess is null");
        Objects.requireNonNull(gVar2, "onError is null");
        Objects.requireNonNull(aVar, "onComplete is null");
        MaybeCallbackObserver maybeCallbackObserver = new MaybeCallbackObserver(gVar, gVar2, aVar);
        subscribe(maybeCallbackObserver);
        return maybeCallbackObserver;
    }

    public final Disposable subscribe(g gVar, g gVar2, io.reactivex.rxjava3.functions.a aVar, b bVar) {
        Objects.requireNonNull(gVar, "onSuccess is null");
        Objects.requireNonNull(gVar2, "onError is null");
        Objects.requireNonNull(aVar, "onComplete is null");
        Objects.requireNonNull(bVar, "container is null");
        DisposableAutoReleaseMultiObserver disposableAutoReleaseMultiObserver = new DisposableAutoReleaseMultiObserver(gVar, gVar2, aVar, bVar);
        bVar.c(disposableAutoReleaseMultiObserver);
        subscribe(disposableAutoReleaseMultiObserver);
        return disposableAutoReleaseMultiObserver;
    }

    @Override // io.reactivex.rxjava3.core.MaybeSource
    public final void subscribe(MaybeObserver<? super T> maybeObserver) {
        Objects.requireNonNull(maybeObserver, "observer is null");
        c cVar = RxJavaPlugins.c;
        if (cVar != null) {
            try {
                maybeObserver = (MaybeObserver) cVar.k(this, maybeObserver);
            } catch (Throwable th) {
                throw io.reactivex.rxjava3.internal.util.c.f(th);
            }
        }
        Objects.requireNonNull(maybeObserver, "The RxJavaPlugins.onSubscribe hook returned a null MaybeObserver. Please check the handler provided to RxJavaPlugins.setOnMaybeSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
        try {
            c(maybeObserver);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th2) {
            he1.R(th2);
            NullPointerException nullPointerException = new NullPointerException("subscribeActual failed");
            nullPointerException.initCause(th2);
            throw nullPointerException;
        }
    }
}
