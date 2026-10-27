package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.fuseable.FuseToObservable;
import io.reactivex.rxjava3.internal.observers.BlockingMultiObserver;
import io.reactivex.rxjava3.internal.operators.maybe.MaybeCreate;
import io.reactivex.rxjava3.internal.operators.maybe.MaybeFlatMapCompletable;
import io.reactivex.rxjava3.internal.operators.maybe.MaybeMap;
import io.reactivex.rxjava3.internal.operators.maybe.MaybeSubscribeOn;
import io.reactivex.rxjava3.internal.operators.maybe.MaybeToObservable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;

/* JADX INFO: loaded from: classes7.dex */
public abstract class Maybe<T> implements MaybeSource<T> {
    protected abstract void subscribeActual(MaybeObserver<? super T> observer);

    public static <T> Maybe<T> create(MaybeOnSubscribe<T> onSubscribe) {
        Objects.requireNonNull(onSubscribe, "onSubscribe is null");
        return RxJavaPlugins.onAssembly(new MaybeCreate(onSubscribe));
    }

    public final T blockingGet() {
        BlockingMultiObserver blockingMultiObserver = new BlockingMultiObserver();
        subscribe(blockingMultiObserver);
        return (T) blockingMultiObserver.blockingGet();
    }

    public final Completable flatMapCompletable(Function<? super T, ? extends CompletableSource> mapper) {
        Objects.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new MaybeFlatMapCompletable(this, mapper));
    }

    public final <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
        Objects.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new MaybeMap(this, mapper));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Observable<T> toObservable() {
        if (this instanceof FuseToObservable) {
            return ((FuseToObservable) this).fuseToObservable();
        }
        return RxJavaPlugins.onAssembly(new MaybeToObservable(this));
    }

    @Override // io.reactivex.rxjava3.core.MaybeSource
    public final void subscribe(MaybeObserver<? super T> observer) {
        Objects.requireNonNull(observer, "observer is null");
        MaybeObserver<? super T> maybeObserverOnSubscribe = RxJavaPlugins.onSubscribe(this, observer);
        Objects.requireNonNull(maybeObserverOnSubscribe, "The RxJavaPlugins.onSubscribe hook returned a null MaybeObserver. Please check the handler provided to RxJavaPlugins.setOnMaybeSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
        try {
            subscribeActual(maybeObserverOnSubscribe);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            NullPointerException nullPointerException = new NullPointerException("subscribeActual failed");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    public final Maybe<T> subscribeOn(Scheduler scheduler) {
        Objects.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new MaybeSubscribeOn(this, scheduler));
    }
}
