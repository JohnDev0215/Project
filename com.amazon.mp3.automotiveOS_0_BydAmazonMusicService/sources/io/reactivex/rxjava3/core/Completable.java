package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.functions.Functions;
import io.reactivex.rxjava3.internal.fuseable.FuseToObservable;
import io.reactivex.rxjava3.internal.observers.BlockingMultiObserver;
import io.reactivex.rxjava3.internal.observers.CallbackCompletableObserver;
import io.reactivex.rxjava3.internal.observers.EmptyCompletableObserver;
import io.reactivex.rxjava3.internal.operators.completable.CompletableAndThenCompletable;
import io.reactivex.rxjava3.internal.operators.completable.CompletableConcatArray;
import io.reactivex.rxjava3.internal.operators.completable.CompletableConcatIterable;
import io.reactivex.rxjava3.internal.operators.completable.CompletableCreate;
import io.reactivex.rxjava3.internal.operators.completable.CompletableDefer;
import io.reactivex.rxjava3.internal.operators.completable.CompletableEmpty;
import io.reactivex.rxjava3.internal.operators.completable.CompletableError;
import io.reactivex.rxjava3.internal.operators.completable.CompletableFromAction;
import io.reactivex.rxjava3.internal.operators.completable.CompletableFromUnsafeSource;
import io.reactivex.rxjava3.internal.operators.completable.CompletableMergeArray;
import io.reactivex.rxjava3.internal.operators.completable.CompletableOnErrorComplete;
import io.reactivex.rxjava3.internal.operators.completable.CompletablePeek;
import io.reactivex.rxjava3.internal.operators.completable.CompletableResumeNext;
import io.reactivex.rxjava3.internal.operators.completable.CompletableSubscribeOn;
import io.reactivex.rxjava3.internal.operators.completable.CompletableTimeout;
import io.reactivex.rxjava3.internal.operators.completable.CompletableTimer;
import io.reactivex.rxjava3.internal.operators.completable.CompletableToObservable;
import io.reactivex.rxjava3.internal.operators.completable.CompletableToSingle;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes7.dex */
public abstract class Completable implements CompletableSource {
    protected abstract void subscribeActual(CompletableObserver observer);

    public static Completable complete() {
        return RxJavaPlugins.onAssembly(CompletableEmpty.INSTANCE);
    }

    @SafeVarargs
    public static Completable concatArray(CompletableSource... sources) {
        Objects.requireNonNull(sources, "sources is null");
        if (sources.length == 0) {
            return complete();
        }
        if (sources.length == 1) {
            return wrap(sources[0]);
        }
        return RxJavaPlugins.onAssembly(new CompletableConcatArray(sources));
    }

    public static Completable concat(Iterable<? extends CompletableSource> sources) {
        Objects.requireNonNull(sources, "sources is null");
        return RxJavaPlugins.onAssembly(new CompletableConcatIterable(sources));
    }

    public static Completable create(CompletableOnSubscribe source) {
        Objects.requireNonNull(source, "source is null");
        return RxJavaPlugins.onAssembly(new CompletableCreate(source));
    }

    public static Completable defer(Supplier<? extends CompletableSource> supplier) {
        Objects.requireNonNull(supplier, "supplier is null");
        return RxJavaPlugins.onAssembly(new CompletableDefer(supplier));
    }

    public static Completable error(Throwable throwable) {
        Objects.requireNonNull(throwable, "throwable is null");
        return RxJavaPlugins.onAssembly(new CompletableError(throwable));
    }

    public static Completable fromAction(Action action) {
        Objects.requireNonNull(action, "action is null");
        return RxJavaPlugins.onAssembly(new CompletableFromAction(action));
    }

    @SafeVarargs
    public static Completable mergeArray(CompletableSource... sources) {
        Objects.requireNonNull(sources, "sources is null");
        if (sources.length == 0) {
            return complete();
        }
        if (sources.length == 1) {
            return wrap(sources[0]);
        }
        return RxJavaPlugins.onAssembly(new CompletableMergeArray(sources));
    }

