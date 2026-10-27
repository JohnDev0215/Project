package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.functions.Functions;
import io.reactivex.rxjava3.internal.functions.ObjectHelper;
import io.reactivex.rxjava3.internal.observers.BlockingFirstObserver;
import io.reactivex.rxjava3.internal.observers.LambdaObserver;
import io.reactivex.rxjava3.internal.operators.observable.ObservableBlockingSubscribe;
import io.reactivex.rxjava3.internal.operators.observable.ObservableCollectSingle;
import io.reactivex.rxjava3.internal.operators.observable.ObservableConcatMap;
import io.reactivex.rxjava3.internal.operators.observable.ObservableCreate;
import io.reactivex.rxjava3.internal.operators.observable.ObservableDebounceTimed;
import io.reactivex.rxjava3.internal.operators.observable.ObservableDistinctUntilChanged;
import io.reactivex.rxjava3.internal.operators.observable.ObservableDoOnEach;
import io.reactivex.rxjava3.internal.operators.observable.ObservableDoOnLifecycle;
import io.reactivex.rxjava3.internal.operators.observable.ObservableElementAtMaybe;
import io.reactivex.rxjava3.internal.operators.observable.ObservableElementAtSingle;
import io.reactivex.rxjava3.internal.operators.observable.ObservableEmpty;
import io.reactivex.rxjava3.internal.operators.observable.ObservableError;
import io.reactivex.rxjava3.internal.operators.observable.ObservableFilter;
import io.reactivex.rxjava3.internal.operators.observable.ObservableFlatMap;
import io.reactivex.rxjava3.internal.operators.observable.ObservableFlatMapCompletableCompletable;
import io.reactivex.rxjava3.internal.operators.observable.ObservableFromArray;
import io.reactivex.rxjava3.internal.operators.observable.ObservableFromCallable;
import io.reactivex.rxjava3.internal.operators.observable.ObservableFromIterable;
import io.reactivex.rxjava3.internal.operators.observable.ObservableFromUnsafeSource;
import io.reactivex.rxjava3.internal.operators.observable.ObservableHide;
import io.reactivex.rxjava3.internal.operators.observable.ObservableInternalHelper;
import io.reactivex.rxjava3.internal.operators.observable.ObservableJust;
import io.reactivex.rxjava3.internal.operators.observable.ObservableMap;
import io.reactivex.rxjava3.internal.operators.observable.ObservableObserveOn;
import io.reactivex.rxjava3.internal.operators.observable.ObservableOnErrorNext;
import io.reactivex.rxjava3.internal.operators.observable.ObservableOnErrorReturn;
import io.reactivex.rxjava3.internal.operators.observable.ObservableRetryWhen;
import io.reactivex.rxjava3.internal.operators.observable.ObservableScalarXMap;
import io.reactivex.rxjava3.internal.operators.observable.ObservableSingleSingle;
import io.reactivex.rxjava3.internal.operators.observable.ObservableSubscribeOn;
import io.reactivex.rxjava3.internal.operators.observable.ObservableSwitchIfEmpty;
import io.reactivex.rxjava3.internal.operators.observable.ObservableTake;
import io.reactivex.rxjava3.internal.operators.observable.ObservableThrottleFirstTimed;
import io.reactivex.rxjava3.internal.operators.observable.ObservableTimer;
import io.reactivex.rxjava3.internal.operators.observable.ObservableToListSingle;
import io.reactivex.rxjava3.internal.operators.observable.ObservableZip;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import io.reactivex.rxjava3.internal.util.HashMapSupplier;
import io.reactivex.rxjava3.operators.ScalarSupplier;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes7.dex */
public abstract class Observable<T> implements ObservableSource<T> {
    protected abstract void subscribeActual(Observer<? super T> observer);

    public static int bufferSize() {
        return Flowable.bufferSize();
    }

    @SafeVarargs
    public static <T> Observable<T> concatArray(ObservableSource<? extends T>... sources) {
        Objects.requireNonNull(sources, "sources is null");
        if (sources.length == 0) {
            return empty();
        }
        if (sources.length == 1) {
            return wrap(sources[0]);
        }
        return RxJavaPlugins.onAssembly(new ObservableConcatMap(fromArray(sources), Functions.identity(), bufferSize(), ErrorMode.BOUNDARY));
    }

