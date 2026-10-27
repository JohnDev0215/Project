package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.c;
import io.reactivex.rxjava3.functions.g;
import io.reactivex.rxjava3.internal.functions.k;
import io.reactivex.rxjava3.internal.observers.CallbackCompletableObserver;
import io.reactivex.rxjava3.internal.observers.DisposableAutoReleaseMultiObserver;
import io.reactivex.rxjava3.internal.observers.EmptyCompletableObserver;
import io.reactivex.rxjava3.internal.operators.completable.b;
import io.reactivex.rxjava3.internal.operators.completable.e;
import io.reactivex.rxjava3.internal.operators.completable.n;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public abstract class Completable implements CompletableSource {
    public static e c(Runnable runnable) {
        Objects.requireNonNull(runnable, "run is null");
        return new e(4, runnable);
    }

    public final b b(Completable completable) {
        return new b(this, completable, 0);
    }

    public abstract void d(CompletableObserver completableObserver);

    public final n e(Object obj) {
        Objects.requireNonNull(obj, "completionValue is null");
        return new n(this, null, obj, 0);
    }

    public final Disposable subscribe() {
        EmptyCompletableObserver emptyCompletableObserver = new EmptyCompletableObserver();
        subscribe(emptyCompletableObserver);
        return emptyCompletableObserver;
    }

    @Override // io.reactivex.rxjava3.core.CompletableSource
    public final void subscribe(CompletableObserver completableObserver) {
        Objects.requireNonNull(completableObserver, "observer is null");
        try {
            c cVar = RxJavaPlugins.f;
            if (cVar != null) {
                try {
                    completableObserver = (CompletableObserver) cVar.k(this, completableObserver);
                } catch (Throwable th) {
                    throw io.reactivex.rxjava3.internal.util.c.f(th);
                }
            }
            Objects.requireNonNull(completableObserver, "The RxJavaPlugins.onSubscribe hook returned a null CompletableObserver. Please check the handler provided to RxJavaPlugins.setOnCompletableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            d(completableObserver);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th2) {
            he1.R(th2);
            RxJavaPlugins.b(th2);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
            nullPointerException.initCause(th2);
            throw nullPointerException;
        }
    }

    public final Disposable subscribe(io.reactivex.rxjava3.functions.a aVar, g gVar) {
        Objects.requireNonNull(gVar, "onError is null");
        Objects.requireNonNull(aVar, "onComplete is null");
        CallbackCompletableObserver callbackCompletableObserver = new CallbackCompletableObserver(gVar, aVar);
        subscribe(callbackCompletableObserver);
        return callbackCompletableObserver;
    }

    public final Disposable subscribe(io.reactivex.rxjava3.functions.a aVar, g gVar, io.reactivex.rxjava3.disposables.b bVar) {
        Objects.requireNonNull(aVar, "onComplete is null");
        Objects.requireNonNull(gVar, "onError is null");
        Objects.requireNonNull(bVar, "container is null");
        DisposableAutoReleaseMultiObserver disposableAutoReleaseMultiObserver = new DisposableAutoReleaseMultiObserver(k.d, gVar, aVar, bVar);
        bVar.c(disposableAutoReleaseMultiObserver);
        subscribe(disposableAutoReleaseMultiObserver);
        return disposableAutoReleaseMultiObserver;
    }

    public final Disposable subscribe(io.reactivex.rxjava3.functions.a aVar) {
        return subscribe(aVar, k.f);
    }
}