    public static Completable timer(long delay, TimeUnit unit) {
        return timer(delay, unit, Schedulers.computation());
    }

    public static Completable timer(long delay, TimeUnit unit, Scheduler scheduler) {
        Objects.requireNonNull(unit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new CompletableTimer(delay, unit, scheduler));
    }

    private static NullPointerException toNpe(Throwable ex) {
        NullPointerException nullPointerException = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
        nullPointerException.initCause(ex);
        return nullPointerException;
    }

    public static Completable wrap(CompletableSource source) {
        Objects.requireNonNull(source, "source is null");
        if (source instanceof Completable) {
            return RxJavaPlugins.onAssembly((Completable) source);
        }
        return RxJavaPlugins.onAssembly(new CompletableFromUnsafeSource(source));
    }

    public final Completable andThen(CompletableSource next) {
        Objects.requireNonNull(next, "next is null");
        return RxJavaPlugins.onAssembly(new CompletableAndThenCompletable(this, next));
    }

    public final void blockingAwait() {
        BlockingMultiObserver blockingMultiObserver = new BlockingMultiObserver();
        subscribe(blockingMultiObserver);
        blockingMultiObserver.blockingGet();
    }

    public final boolean blockingAwait(long timeout, TimeUnit unit) {
        Objects.requireNonNull(unit, "unit is null");
        BlockingMultiObserver blockingMultiObserver = new BlockingMultiObserver();
        subscribe(blockingMultiObserver);
        return blockingMultiObserver.blockingAwait(timeout, unit);
    }

    public final void blockingSubscribe() {
        blockingSubscribe(Functions.EMPTY_ACTION, Functions.ERROR_CONSUMER);
    }

    public final void blockingSubscribe(Action onComplete, Consumer<? super Throwable> onError) {
        Objects.requireNonNull(onComplete, "onComplete is null");
        Objects.requireNonNull(onError, "onError is null");
        BlockingMultiObserver blockingMultiObserver = new BlockingMultiObserver();
        subscribe(blockingMultiObserver);
        blockingMultiObserver.blockingConsume(Functions.emptyConsumer(), onError, onComplete);
    }

    public final Completable doOnComplete(Action onComplete) {
        Consumer<? super Disposable> consumerEmptyConsumer = Functions.emptyConsumer();
        Consumer<? super Throwable> consumerEmptyConsumer2 = Functions.emptyConsumer();
        Action action = Functions.EMPTY_ACTION;
        return doOnLifecycle(consumerEmptyConsumer, consumerEmptyConsumer2, onComplete, action, action, action);
    }

    public final Completable doOnDispose(Action onDispose) {
        Consumer<? super Disposable> consumerEmptyConsumer = Functions.emptyConsumer();
        Consumer<? super Throwable> consumerEmptyConsumer2 = Functions.emptyConsumer();
        Action action = Functions.EMPTY_ACTION;
        return doOnLifecycle(consumerEmptyConsumer, consumerEmptyConsumer2, action, action, action, onDispose);
    }

    public final Completable doOnError(Consumer<? super Throwable> onError) {
        Consumer<? super Disposable> consumerEmptyConsumer = Functions.emptyConsumer();
        Action action = Functions.EMPTY_ACTION;
        return doOnLifecycle(consumerEmptyConsumer, onError, action, action, action, action);
    }

    private Completable doOnLifecycle(final Consumer<? super Disposable> onSubscribe, final Consumer<? super Throwable> onError, final Action onComplete, final Action onTerminate, final Action onAfterTerminate, final Action onDispose) {
        Objects.requireNonNull(onSubscribe, "onSubscribe is null");
        Objects.requireNonNull(onError, "onError is null");
        Objects.requireNonNull(onComplete, "onComplete is null");
        Objects.requireNonNull(onTerminate, "onTerminate is null");
        Objects.requireNonNull(onAfterTerminate, "onAfterTerminate is null");
        Objects.requireNonNull(onDispose, "onDispose is null");
        return RxJavaPlugins.onAssembly(new CompletablePeek(this, onSubscribe, onError, onComplete, onTerminate, onAfterTerminate, onDispose));
    }