    public static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
        Objects.requireNonNull(source, "source is null");
        return RxJavaPlugins.onAssembly(new ObservableCreate(source));
    }

    public static <T> Observable<T> empty() {
        return RxJavaPlugins.onAssembly(ObservableEmpty.INSTANCE);
    }

    public static <T> Observable<T> error(Supplier<? extends Throwable> supplier) {
        Objects.requireNonNull(supplier, "supplier is null");
        return RxJavaPlugins.onAssembly(new ObservableError(supplier));
    }

    public static <T> Observable<T> error(Throwable throwable) {
        Objects.requireNonNull(throwable, "throwable is null");
        return error((Supplier<? extends Throwable>) Functions.justSupplier(throwable));
    }

    @SafeVarargs
    public static <T> Observable<T> fromArray(T... items) {
        Objects.requireNonNull(items, "items is null");
        if (items.length == 0) {
            return empty();
        }
        if (items.length == 1) {
            return just(items[0]);
        }
        return RxJavaPlugins.onAssembly(new ObservableFromArray(items));
    }

    public static <T> Observable<T> fromCallable(Callable<? extends T> callable) {
        Objects.requireNonNull(callable, "callable is null");
        return RxJavaPlugins.onAssembly(new ObservableFromCallable(callable));
    }

    public static <T> Observable<T> fromIterable(Iterable<? extends T> source) {
        Objects.requireNonNull(source, "source is null");
        return RxJavaPlugins.onAssembly(new ObservableFromIterable(source));
    }

    public static <T> Observable<T> just(T item) {
        Objects.requireNonNull(item, "item is null");
        return RxJavaPlugins.onAssembly(new ObservableJust(item));
    }

    public static <T> Observable<T> merge(Iterable<? extends ObservableSource<? extends T>> sources) {
        return fromIterable(sources).flatMap(Functions.identity());
    }

    public static Observable<Long> timer(long delay, TimeUnit unit) {
        return timer(delay, unit, Schedulers.computation());
    }

    public static Observable<Long> timer(long delay, TimeUnit unit, Scheduler scheduler) {
        Objects.requireNonNull(unit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new ObservableTimer(Math.max(delay, 0L), unit, scheduler));
    }

    public static <T> Observable<T> wrap(ObservableSource<T> source) {
        Objects.requireNonNull(source, "source is null");
        if (source instanceof Observable) {
            return RxJavaPlugins.onAssembly((Observable) source);
        }
        return RxJavaPlugins.onAssembly(new ObservableFromUnsafeSource(source));
    }

    public static <T1, T2, R> Observable<R> zip(ObservableSource<? extends T1> source1, ObservableSource<? extends T2> source2, BiFunction<? super T1, ? super T2, ? extends R> zipper) {
        Objects.requireNonNull(source1, "source1 is null");
        Objects.requireNonNull(source2, "source2 is null");
        Objects.requireNonNull(zipper, "zipper is null");
        return zipArray(Functions.toFunction(zipper), false, bufferSize(), source1, source2);
    }

    @SafeVarargs
    public static <T, R> Observable<R> zipArray(Function<? super Object[], ? extends R> zipper, boolean delayError, int bufferSize, ObservableSource<? extends T>... sources) {
        Objects.requireNonNull(sources, "sources is null");
        if (sources.length == 0) {
            return empty();
        }
        Objects.requireNonNull(zipper, "zipper is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        return RxJavaPlugins.onAssembly(new ObservableZip(sources, null, zipper, bufferSize, delayError));
    }

    public final T blockingFirst() {
        BlockingFirstObserver blockingFirstObserver = new BlockingFirstObserver();
        subscribe(blockingFirstObserver);
        T tBlockingGet = blockingFirstObserver.blockingGet();
        if (tBlockingGet != null) {
            return tBlockingGet;
        }
        throw new NoSuchElementException();
    }

    public final void blockingSubscribe(Consumer<? super T> onNext) {
        ObservableBlockingSubscribe.subscribe(this, onNext, Functions.ON_ERROR_MISSING, Functions.EMPTY_ACTION);
    }

    public final <U> Observable<U> cast(Class<U> cls) {
        Objects.requireNonNull(cls, "clazz is null");
        return (Observable<U>) map(Functions.castFunction(cls));
    }

    public final <U> Single<U> collect(Supplier<? extends U> initialItemSupplier, BiConsumer<? super U, ? super T> collector) {
        Objects.requireNonNull(initialItemSupplier, "initialItemSupplier is null");
        Objects.requireNonNull(collector, "collector is null");
        return RxJavaPlugins.onAssembly(new ObservableCollectSingle(this, initialItemSupplier, collector));
    }

    public final <R> Observable<R> concatMap(Function<? super T, ? extends ObservableSource<? extends R>> mapper) {
        return concatMap(mapper, 2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> Observable<R> concatMap(Function<? super T, ? extends ObservableSource<? extends R>> mapper, int bufferSize) {
        Objects.requireNonNull(mapper, "mapper is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        if (this instanceof ScalarSupplier) {
            Object obj = ((ScalarSupplier) this).get();
            if (obj == null) {
                return empty();
            }
            return ObservableScalarXMap.scalarXMap(obj, mapper);
        }
        return RxJavaPlugins.onAssembly(new ObservableConcatMap(this, mapper, bufferSize, ErrorMode.IMMEDIATE));
    }

    public final Observable<T> debounce(long timeout, TimeUnit unit) {
        return debounce(timeout, unit, Schedulers.computation());
    }

    public final Observable<T> debounce(long timeout, TimeUnit unit, Scheduler scheduler) {
        Objects.requireNonNull(unit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new ObservableDebounceTimed(this, timeout, unit, scheduler));
    }

    public final Observable<T> defaultIfEmpty(T defaultItem) {
        Objects.requireNonNull(defaultItem, "defaultItem is null");
        return switchIfEmpty(just(defaultItem));
    }

    public final Observable<T> distinctUntilChanged() {
        return distinctUntilChanged(Functions.identity());
    }

    public final <K> Observable<T> distinctUntilChanged(Function<? super T, K> keySelector) {
        Objects.requireNonNull(keySelector, "keySelector is null");
        return RxJavaPlugins.onAssembly(new ObservableDistinctUntilChanged(this, keySelector, ObjectHelper.equalsPredicate()));
    }

    public final Observable<T> doAfterTerminate(Action onAfterTerminate) {
        Objects.requireNonNull(onAfterTerminate, "onAfterTerminate is null");
        return doOnEach(Functions.emptyConsumer(), Functions.emptyConsumer(), Functions.EMPTY_ACTION, onAfterTerminate);
    }

    public final Observable<T> doOnComplete(Action onComplete) {
        return doOnEach(Functions.emptyConsumer(), Functions.emptyConsumer(), onComplete, Functions.EMPTY_ACTION);
    }

    private Observable<T> doOnEach(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete, Action onAfterTerminate) {
        Objects.requireNonNull(onNext, "onNext is null");
        Objects.requireNonNull(onError, "onError is null");
        Objects.requireNonNull(onComplete, "onComplete is null");
        Objects.requireNonNull(onAfterTerminate, "onAfterTerminate is null");
        return RxJavaPlugins.onAssembly(new ObservableDoOnEach(this, onNext, onError, onComplete, onAfterTerminate));
    }

    public final Observable<T> doOnError(Consumer<? super Throwable> onError) {
        Consumer<? super T> consumerEmptyConsumer = Functions.emptyConsumer();
        Action action = Functions.EMPTY_ACTION;
        return doOnEach(consumerEmptyConsumer, onError, action, action);
    }

    public final Observable<T> doOnLifecycle(Consumer<? super Disposable> onSubscribe, Action onDispose) {
        Objects.requireNonNull(onSubscribe, "onSubscribe is null");
        Objects.requireNonNull(onDispose, "onDispose is null");
        return RxJavaPlugins.onAssembly(new ObservableDoOnLifecycle(this, onSubscribe, onDispose));
    }

    public final Observable<T> doOnNext(Consumer<? super T> onNext) {
        Consumer<? super Throwable> consumerEmptyConsumer = Functions.emptyConsumer();
        Action action = Functions.EMPTY_ACTION;
        return doOnEach(onNext, consumerEmptyConsumer, action, action);
    }

    public final Observable<T> doOnSubscribe(Consumer<? super Disposable> onSubscribe) {
        return doOnLifecycle(onSubscribe, Functions.EMPTY_ACTION);
    }

    public final Maybe<T> elementAt(long index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index >= 0 required but it was " + index);
        }
        return RxJavaPlugins.onAssembly(new ObservableElementAtMaybe(this, index));
    }

    public final Single<T> elementAtOrError(long index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index >= 0 required but it was " + index);
        }
        return RxJavaPlugins.onAssembly(new ObservableElementAtSingle(this, index, null));
    }

    public final Observable<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate, "predicate is null");
        return RxJavaPlugins.onAssembly(new ObservableFilter(this, predicate));
    }

    public final Maybe<T> firstElement() {
        return elementAt(0L);
    }

    public final Single<T> firstOrError() {
        return elementAtOrError(0L);
    }

    public final <R> Observable<R> flatMap(Function<? super T, ? extends ObservableSource<? extends R>> mapper) {
        return flatMap((Function) mapper, false);
    }

    public final <R> Observable<R> flatMap(Function<? super T, ? extends ObservableSource<? extends R>> mapper, boolean delayErrors) {
        return flatMap(mapper, delayErrors, Integer.MAX_VALUE);
    }

    public final <R> Observable<R> flatMap(Function<? super T, ? extends ObservableSource<? extends R>> mapper, boolean delayErrors, int maxConcurrency) {
        return flatMap(mapper, delayErrors, maxConcurrency, bufferSize());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> Observable<R> flatMap(Function<? super T, ? extends ObservableSource<? extends R>> mapper, boolean delayErrors, int maxConcurrency, int bufferSize) {
        Objects.requireNonNull(mapper, "mapper is null");
        ObjectHelper.verifyPositive(maxConcurrency, "maxConcurrency");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        if (this instanceof ScalarSupplier) {
            Object obj = ((ScalarSupplier) this).get();
            if (obj == null) {
                return empty();
            }
            return ObservableScalarXMap.scalarXMap(obj, mapper);
        }
        return RxJavaPlugins.onAssembly(new ObservableFlatMap(this, mapper, delayErrors, maxConcurrency, bufferSize));
    }

    public final <U, R> Observable<R> flatMap(Function<? super T, ? extends ObservableSource<? extends U>> mapper, BiFunction<? super T, ? super U, ? extends R> combiner) {
        return flatMap(mapper, combiner, false, bufferSize(), bufferSize());
    }

    public final <U, R> Observable<R> flatMap(Function<? super T, ? extends ObservableSource<? extends U>> mapper, BiFunction<? super T, ? super U, ? extends R> combiner, boolean delayErrors, int maxConcurrency, int bufferSize) {
        Objects.requireNonNull(mapper, "mapper is null");
        Objects.requireNonNull(combiner, "combiner is null");
        return flatMap(ObservableInternalHelper.flatMapWithCombiner(mapper, combiner), delayErrors, maxConcurrency, bufferSize);
    }

    public final Completable flatMapCompletable(Function<? super T, ? extends CompletableSource> mapper) {
        return flatMapCompletable(mapper, false);
    }

    public final Completable flatMapCompletable(Function<? super T, ? extends CompletableSource> mapper, boolean delayErrors) {
        Objects.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new ObservableFlatMapCompletableCompletable(this, mapper, delayErrors));
    }

    public final Observable<T> hide() {
        return RxJavaPlugins.onAssembly(new ObservableHide(this));
    }

    public final <R> Observable<R> map(Function<? super T, ? extends R> mapper) {
        Objects.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new ObservableMap(this, mapper));
    }

    public final Observable<T> observeOn(Scheduler scheduler) {
        return observeOn(scheduler, false, bufferSize());
    }

    public final Observable<T> observeOn(Scheduler scheduler, boolean delayError, int bufferSize) {
        Objects.requireNonNull(scheduler, "scheduler is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        return RxJavaPlugins.onAssembly(new ObservableObserveOn(this, scheduler, delayError, bufferSize));
    }

    public final Observable<T> onErrorResumeNext(Function<? super Throwable, ? extends ObservableSource<? extends T>> fallbackSupplier) {
        Objects.requireNonNull(fallbackSupplier, "fallbackSupplier is null");
        return RxJavaPlugins.onAssembly(new ObservableOnErrorNext(this, fallbackSupplier));
    }

    public final Observable<T> onErrorReturn(Function<? super Throwable, ? extends T> itemSupplier) {
        Objects.requireNonNull(itemSupplier, "itemSupplier is null");
        return RxJavaPlugins.onAssembly(new ObservableOnErrorReturn(this, itemSupplier));
    }

    public final Observable<T> onErrorReturnItem(T item) {
        Objects.requireNonNull(item, "item is null");
        return onErrorReturn(Functions.justFunction(item));
    }

    public final Observable<T> retryWhen(Function<? super Observable<Throwable>, ? extends ObservableSource<?>> handler) {
        Objects.requireNonNull(handler, "handler is null");
        return RxJavaPlugins.onAssembly(new ObservableRetryWhen(this, handler));
    }

    public final Single<T> singleOrError() {
        return RxJavaPlugins.onAssembly(new ObservableSingleSingle(this, null));
    }

    public final Observable<T> startWithItem(T item) {
        return concatArray(just(item), this);
    }

    public final Disposable subscribe(Consumer<? super T> onNext) {
        return subscribe(onNext, Functions.ON_ERROR_MISSING, Functions.EMPTY_ACTION);
    }

    public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError) {
        return subscribe(onNext, onError, Functions.EMPTY_ACTION);
    }

    public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete) {
        Objects.requireNonNull(onNext, "onNext is null");
        Objects.requireNonNull(onError, "onError is null");
        Objects.requireNonNull(onComplete, "onComplete is null");
        LambdaObserver lambdaObserver = new LambdaObserver(onNext, onError, onComplete, Functions.emptyConsumer());
        subscribe(lambdaObserver);
        return lambdaObserver;
    }

    @Override // io.reactivex.rxjava3.core.ObservableSource
    public final void subscribe(Observer<? super T> observer) {
        Objects.requireNonNull(observer, "observer is null");
        try {
            Observer<? super T> observerOnSubscribe = RxJavaPlugins.onSubscribe(this, observer);
            Objects.requireNonNull(observerOnSubscribe, "The RxJavaPlugins.onSubscribe hook returned a null Observer. Please change the handler provided to RxJavaPlugins.setOnObservableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            subscribeActual(observerOnSubscribe);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    public final Observable<T> subscribeOn(Scheduler scheduler) {
        Objects.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new ObservableSubscribeOn(this, scheduler));
    }

    public final Observable<T> switchIfEmpty(ObservableSource<? extends T> other) {
        Objects.requireNonNull(other, "other is null");
        return RxJavaPlugins.onAssembly(new ObservableSwitchIfEmpty(this, other));
    }

    public final Observable<T> take(long count) {
        if (count < 0) {
            throw new IllegalArgumentException("count >= 0 required but it was " + count);
        }
        return RxJavaPlugins.onAssembly(new ObservableTake(this, count));
    }

    public final Observable<T> throttleFirst(long windowDuration, TimeUnit unit) {
        return throttleFirst(windowDuration, unit, Schedulers.computation());
    }

    public final Observable<T> throttleFirst(long skipDuration, TimeUnit unit, Scheduler scheduler) {
        Objects.requireNonNull(unit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new ObservableThrottleFirstTimed(this, skipDuration, unit, scheduler));
    }

    public final Observable<T> throttleWithTimeout(long timeout, TimeUnit unit) {
        return debounce(timeout, unit);
    }

    public final Single<List<T>> toList() {
        return toList(16);
    }

    public final Single<List<T>> toList(int capacityHint) {
        ObjectHelper.verifyPositive(capacityHint, "capacityHint");
        return RxJavaPlugins.onAssembly(new ObservableToListSingle(this, capacityHint));
    }

    public final <K, V> Single<Map<K, V>> toMap(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2) {
        Objects.requireNonNull(function, "keySelector is null");
        Objects.requireNonNull(function2, "valueSelector is null");
        return (Single<Map<K, V>>) collect(HashMapSupplier.asSupplier(), Functions.toMapKeyValueSelector(function, function2));
    }
}
