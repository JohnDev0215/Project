package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.functions.Functions;
import io.reactivex.rxjava3.internal.fuseable.FuseToFlowable;
import io.reactivex.rxjava3.internal.observers.BlockingMultiObserver;
import io.reactivex.rxjava3.internal.observers.ConsumerSingleObserver;
import io.reactivex.rxjava3.internal.operators.completable.CompletableFromSingle;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableSingleSingle;
import io.reactivex.rxjava3.internal.operators.maybe.MaybeFilterSingle;
import io.reactivex.rxjava3.internal.operators.mixed.SingleFlatMapObservable;
import io.reactivex.rxjava3.internal.operators.single.SingleCreate;
import io.reactivex.rxjava3.internal.operators.single.SingleDelayWithObservable;
import io.reactivex.rxjava3.internal.operators.single.SingleError;
import io.reactivex.rxjava3.internal.operators.single.SingleFlatMap;
import io.reactivex.rxjava3.internal.operators.single.SingleFlatMapCompletable;
import io.reactivex.rxjava3.internal.operators.single.SingleJust;
import io.reactivex.rxjava3.internal.operators.single.SingleMap;
import io.reactivex.rxjava3.internal.operators.single.SingleObserveOn;
import io.reactivex.rxjava3.internal.operators.single.SingleOnErrorReturn;
import io.reactivex.rxjava3.internal.operators.single.SingleResumeNext;
import io.reactivex.rxjava3.internal.operators.single.SingleSubscribeOn;
import io.reactivex.rxjava3.internal.operators.single.SingleTimeout;
import io.reactivex.rxjava3.internal.operators.single.SingleToFlowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;

/* JADX INFO: loaded from: classes7.dex */
public abstract class Single<T> implements SingleSource<T> {
    protected abstract void subscribeActual(SingleObserver<? super T> observer);

    public static <T> Single<T> create(SingleOnSubscribe<T> source) {
        Objects.requireNonNull(source, "source is null");
        return RxJavaPlugins.onAssembly(new SingleCreate(source));
    }

    public static <T> Single<T> error(Supplier<? extends Throwable> supplier) {
        Objects.requireNonNull(supplier, "supplier is null");
        return RxJavaPlugins.onAssembly(new SingleError(supplier));
    }

    public static <T> Single<T> error(Throwable throwable) {
        Objects.requireNonNull(throwable, "throwable is null");
        return error((Supplier<? extends Throwable>) Functions.justSupplier(throwable));
    }

    public static <T> Single<T> just(T item) {
        Objects.requireNonNull(item, "item is null");
        return RxJavaPlugins.onAssembly(new SingleJust(item));
    }

    public final <U> Single<T> delaySubscription(ObservableSource<U> subscriptionIndicator) {
        Objects.requireNonNull(subscriptionIndicator, "subscriptionIndicator is null");
        return RxJavaPlugins.onAssembly(new SingleDelayWithObservable(this, subscriptionIndicator));
    }

    public final Single<T> delaySubscription(long time, TimeUnit unit) {
        return delaySubscription(time, unit, Schedulers.computation());
    }

    public final Single<T> delaySubscription(long time, TimeUnit unit, Scheduler scheduler) {
        return delaySubscription(Observable.timer(time, unit, scheduler));
    }

    public final Maybe<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate, "predicate is null");
        return RxJavaPlugins.onAssembly(new MaybeFilterSingle(this, predicate));
    }

    public final <R> Single<R> flatMap(Function<? super T, ? extends SingleSource<? extends R>> mapper) {
        Objects.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new SingleFlatMap(this, mapper));
    }

    public final <R> Observable<R> flatMapObservable(Function<? super T, ? extends ObservableSource<? extends R>> mapper) {
        Objects.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new SingleFlatMapObservable(this, mapper));
    }

    public final Completable flatMapCompletable(Function<? super T, ? extends CompletableSource> mapper) {
        Objects.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new SingleFlatMapCompletable(this, mapper));
    }

    public final T blockingGet() {
        BlockingMultiObserver blockingMultiObserver = new BlockingMultiObserver();
        subscribe(blockingMultiObserver);
        return (T) blockingMultiObserver.blockingGet();
    }

    public final <R> Single<R> map(Function<? super T, ? extends R> mapper) {
        Objects.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new SingleMap(this, mapper));
    }

    public final Single<T> observeOn(Scheduler scheduler) {
        Objects.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new SingleObserveOn(this, scheduler));
    }

    public final Single<T> onErrorReturnItem(T item) {
        Objects.requireNonNull(item, "item is null");
        return RxJavaPlugins.onAssembly(new SingleOnErrorReturn(this, null, item));
    }

    public final Single<T> onErrorResumeNext(Function<? super Throwable, ? extends SingleSource<? extends T>> fallbackSupplier) {
        Objects.requireNonNull(fallbackSupplier, "fallbackSupplier is null");
        return RxJavaPlugins.onAssembly(new SingleResumeNext(this, fallbackSupplier));
    }

    public final Single<T> retryWhen(Function<? super Flowable<Throwable>, ? extends Publisher<?>> handler) {
        return toSingle(toFlowable().retryWhen(handler));
    }

    public final Disposable subscribe() {
        return subscribe(Functions.emptyConsumer(), Functions.ON_ERROR_MISSING);
    }

    public final Disposable subscribe(Consumer<? super T> onSuccess, Consumer<? super Throwable> onError) {
        Objects.requireNonNull(onSuccess, "onSuccess is null");
        Objects.requireNonNull(onError, "onError is null");
        ConsumerSingleObserver consumerSingleObserver = new ConsumerSingleObserver(onSuccess, onError);
        subscribe(consumerSingleObserver);
        return consumerSingleObserver;
    }

    @Override // io.reactivex.rxjava3.core.SingleSource
    public final void subscribe(SingleObserver<? super T> observer) {
        Objects.requireNonNull(observer, "observer is null");
        SingleObserver<? super T> singleObserverOnSubscribe = RxJavaPlugins.onSubscribe(this, observer);
        Objects.requireNonNull(singleObserverOnSubscribe, "The RxJavaPlugins.onSubscribe hook returned a null SingleObserver. Please check the handler provided to RxJavaPlugins.setOnSingleSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
        try {
            subscribeActual(singleObserverOnSubscribe);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            NullPointerException nullPointerException = new NullPointerException("subscribeActual failed");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    public final <E extends SingleObserver<? super T>> E subscribeWith(E observer) {
        subscribe(observer);
        return observer;
    }

    public final Single<T> subscribeOn(Scheduler scheduler) {
        Objects.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new SingleSubscribeOn(this, scheduler));
    }

    public final Single<T> timeout(long timeout, TimeUnit unit) {
        return timeout0(timeout, unit, Schedulers.computation(), null);
    }

    private Single<T> timeout0(final long timeout, final TimeUnit unit, final Scheduler scheduler, final SingleSource<? extends T> fallback) {
        Objects.requireNonNull(unit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new SingleTimeout(this, timeout, unit, scheduler, fallback));
    }

    public final Completable ignoreElement() {
        return RxJavaPlugins.onAssembly(new CompletableFromSingle(this));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Flowable<T> toFlowable() {
        if (this instanceof FuseToFlowable) {
            return ((FuseToFlowable) this).fuseToFlowable();
        }
        return RxJavaPlugins.onAssembly(new SingleToFlowable(this));
    }

    private static <T> Single<T> toSingle(Flowable<T> source) {
        return RxJavaPlugins.onAssembly(new FlowableSingleSingle(source, null));
    }
}