    public final Completable doOnSubscribe(Consumer<? super Disposable> onSubscribe) {
        Consumer<? super Throwable> consumerEmptyConsumer = Functions.emptyConsumer();
        Action action = Functions.EMPTY_ACTION;
        return doOnLifecycle(onSubscribe, consumerEmptyConsumer, action, action, action, action);
    }

    public final Completable doOnTerminate(Action onTerminate) {
        Consumer<? super Disposable> consumerEmptyConsumer = Functions.emptyConsumer();
        Consumer<? super Throwable> consumerEmptyConsumer2 = Functions.emptyConsumer();
        Action action = Functions.EMPTY_ACTION;
        return doOnLifecycle(consumerEmptyConsumer, consumerEmptyConsumer2, action, onTerminate, action, action);
    }

    public final Completable onErrorComplete(Predicate<? super Throwable> predicate) {
        Objects.requireNonNull(predicate, "predicate is null");
        return RxJavaPlugins.onAssembly(new CompletableOnErrorComplete(this, predicate));
    }

    public final Completable onErrorResumeNext(Function<? super Throwable, ? extends CompletableSource> fallbackSupplier) {
        Objects.requireNonNull(fallbackSupplier, "fallbackSupplier is null");
        return RxJavaPlugins.onAssembly(new CompletableResumeNext(this, fallbackSupplier));
    }

    public final Disposable subscribe() {
        EmptyCompletableObserver emptyCompletableObserver = new EmptyCompletableObserver();
        subscribe(emptyCompletableObserver);
        return emptyCompletableObserver;
    }

    @Override // io.reactivex.rxjava3.core.CompletableSource
    public final void subscribe(CompletableObserver observer) {
        Objects.requireNonNull(observer, "observer is null");
        try {
            CompletableObserver completableObserverOnSubscribe = RxJavaPlugins.onSubscribe(this, observer);
            Objects.requireNonNull(completableObserverOnSubscribe, "The RxJavaPlugins.onSubscribe hook returned a null CompletableObserver. Please check the handler provided to RxJavaPlugins.setOnCompletableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            subscribeActual(completableObserverOnSubscribe);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
            throw toNpe(th);
        }
    }

    public final Disposable subscribe(Action onComplete, Consumer<? super Throwable> onError) {
        Objects.requireNonNull(onError, "onError is null");
        Objects.requireNonNull(onComplete, "onComplete is null");
        CallbackCompletableObserver callbackCompletableObserver = new CallbackCompletableObserver(onError, onComplete);
        subscribe(callbackCompletableObserver);
        return callbackCompletableObserver;
    }

    public final Completable subscribeOn(Scheduler scheduler) {
        Objects.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new CompletableSubscribeOn(this, scheduler));
    }

    public final Completable timeout(long timeout, TimeUnit unit) {
        return timeout0(timeout, unit, Schedulers.computation(), null);
    }

    private Completable timeout0(long timeout, TimeUnit unit, Scheduler scheduler, CompletableSource fallback) {
        Objects.requireNonNull(unit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new CompletableTimeout(this, timeout, unit, scheduler, fallback));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> Observable<T> toObservable() {
        if (this instanceof FuseToObservable) {
            return ((FuseToObservable) this).fuseToObservable();
        }
        return RxJavaPlugins.onAssembly(new CompletableToObservable(this));
    }

    public final <T> Single<T> toSingle(Supplier<? extends T> completionValueSupplier) {
        Objects.requireNonNull(completionValueSupplier, "completionValueSupplier is null");
        return RxJavaPlugins.onAssembly(new CompletableToSingle(this, completionValueSupplier, null));
    }
}
